import java.util.Map;
import java.util.TreeMap;

public class Trie {

     class Node{
        private char value;
        public Map<Character, Node> map;

        private boolean valid;

        public Node pre;

        public Node(Character value){
            map = new TreeMap<Character,Node>();
            valid = false;
            this.value = value;
        }

        public void addNode(Character value){
            Node node = new Node(value);
            node.pre = this;
            map.put(value,node);
        }

        public void setValid(){
            this.valid = true;
        }

        public boolean isValid(){return this.valid;}

        public char value(){return value;}
    }

    public Node root =  new Node(null);

    public void putString(String word){
       if(word.length()<=2)return;
       Node ptr = root;
       for(int i=0;i<word.length();i++){
           Character c = word.charAt(i);
           ptr.addNode(c);
           if(i==word.length()-1) {
               ptr.setValid();
           }
           ptr = ptr.map.get(c);
       }
    }

}
