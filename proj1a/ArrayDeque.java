public class ArrayDeque<T>{
    private T[] items;
    private int size;
    private int first;
    private int last;

    /** Creat a new ArrayDeque*/
    public ArrayDeque(){
        items = (T[]) new Object[8];
        first=items.length-1;
        last=0;
        size=0;

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
        T[] temp = (T[]) new Object[items.length*2];
        for(int i=0;i<size;i++)
            temp[i]=items[(first+1+i)%items.length];
        first=temp.length-1;
        last=size;
        items=temp;
        temp=null;
    }

    private void resizeReduce(){
        T[] temp = (T[]) new Object[items.length/2];
        while(4*size<items.length &&items.length>=16){
            for(int i=0;i<size;i++)
                temp[i]=items[(first+1+i)%items.length];
            first=temp.length-1;
            last=size;
            items=temp;
            temp=null;
        }
    }

    /** Adds an item of type T to the front of the deque.*/
    public void addFirst(T item){
        if(size>= items.length) resizeEnlarge();
        items[first]=item;
        first= first==0?items.length-1:first-1;
        size+=1;
    }

    /** Adds an item of type T to the back of the deque.*/
    public void addLast(T item){
        if(size>= items.length) resizeEnlarge();
        items[last]=item;
        last=(last+1)%items.length;
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
    for(int i= 0;i< size;i++)
        System.out.print(items[(first+i+1)%items.length]+" ");
    System.out.print("\n");
    }

    /** Removes and returns the item at the front of the deque.
     * If no such item exists, returns null.*/
    public T removeFirst(){
        if(size==0)return null;
        first=(first+1)%items.length;
        T temp=items[first];
        items[first]=null;
        size-=1;
        resizeReduce();
        return temp;
    }

    /** Removes and returns the item at the back of the deque.
     * If no such item exists, returns null.*/
    public T removeLast(){
        if(size==0)return null;
        last=last==0?items.length-1:last-1;
        T temp=items[last];
        items[last]=null;
        size-=1;
        resizeReduce();
        return temp;
    }

    /** Gets the item at the given index,
     * where 0 is the front, 1 is the next
     * item, and so forth.
     * If no such item exists, returns null. Must not alter the deque!*/
    public T get(int index){
        if(index>items.length-1)
            return null;
        return items[(first+1+index)% items.length];
    }


}