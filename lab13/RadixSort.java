/**
 * Class for doing Radix sort
 *
 * @author Akhil Batra, Alexander Hwang
 *
 */
public class RadixSort {
    /**
     * Does LSD radix sort on the passed in array with the following restrictions:
     * The array can only have ASCII Strings (sequence of 1 byte characters)
     * The sorting is stable and non-destructive
     * The Strings can be variable length (all Strings are not constrained to 1 length)
     *
     * @param asciis String[] that needs to be sorted
     *
     * @return String[] the sorted array
     */
    public static String[] sort(String[] asciis) {
        // TODO: Implement LSD Sort
        int max = Integer.MIN_VALUE;
        for(String i:asciis){
            max = i.length()>max ? i.length():max;
        }

        for(int i = max-1;i>=0;i--){
            sortHelperLSD(asciis,i);
        }

        return asciis;
    }

    /**
     * LSD helper method that performs a destructive counting sort the array of
     * Strings based off characters at a specific index.
     * @param asciis Input array of Strings
     * @param index The position to sort the Strings on.
     */
    private static void sortHelperLSD(String[] asciis, int index) {
        // Optional LSD helper method for required LSD radix sort
        int[] counts = new int[129];
        for(String i:asciis) {
            int c = getCharAt(i,index);
            counts[c]++;
        }

        for(int i=1;i<=129;i++){
            counts[i]+=counts[i-1];
        }

        String[] sorted = new String[asciis.length];
        for(int i = asciis.length-1;i>=0;i--){
            int c = getCharAt(asciis[i],index);
            sorted[--counts[c]]=asciis[i];
        }
        System.arraycopy(sorted,0,asciis,0,asciis.length);
    }

    private static int getCharAt(String s, int index) {
        if (s.length() > index && index >= 0) {
            return (int) s.charAt(index) + 1;
        }
        return 0;
    }

    /**
     * MSD radix sort helper function that recursively calls itself to achieve the sorted array.
     * Destructive method that changes the passed in array, asciis.
     *
     * @param asciis String[] to be sorted
     * @param start int for where to start sorting in this method (includes String at start)
     * @param end int for where to end sorting in this method (does not include String at end)
     * @param index the index of the character the method is currently sorting on
     *
     **/
    private static void sortHelperMSD(String[] asciis, int start, int end, int index) {
        // Optional MSD helper method for optional MSD radix sort
        return;
    }
}
