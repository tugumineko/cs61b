import edu.princeton.cs.algs4.MinPQ;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class BinaryTrie implements Serializable {

    private BinaryTrie(){};

    private Node root;

    private class Node implements Comparable<Node>,Serializable{
        private final char ch;
        private final int freq;
        private final Node left,right;

        Node(char ch,int freq,Node left,Node right){
            this.ch = ch;
            this.freq = freq;
            this.left = left;
            this.right = right;
        }

        private boolean isLeaf() {
            assert ((left == null) && (right == null) || ((left != null) && (right != null)));
            return  (right == null)&&(left == null) ;
        }

        @Override
        public int compareTo(Node o) {
            return this.freq - o.freq;
        }
    }

    /** Given a frequency table which maps symbols of type V to their relative frequencies,
     *  the constructor should build a Huffman decoding trie
     *  according to the procedure discussed in class.
     */
    public BinaryTrie(Map<Character,Integer> frequencyTable) {
        MinPQ<Node> pq = new MinPQ<Node>();
        for(Character c : frequencyTable.keySet()){
            pq.insert(new Node(c,frequencyTable.get(c),null,null));
        }

        while(pq.size()>1) {
            Node left = pq.delMin();
            Node right = pq.delMin();
            Node parent = new Node('\0', left.freq + right.freq, left, right);
            pq.insert(parent);
        }

        root = pq.delMin();

    }

    public Match longestPrefixMatch(BitSequence querySequence){
        Node ptr = root;
        int n = 0;
        for(int i=0;i< querySequence.length();i++){
            if(!ptr.isLeaf()) {
                if (querySequence.bitAt(i) == 0) {
                    ptr = ptr.left;
                } else if (querySequence.bitAt(i) == 1) {
                    ptr = ptr.right;
                }
                n+=1;
            }
            else {
                return new Match(querySequence.firstNBits(n),(Character) ptr.ch);
            }
        }
        return new Match(querySequence.firstNBits(n),(Character) ptr.ch);
    }

    public Map<Character,BitSequence>  buildLookupTable(){
        Node ptr = root;
        Map<Character, BitSequence> map = new HashMap<>();
        buildCode(map,ptr,new BitSequence());
        return map;
    }

    private static void buildCode(Map<Character,BitSequence> map, Node ptr, BitSequence bits) {
        if (!ptr.isLeaf()) {
            buildCode(map, ptr.left, bits.appended(0) );
            buildCode(map, ptr.right, bits.appended(1));
        }
        else {
            map.put(ptr.ch,bits);
        }
    }

}
