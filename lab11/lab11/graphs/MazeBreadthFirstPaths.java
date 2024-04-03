package lab11.graphs;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 *  @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */

    private int source;
    private int target;
    private Maze maze;


    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        // Add more variables here!
        maze = m;
        source = maze.xyTo1D(sourceX,sourceY);
        target = maze.xyTo1D(targetX,targetY);
        distTo[source] = 0;
        edgeTo[source] = source;
    }

    /** Conducts a breadth first search of the maze starting at the source. */
    private void bfs() {
        // TODO: Your code here. Don't forget to update distTo, edgeTo, and marked, as well as call announce()
        Queue<Integer> q =new ArrayDeque();
        q.add(source);
        marked[source] = true;
        announce();
        while (!q.isEmpty()) {
            int X = q.remove();
            if (marked[target]) {
                return;
            }
            for (Integer i : maze.adj(X)) {
                if(!marked[i]) {
                    distTo[i] = distTo[X] + 1;
                    edgeTo[i] = X;
                    marked[i] = true;
                    announce();
                    if (marked[target]) {
                        return;
                    }
                    q.add(i);
                }
            }
        }
    }


    @Override
    public void solve() {
         bfs();
    }
}

