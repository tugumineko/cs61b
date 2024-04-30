import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Boggle {
    
    // File path of dictionary file
    static String dictPath = "words.txt";

    /**
     * Solves a Boggle puzzle.
     * 
     * @param k The maximum number of words to return.
     * @param boardFilePath The file path to Boggle board file.
     * @return a list of words found in given Boggle board.
     *         The Strings are sorted in descending order of length.
     *         If multiple words have the same length,
     *         have them in ascending alphabetical order.
     */
    public static List<String> solve(int k, String boardFilePath) {
        if(k<=0)throw new IllegalArgumentException();

        //set dictionary trie
        Trie t = new Trie();
        In inDic = new In(dictPath);

        while(inDic.hasNextLine()){
            t.putString(inDic.readLine());
        }

        //initialize with the boardFile
        BoggleSearch boggleSearch = new BoggleSearch();
        boggleSearch.setTrie(t);
        boggleSearch.generateMatrix(boardFilePath);

        //search as the board file
        for(int i=0;i<boggleSearch.n;i++){
            for(int j=0;j<boggleSearch.m;j++){
                boggleSearch.setDFS(i,j);
                boggleSearch.dfs(i,j);
            }
        }
        Queue<String> pq = boggleSearch.priorityQueue();
        List<String> ret = new ArrayList<String>();
        for(int i=0;i<k;i++){
            ret.add(pq.remove());
        }
        return ret;
    }

}
