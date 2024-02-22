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
        ArrayDeque.addFirst(3);
        ArrayDeque.removeLast();
        /**for(int i= 0;i<10000;i++)
        {
            AList.addLast(17);
            AList.addFirst(1);
        }
        for(int i=0;i<10000;i++)
        {
            System.out.print(AList.removeLast()+" ");
            AList.removeFirst();
        }
        AList.removeLast();
        System.out.println();
        if(!AList.isEmpty()){
            System.out.print(AList.get(2)+" " +AList.size()+" ");
            AList.printDeque();
        }
        System.out.println();*/


    }


}