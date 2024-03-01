import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {



    public void testStudentArrayDeque() {
        /** array必须创建在函数中，如果创建在函数外就是使用堆内存，当size==0时就被内存管理器回收了下次就会返回空指针 */
        StudentArrayDeque<Integer> arrayDeque = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> arrayDequeSolution = new ArrayDequeSolution<>();

        String log="";
        for(int i=1;i<=1000;i++) {
            if(arrayDequeSolution.isEmpty()) {
                int x = StdRandom.uniform(2);
                if (x == 0) {
                    int r = StdRandom.uniform(100);
                    log = log + "addFirst(" + r + ")\n";
                    arrayDeque.addFirst(r);
                    arrayDequeSolution.addFirst(r);
                } else {
                    int r = StdRandom.uniform(100);
                    log = log + "addLast(" + r + ")\n";
                    arrayDeque.addLast(r);
                    arrayDequeSolution.addLast(r);
                }
            }
            else{
                int x = StdRandom.uniform(4);
                int r = StdRandom.uniform(100);
                int numTest = 0;
                int numStd = 0;
                switch(x){
                    case 0 :
                        log = log + "addFirst(" + r + ")\n";
                        arrayDeque.addFirst(r);
                        arrayDequeSolution.addFirst(r);
                        break;
                    case 1 :
                        log = log + "addLast(" + r + ")\n";
                        arrayDeque.addLast(r);
                        arrayDequeSolution.addLast(r);
                        break;
                    case 2 :
                        log = log + "removeFirst()\n";
                        numTest = arrayDeque.removeFirst();
                        numStd = arrayDequeSolution.removeFirst();
                        break;
                    case 3 :
                        log = log + "removeLast()\n";
                        numTest = arrayDeque.removeLast();
                        numStd = arrayDequeSolution.removeLast();
                        break;
                    default:
                }
                assertEquals(log,numTest,numStd);
            }
        }

    }

    public static void main(String[] args) {
        jh61b.junit.TestRunner.runTests(TestArrayDequeGold.class);
    }
}