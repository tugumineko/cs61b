public class HuffmanDecoder {

    /** The main method should open the file given as the 0th command line argument (args[0]), decode it,
     *  and write a new file with the name given as args[1].
     *  For example java HuffmanDecoder watermelonsugar.txt.huf originalwatermelon.txt should decode the contents of watermelonsugar.txt.huf and write them into originalwatermelon.txt.
     */
    public static void main(String[] args){
        String input = args[0];
        String output = args[1];
        ObjectReader or = new ObjectReader(input);
        BitSequence bits = (BitSequence) or.readObject();
        BinaryTrie trie = (BinaryTrie) or.readObject();
        StringBuilder s = new StringBuilder();
        while(bits.length()!=0) {
            Match match = trie.longestPrefixMatch(bits);
            bits = bits.allButFirstNBits(match.getSequence().length());
            s.append(match.getSymbol());
        }
        FileUtils.writeCharArray(args[1],s.toString().toCharArray());
    }


}
