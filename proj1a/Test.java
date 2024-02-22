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
        for(int i=0;i<65536;i++)
        ArrayDeque.addFirst(0); ;
    }


}