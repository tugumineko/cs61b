public class Test{
    public static void main(String[] args){
        LinkedListDeque DLList=new LinkedListDeque();
        DLList.addFirst(14);
        DLList.addLast(16);
        DLList.addFirst(13);
        DLList.addLast(17);
        DLList.removeLast();
        DLList.removeFirst();
        if(!DLList.isEmpty()){
            System.out.print(DLList.get(2)+" "+DLList.getRecursive(2)+" " +DLList.size()+" ");
            DLList.printDeque();
        }
        System.out.println();

        ArrayDeque ArrayDeque = new ArrayDeque();
        ArrayDeque.addLast(0);
        ArrayDeque.get(0) ;
        ArrayDeque.addFirst(2);
        ArrayDeque.addFirst(3);
        ArrayDeque.addFirst(4);
        ArrayDeque.addLast(5);
        ArrayDeque.addFirst(6);
        ArrayDeque.addLast(7);
        ArrayDeque.addLast(8);
        ArrayDeque.removeFirst()  ;
        ArrayDeque.addFirst(10);
        ArrayDeque.get(4)     ;
        ArrayDeque.get(7)      ;
        ArrayDeque.addLast(13);
        ArrayDeque.removeFirst()    ;
        ArrayDeque.get(7)    ;
        ArrayDeque.removeFirst()   ;
        ArrayDeque.get(0)      ;
        ArrayDeque.removeLast()      ;
        ArrayDeque.removeLast()     ;
        ArrayDeque.removeFirst()    ;
        ArrayDeque.removeLast()      ;
        ArrayDeque.removeLast()     ;
    }


}