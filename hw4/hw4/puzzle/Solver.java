package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

public class Solver {

    public class SearchNode implements Comparable<SearchNode> {
        private WorldState worldState;
        private int step;
        private SearchNode prenode;

        public SearchNode(WorldState ws,int sp,SearchNode pre){
            worldState = ws;
            step = sp;
            prenode = pre;
        }

        //A second optimization???
        @Override
        public int compareTo(SearchNode o) {
            if(this.step+this.worldState.estimatedDistanceToGoal()<o.step+o.worldState.estimatedDistanceToGoal())
                return -1;
            else if(this.step+this.worldState.estimatedDistanceToGoal()==o.step+o.worldState.estimatedDistanceToGoal())
                return 0;
            else
                return 1;
        }
    }

    private int moves;
    private Iterable<WorldState> solution;

    /**
     * Constructor which solves the puzzle, computing
     * everything necessary for moves() and solution() to
     * not have to solve the problem again. Solves the
     * puzzle using the A* algorithm. Assumes a solution exists.
     * */
    public Solver(WorldState initial)
    {
        //int num = 0;
        MinPQ<SearchNode> PQ = new MinPQ<>();
        List<WorldState> list = new ArrayList<>();
        PQ.insert(new SearchNode(initial,0,null));
        while(true) {
            SearchNode X = PQ.delMin();
            //System.out.println(X.worldState.estimatedDistanceToGoal());
            if (X.worldState.isGoal()) {
                moves = X.step;
                SearchNode node = X;
                for(int i=0;i<=moves;i++) {
                    list.addFirst(node.worldState);
                    node = node.prenode;
                }
                solution = list;
                return;
            }
            for(WorldState w:X.worldState.neighbors()) {
                if (X.prenode!=null&&w.equals(X.prenode.worldState))continue;
                PQ.insert(new SearchNode(w, X.step + 1, X));
                //num++;
                //System.out.println(num);
            }
        }
    }

    /**
     * Returns the minimum number of moves
     * to solve the puzzle starting
     * at the initial WorldState.
     * */
    public int moves(){
        return moves;
    }

    /**
     * Returns a sequence of WorldStates
     * from the initial WorldState
     * to the solution.
     * */
    public Iterable<WorldState> solution(){
        return solution;
    }
}
