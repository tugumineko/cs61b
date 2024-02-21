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

        ArrayDeque AList = new ArrayDeque();
        AList.addFirst(14);
        AList.addLast(16);
        AList.addFirst(13);
        AList.addLast(4);
        AList.addLast(3);
        AList.addLast(2);
        AList.addLast(1);
        AList.addLast(17);
        AList.addLast(12);
        AList.addLast(17);
        for(int i= 0;i<10000;i++)
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
        System.out.println();


    }


}