public class OffByN implements CharacterComparator{
    private int N;

    public OffByN(int n){
        N=n;
    }




    @Override
    public boolean equalChars(char x, char y){
        if(x<97||x>122||y<97||y>122)return false;
        return x - y == N || x - y == -N;
    }
}
