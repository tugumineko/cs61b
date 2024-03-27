package hw3.hash;

import java.util.ArrayList;
import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        int[] numberInbucket = new int[M];
        //List<Oomage>[] hashOomage = new ArrayList[M];
        for(Oomage oomage:oomages){
            int bucketNumber = (oomage.hashCode()&0x7fffffff)%M;
            numberInbucket[bucketNumber]++;
        }
        int N = oomages.size();
        for(int number : numberInbucket){
            if(number<N/50||number>N/2.5)
                return false;
        }
        return true;
    }
}
