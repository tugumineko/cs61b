public class LinkedListDeque<T> implements Deque<T>{
    private class Deque  {
        public T item;
        public Deque prev;
        public Deque next;

        public Deque(T i, Deque p, Deque n) {
            item = i;
            prev = p;
            next = n;
        }
    }
        private Deque firstSentinel;
        private Deque lastSentinel;
        private int size;

        /** Creates an empty LinkedListDeque*/
        public LinkedListDeque(){

            firstSentinel= new Deque(null,null,null);
            lastSentinel = new Deque(null,null,null);
            firstSentinel.next=lastSentinel;
            lastSentinel.prev=firstSentinel;
            size = 0;
        }

        /**
        public LinkedListDeque(T i){
            firstSentinel= new Deque(null,null,null);
            lastSentinel = new Deque(null,null,null);
            firstSentinel.next=new Deque(i,firstSentinel,lastSentinel);
            lastSentinel.prev=firstSentinel.next;
            size =1;
        }

        /** Creates a deep copy of other*/
    /**
        public LinkedListDeque(LinkedListDeque other) {
            firstSentinel=new Deque(null,null,null);
            Deque ptr = other.firstSentinel.next;
            Deque res=firstSentinel.next;
            Deque resp=firstSentinel;
            size=0;
            while(ptr!=other.lastSentinel)
            {
                res=new Deque(ptr.item,resp,null);
                res=res.next;
                resp=resp.next;
                ptr=ptr.next;
                size+=1;
            }
            lastSentinel=new Deque(null,res,null);
        }
*/
        /** Adds x to the front of the list*/
        @Override
        public void addFirst(T x){
            firstSentinel.next = new Deque(x,firstSentinel,firstSentinel.next);
            firstSentinel.next.next.prev=firstSentinel.next;
            size +=1;
        }

        /** Adds x to the last of the list*/
        @Override
        public void addLast(T x){
            lastSentinel.prev = new Deque(x,lastSentinel.prev,lastSentinel);
            lastSentinel.prev.prev.next=lastSentinel.prev;
            size+=1;
        }

        /** Returns true if deque is empty,false otherwise */
        @Override
        public boolean isEmpty(){
            return size==0;
        }

        /** Returns the num=ber of items in the deque*/
        @Override
        public int size(){
            return size;
        }

        /** Prints the items in the deque from first to last
         * separated by a space
         * Once all the items have been printed,
         * print out a new line.*/
        @Override
        public void printDeque(){
        if(size==0)System.out.println("No items,This list is empty!");
        Deque ptr=firstSentinel.next;
        while(ptr!=lastSentinel){
            System.out.print(ptr.item+" ");
            ptr=ptr.next;
        }
        System.out.print("\n");
        }

        /** Removes and returns the items at the back of the Deque
         *  If no such item exists,return null*/
        @Override
        public T removeFirst() {
            if (firstSentinel.next.item == null) return null;
            firstSentinel.next = firstSentinel.next.next;
            T temp = firstSentinel.next.prev.item;
            firstSentinel.next.prev.item = null;
            firstSentinel.next.prev.next = null;
            firstSentinel.next.prev.prev = null;
            firstSentinel.next.prev = firstSentinel;
            size-=1;
            return temp;
        }

        /** Removes and returns the item at the back of the deque.
         * If no such items,return null*/
        @Override
        public T removeLast() {
            if (lastSentinel.prev.item == null) return null;
            lastSentinel.prev = lastSentinel.prev.prev;
            T temp = lastSentinel.prev.next.item;
            lastSentinel.prev.next.item = null;
            lastSentinel.prev.next.next = null;
            lastSentinel.prev.next.prev = null;
            lastSentinel.prev.next = lastSentinel;
            size-=1;
            return temp;
        }

        /** Gets the item at the given index,
         * where 0 is the front,1 is the next item,and so forth
         * If no such items exists ,returns null.
         * Must not alter the deque!*/
        @Override
        public T get(int index) {
            Deque ptr = firstSentinel.next;
            int number = 0;
            while (ptr != lastSentinel) {
                if (number == index) return ptr.item;
                number++;
                ptr = ptr.next;
            }
            return null;
        }

        /** Same as get.But uses recursion*/
        private T getRecursionHelp(Deque start,int index) {
            if (index == 0) return start.item;
            else return getRecursionHelp(start.next, index - 1);
        }

        public T getRecursive(int index){
            if(index>=size)return null;
            Deque ptr = firstSentinel.next;
            return getRecursionHelp(ptr,index);
        }
}



