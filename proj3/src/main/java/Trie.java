import java.util.*;

class Trie{
    private class trieNode{
        private List<trieNode> children;
        private Boolean terminal = false;

        private String value;

        private trieNode(List<trieNode> children, Boolean terminal, String value){
            this.children = children;
            this.terminal = terminal;
            this.value = value;
        }

    }

    private trieNode root=new trieNode(new LinkedList<>(),false,null);

    trieNode search(String key){
        trieNode node = root;
        for(int i=0;i<key.length();i++){
            if(node.children==null)return null;
            for(trieNode child: node.children){
                if(child.value.charAt(i)==key.charAt(i)||child.value.toUpperCase().charAt(i)==key.charAt(i)){
                    node = child;
                    break;
                }
            }
        }
        return node;
    }

    List<String> bfs(trieNode node){
        List<String> list = new ArrayList<>();
        Queue<trieNode> q = new ArrayDeque<trieNode>() ;
        q.add(node);
        while(!q.isEmpty()){
            trieNode top = q.remove();
            if(node.terminal)list.add(top.value);

            if(node.children==null)continue;
            for(trieNode child: top.children){
                q.add(child);
            }
        }
        return list;
    }

    void insert(String key, String v){
        trieNode node = root;
        String s = "";
        for(int i=0;i<key.length();i++){
            s += key.charAt(i);
            if(node.children==null){
                node.children = new ArrayList<>();
                if(i==key.length()-1)node.children.add(new trieNode(null,true,s));
                else node.children.add(new trieNode(null,false,s));
            }
            for(trieNode child: node.children){
                if(child.value.charAt(i)==key.charAt(i)||child.value.toUpperCase().charAt(i)==key.charAt(i)){
                    return;
                }
            }
        }
    }
}

