package lab9;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Implementation of interface Map61B with BST as core data structure.
 *
 * @author Your name here
 */
public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private class Node {
        /* (K, V) pair stored in this Node. */
        private K key;
        private V value;

        /* Children of this Node. */
        private Node left;
        private Node right;

        private Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    private Node root;  /* Root node of the tree. */
    private int size; /* The number of key-value pairs in the tree */

    /* Creates an empty BSTMap. */
    public BSTMap() {
        this.clear();
    }

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /** Returns the value mapped to by KEY in the subtree rooted in P.
     *  or null if this map contains no mapping for the key.
     */
    private V getHelper(K key, Node p) {
        if(p == null) return null;
        int cmp = key.compareTo(p.key);
        if(cmp > 0)return getHelper(key,p.right);
        else if(cmp < 0)return getHelper(key,p.left);
        else return p.value;
    }

    /** Returns the value to which the specified key is mapped, or null if this
     *  map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        Node p = root ;
        return getHelper(key,p);
    }

    /** Returns a BSTMap rooted in p with (KEY, VALUE) added as a key-value mapping.
      * Or if p is null, it returns a one node BSTMap containing (KEY, VALUE).
     */
    private Node putHelper(K key, V value, Node p) {
        if(p == null){
            size++;
            return new Node(key,value);
        }
        int cmp = key.compareTo(p.key);
        if(cmp > 0)p.right=putHelper(key,value,p.right);
        else if(cmp<0)p.left=putHelper(key,value,p.left);
        else p.value = value;
        return p;
    }

    /** Inserts the key KEY
     *  If it is already present, updates value to be VALUE.
     */
    @Override
    public void put(K key, V value) {
        if(key==null)throw new IllegalArgumentException("calls put() with a null key");
        if(value==null){
            remove(key);
            return;
        }
        root = putHelper(key, value, root);
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size () {
        return size;
    }

    //////////////// EVERYTHING BELOW THIS LINE IS OPTIONAL ////////////////

    /* Returns a Set view of the keys contained in this map. */
    private void keySetHelp(Set<K> set,Node p){
        if(p==null)return;
        set.add(p.key);
        keySetHelp(set,p.left);
        keySetHelp(set,p.right);
    }

    @Override
    public Set<K> keySet() {
        Set<K> set = new HashSet<>();
        keySetHelp(set,root);
        return set;
    }

    /** Removes KEY from the tree if present
     *  returns VALUE removed,
     *  null on failed removal.
     */
    private boolean noChild(Node p){
        return (p.left==null)&&(p.right==null);
    }

    private boolean oneChild(Node p){
        return (p.left==null)^(p.right==null);
    }

    private boolean twoChildren(Node p){
        return (p.right!=null)&&(p.left!=null);
    }

    private Node findNode(K key,Node p) {
        if(p == null) return null;
        int cmp = key.compareTo(p.key);
        if(cmp > 0){
            return findNode(key,p.right);
        }
        else if(cmp < 0){
            return findNode(key,p.left);
        }
        else return p;
    }

    private Node findFather(K key,Node p,Node father) {
        if(root==null||root.left==p||root.right==p)return root;
        if(p == null) return null;
        int cmp = key.compareTo(p.key);
        if(cmp > 0){
            father = p;
            return findFather(key,p.right,father);
        }
        else if(cmp < 0){
            father = p;
            return findFather(key,p.left,father);
        }
        else return father;
    }

    @Override
    public V remove(K key) {
        Node p = findNode(key,root);
        if(p==null)return null;
        size--;
        V value = p.value;
        if(noChild(p)){
            p = null;
        }
        Node pf = findFather(key,root,null);
        if(oneChild(p)){
            if(p.left!=null) {
                if (pf.left == p) {
                    pf.left = p.left;
                    p = null;
                }
                else {
                    pf.right = p.left;
                    p = null;
                }
            }
            else{
                if(pf.left == p){
                    pf.left = p.right;
                    p = null;
                }
                else {
                    pf.right = p.right;
                    p = null;
                }
            }
        }
        if(twoChildren(p)){
            Node newp = p.left;
            while(newp.right!=null){
                newp=newp.right;
            }
            newp.left=p.left;
            newp.right=p.right;
            if(pf.left==p){
                pf.left=newp;
                p=null;
            }
            if(pf.right==p){
                pf.right=newp;
            }
        }
        return value;
    }

    /** Removes the key-value entry for the specified key only if it is
     *  currently mapped to the specified value.  Returns the VALUE removed,
     *  null on failed removal.
     **/
    @Override
    public V remove(K key, V value) {
        if(get(key)!=value)return null;
        else return remove(key);
    }

    @Override
    public Iterator<K> iterator() {
            Set<K> set = keySet();
            return set.iterator();
    }
}
