public class ArrayDeque<T>{
    private T[] items;
    private int size;
    private int first;
    private int last;

    /** Creat a new ArrayDeque*/
    public ArrayDeque(){
        first=1;
        last=-1;
        size=0;
        items = (T[]) new Object[8];
    }

/**
    public ArrayDeque(T item){
        first=0;
        last=0;
        size = 1;
        items = (T[]) new Object[8];
        items[first] = item;
        System.out.println(items.length);
    }

    public ArrayDeque(ArrayDeque other){
        items = (T[]) new Object[other.size];
        System.arraycopy(other,0,items,0,size);
    }*/

    /**Resizing the size of ArrayDeque*/
    private void resizeEnlarge(){
        T[] temp = (T[]) new Object[size*2];
        System.arraycopy(items,0,temp,0,last+1);
        System.arraycopy(items,first,temp,temp.length-items.length+first,size-last-1);
        first=temp.length-items.length+first;
        items=temp;
    }

    private void resizeReduce(){
        double ssize=size;
        double ratio=ssize/ items.length;
        while(ratio<0.25&&items.length>=16){
            T[] temp = (T[]) new Object[size/2];
            System.arraycopy(items,0,temp,0,last+1);
            System.arraycopy(items,first,temp,first-temp.length-items.length,size-last-1);
            first=first-temp.length-items.length;
            items=temp;
        }
    }

    /** Adds an item of type T to the front of the deque.*/
    public void addFirst(T item){
        if(size>= items.length) resizeEnlarge();
    if(first==0){
        first= items.length;
    }
    if(first==1)last=0;
    first-=1;
    items[first]=item;
    size+=1;
    }

    /** Adds an item of type T to the back of the deque.*/
    public void addLast(T item){
        if(size>= items.length) resizeEnlarge();
    if(last==-1)first=0;
    last+=1;
    items[last]=item;
    size+=1;
    }

    /** Returns true if deque is empty, false otherwise.*/
    public boolean isEmpty(){
    return size==0;
    }

    /** Returns the number of items in the deque.*/
    public int size(){
    return size;
    }

    /** Prints the items in the deque from first to last,
     * separated by a space.
     * Once all the items have been printed,
     * print out a new line.*/
    public void printDeque(){
        if(size==0){
            System.out.print("\n");
            return;
        }
    for(int i= first;i< items.length;i++)
        System.out.print(items[i]+" ");
    for(int i=0;i<=last;i++)
        System.out.print(items[i]+" ");
    System.out.print("\n");
    }

    /** Removes and returns the item at the front of the deque.
     * If no such item exists, returns null.*/
    public T removeFirst(){
        if(size==0)return null;
        T temp=items[first];
        items[first]=null;
        if(first==size-1)first=0;
        else first+=1;
        size-=1;
        resizeReduce();
        return temp;
    }

    /** Removes and returns the item at the back of the deque.
     * If no such item exists, returns null.*/
    public T removeLast(){
        if(size==0)return null;
        T temp=items[last];
        items[last]=null;
        last-=1;
        size-=1;
        resizeReduce();
        return temp;
    }

    /** Gets the item at the given index,
     * where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null. Must not alter the deque!*/
    public T get(int index){
        if(index>size-1)
            return null;
        return items[(first+index)%size];
    }


}