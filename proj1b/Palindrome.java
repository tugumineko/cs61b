public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque d = new ArrayDeque();
        for(int i=0;i<word.length();i++){
            d.addLast(word.charAt(i));
        }
        return d;
    }


    /**A palindrome is defined as a word that is the same whether it is read forwards or backwards.
     * For example “a”, “racecar”, and “noon” are all palindromes.
     * “horse”, “rancor”, and “aaaaab” are not palindromes.
     * Any word of length 1 or 0 is a palindrome.*/
    public boolean isPalindrome(String word){
       if(word.length()==1||word.length()==0)return true;
       int r=0,l=word.length()-1;
       int mid = (r+l)/2;
       while(l!=mid)
       {
           if(word.charAt(r)!=word.charAt(l))return false;
           r++;l--;
       };
       return true;
    }

    public boolean isPalindrome(String word,CharacterComparator cc){
           if(word.length()==1||word.length()==0)return true;
    int r=0,l=word.length()-1;
    int mid = (r+l)/2;
    while(l!=mid)
    {
        if(!cc.equalChars(word.charAt(r),word.charAt(l)))return false;
        r++;l--;
    }
       return true;
}



}
