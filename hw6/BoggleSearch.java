import java.util.*;

public class BoggleSearch {

    private char[][] map;
    public int n;
    public int m;

    private int[] dx = {0,0,1,-1,1,-1,1,-1};
    private int[] dy = {1,-1,0,0,1,1,-1,-1};

    private boolean[][] vis;

    private Trie t;

    private Trie.Node ptr;

    private Queue pq;

    public void setTrie(Trie t){this.t = t;}

    public Queue priorityQueue(){return pq;}

    public void generateMatrix(String boardFilePath){
        In in = new In(boardFilePath);
        String[] strings =  in.readAllLines();

        for(int i=0;i<strings.length-1;i++){
            if(strings.length==1)break;
            if(strings[i].length()!=strings[i+1].length())
                throw new IllegalArgumentException();
        }
        char[][] map = new char[strings.length][strings[0].length()];
        for(int i=0;i<strings.length;i++){
            map[i] = strings[i].toCharArray();
        }
        vis = new boolean[n][m];
        pq = new PriorityQueue<String>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        this.map =  map;
    }

    private boolean inmap(int x,int y){
        return x>=0&&y>=0&&x<n&&y<m;
    }

    public void setDFS(int x,int y){
        vis = new boolean[n][m];
        vis[x][y] = true;
        ptr = t.root;
    }

    //vis should be clear before dfs everytime for different (x,y)
    public void dfs(int x,int y){
        // the trie with map[x][y] if it is valid,then put it into priority queue)
        // if the trie don't have the child with map[x][y], then it should make vis[x][y] false and return.
        if(ptr.isValid()){
                Trie.Node temp = ptr;
                String s = "";
                while(temp != null ) {
                    s += temp.value();
                    temp = temp.pre;
                }
                pq.add(s);
        }

        if(ptr.map.containsKey(map[x][y])){
            ptr = ptr.map.get(map[x][y]);
        }
        else {
            vis[x][y] = false;
            return;
        }

        for(int i=0;i<8;i++){
            int tx = x + dx[i];
            int ty = y + dy[i];
            if(inmap(tx,ty)&&!vis[tx][ty]){
                vis[tx][ty] = true;
                dfs(tx,ty);
            }
        }

    }

}
