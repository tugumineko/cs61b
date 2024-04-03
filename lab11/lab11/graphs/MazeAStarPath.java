package lab11.graphs;

import edu.princeton.cs.algs4.MinPQ;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 *  @author Josh Hug
 */
public class MazeAStarPath extends MazeExplorer {
    private int s;
    private int t;
    private int tx;
    private int ty;
    private boolean targetFound = false;
    private Maze maze;

    private class SearchNode implements Comparable<SearchNode>{
        private int V;

        private SearchNode(int number){
            V = number;
        }

        @Override
        public int compareTo(SearchNode o) {
            return distTo[this.V]+h(this.V)-distTo[o.V]-h(o.V);
        }
    }

    public MazeAStarPath(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        tx = targetX;
        ty = targetY;
        distTo[s] = 0;
        edgeTo[s] = s;
        for(int i=0;i<maze.N()*maze.N();i++){
            if(i!=s)distTo[i]=0x7fffffff;
        }
    }

    /** Estimate of the distance from v to the target. */
    private int h(int v) {
        //System.out.println(Math.abs(maze.toX(v) - tx) + Math.abs(maze.toY(v) - ty));
        return   Math.abs(maze.toX(v) - tx) + Math.abs(maze.toY(v) - ty);
    }

    /** Finds vertex estimated to be closest to target. */
    private int findMinimumUnmarked() {
        return -1;
        /* You do not have to use this method. */
    }

    /** Performs an A star search from vertex s. */
    private void astar(int s) {
        // TODO
        MinPQ<SearchNode> q =new MinPQ<>();
        q.insert(new SearchNode(s));
        marked[s] = true;
        announce();
        while (!q.isEmpty()) {
            SearchNode X = q.delMin();
            if (marked[t]) {
                return;
            }
            for (Integer i : maze.adj(X.V)) {
                if(!marked[i]) {
                    distTo[i] = distTo[X.V] + 1;
                    edgeTo[i] = X.V;
                    marked[i] = true;
                    announce();
                    if (marked[t]) {
                        return;
                    }
                    q.insert(new SearchNode(i));
                }
            }
        }


    }

    @Override
    public void solve() {
        astar(s);
    }


}

