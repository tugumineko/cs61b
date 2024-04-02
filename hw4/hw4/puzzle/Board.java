package hw4.puzzle;

import edu.princeton.cs.algs4.Queue;


public class Board implements WorldState{

    private int size;
    private int[][] tiles;
    private int estimatedDist;
    private static final int BLANK = 0;

    /**
     * Constructs a board from an N-by-N array of tiles
     * where tiles[i][j] = tile at row i, column j.
     * */
    public  Board(int[][] tiles){
        size = tiles.length;
        this.tiles = new int[size][size];
        estimatedDist = -1;
        for(int i=0;i<size;i++) {
            System.arraycopy(tiles[i], 0, this.tiles[i], 0, size);
        }
    }

    /**
     * Returns value of tile at row i, column j
     * (or 0 if blank)
     * */
    private boolean inmap(int x,int y){
        return x>=0&&x<=size-1&&y>=0&&y<=size-1;
    }

    public int tileAt(int i,int j){
        if(!inmap(i,j))throw new java.lang.IndexOutOfBoundsException();
        if(tiles[i][j]==BLANK)return 0;
        return tiles[i][j];
    }

    public int size(){
        return size;
    }

    //cite from "https://joshh.ug/neighbors.html"
    public Iterable<WorldState> neighbors() {
        Queue<WorldState> neighbors;
        neighbors = new Queue<>();
        int hug = size();
        int bug = -1;
        int zug = -1;
        for (int rug = 0; rug < hug; rug++) {
            for (int tug = 0; tug < hug; tug++) {
                if (tileAt(rug, tug) == BLANK) {
                    bug = rug;
                    zug = tug;
                }
            }
        }
        int[][] ili1li1 = new int[hug][hug];
        for (int pug = 0; pug < hug; pug++) {
            for (int yug = 0; yug < hug; yug++) {
                ili1li1[pug][yug] = tileAt(pug, yug);
            }
        }
        for (int l11il = 0; l11il < hug; l11il++) {
            for (int lil1il1 = 0; lil1il1 < hug; lil1il1++) {
                if (Math.abs(-bug + l11il) + Math.abs(lil1il1 - zug) - 1 == 0) {
                    ili1li1[bug][zug] = ili1li1[l11il][lil1il1];
                    ili1li1[l11il][lil1il1] = BLANK;
                    Board neighbor = new Board(ili1li1);
                    neighbors.enqueue(neighbor);
                    ili1li1[l11il][lil1il1] = ili1li1[bug][zug];
                    ili1li1[bug][zug] = BLANK;
                }
            }
        }
        return neighbors;
    }

    public int hamming(){
        int number = 0;
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                if(tiles[i][j]==0)continue;
                int index = (i)*size+j+1;
                if(tiles[i][j]==index)number++;
            }
        }
        return number;
    }

    public int manhattan(){
        int number = 0;
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                if(tiles[i][j]==0)continue;
                int ii = (tiles[i][j]-1)/(size);
                int jj = tiles[i][j]-ii*size-1;
                number+=Math.abs(i-ii);
                number+=Math.abs(j-jj);
            }
        }
        //System.out.println(number);
        return number;
    }

    public int estimatedDistanceToGoal(){
        if(estimatedDist==-1)
            estimatedDist =  manhattan();
        return estimatedDist;
    }

    public boolean equals(Object y){
        if (y == this) return true;
        if (y == null || this.getClass() != y.getClass()) {
            return false;
        }
        Board other = (Board) y;
        if (this.size != other.size) return false;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (this.tiles[i][j] != other.tiles[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    /** Returns the string representation of the board.
      * Uncomment this method. */
    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i,j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

}
