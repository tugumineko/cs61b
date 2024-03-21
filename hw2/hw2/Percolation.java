package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private static class Position{
        private int x;
        private int y;

        public Position(int x,int y){
            this.x = x;
            this.y = y;
        }
    }

    private int eage;
    private int number;

    //virtual node(an important method)
    private int top;
    private int bottom;

    private WeightedQuickUnionUF  UF;
    private WeightedQuickUnionUF UFexcludeBottom;

    private boolean[][] vis;

    public static final int[] dx={0,0,1,-1};
    public static final int[] dy={1,-1,0,0};

    public Percolation(int N){
        eage = N;
        number = 0;
        UF = new WeightedQuickUnionUF(N*N+2);
        UFexcludeBottom = new WeightedQuickUnionUF(N*N+1);
        top = N*N;
        bottom = N*N+1;
        vis = new boolean[N][N];
    }

    private int convertToId(Position p){
        return p.x*eage+p.y;
    }

    private boolean isInGrid(Position p){
        return p.x>=0&&p.x<eage&&p.y>=0&&p.y<eage;
    }

    public void open(int row,int col){
        Position p = new Position(row,col);
        if(!isInGrid(p))throw new java.lang.IndexOutOfBoundsException("open() is outside its prescribed range!");
        if(vis[p.x][p.y])return;
        vis[p.x][p.y]=true;
        number++;

        if(p.x==0){
            UF.union(convertToId(p),top);
            UFexcludeBottom.union(convertToId(p),top);
        }

        if(p.x==eage-1){
            UF.union(convertToId(p),bottom);
        }

        for(int i=0;i<4;i++){
            int tx=p.x+dx[i];
            int ty=p.y+dy[i];
            Position tp = new Position(tx,ty);
            if(isInGrid(tp)&& vis[tx][ty])
            {
                UF.union(convertToId(p),convertToId(tp));
                UFexcludeBottom.union(convertToId(p),convertToId(tp));
            }
        }
    }

    public boolean isOpen(int row,int col){
        Position p = new Position(row,col);
        if(!isInGrid(p))throw new java.lang.IndexOutOfBoundsException("isOpen() is outside its prescribed range!");
        return vis[p.x][p.y];
    }

    public boolean isFull(int row,int col){
        Position p =new Position(row,col);
        if(!isInGrid(p))throw new java.lang.IndexOutOfBoundsException("isFull() is outside its prescribed range!");
        return UFexcludeBottom.connected(convertToId(p),top);
    }

    public int numberOfOpenSites(){
        return number;
    }

    public boolean percolates() {
        //percolates have nothing to position,so don't need to worry washback.
        return UF.connected(top,bottom);
    }

    public static void main(String[] args){

    }
}
