import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class HuffmanEncoder {
    public static Map<Character,Integer> buildFrequencyTable(char[] inputSymbols){
        Map<Character,Integer> map = new HashMap<>();
        for(char c : inputSymbols){
            if(!map.containsKey(c)){
                map.put(c,1);
            }
            else {
                Integer num = map.get(c);
                num+=1;
                map.put(c,num);
            }
        }
        return map;
    }

    /** The main method should open the file
     *  given as the 0th command line argument (args[0]),
     *  and write a new file with the name args[0] + ".huf"
     *  that contains a huffman encoded version of the original file.
     *  For example java HuffmanEncoder watermelonsugar.txt should generate a new Huffman encoded version of watermelonsugar.txt that contains watermelonsugar.txt.huf.
     */
    public static void main(String[] args){
        char[] chars = FileUtils.readFile(args[0]);
        String output = args[0];
        output = output + ".huf";
        Map<Character,Integer> map = buildFrequencyTable(chars);
        //System.out.println(map);
        BinaryTrie trie = new BinaryTrie(map);
        Map<Character,BitSequence> encodingMap = trie.buildLookupTable();
        List<BitSequence> bitList = new LinkedList<>();
        for(char c : chars){
            bitList.add(encodingMap.get(c));
        }
        BitSequence bits = BitSequence.assemble(bitList);

        ObjectWriter ow = new ObjectWriter(output);
        ow.writeObject(bits);
        ow.writeObject(trie);
    }

}
