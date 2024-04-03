package lab11.graphs;

/**
 *  @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private boolean cycleFound = false;
    public MazeCycles(Maze m) {
        super(m);
    }

    @Override
    public void solve() {
        // TODO: Your code here!
        distTo[0] = 0;
        edgeTo[0] = 0;
        dfs(0);

    }

    // Helper methods go here
    private void dfs(int v) {
        marked[v] = true;

        announce();
        if (cycleFound) {
            return;
        }

        for (int w : maze.adj(v)) {
            if (marked[w]&&edgeTo[v]!=w) {
                edgeTo[w]=v;
                cycleFound = true;
                announce();
            }
            if (!marked[w]) {
                edgeTo[w] = v;
                distTo[w] = distTo[v] + 1;
                dfs(w);
                if (cycleFound) {
                    return;
                }
            }
        }
    }
}

