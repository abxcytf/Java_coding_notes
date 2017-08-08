/*
567. Permutation in String
Given two strings s1 and s2, write a function to return true if s2 contains the permutation of s1. 
In other words, one of the first string's permutations is the substring of the second string.

Example 1:
Input:s1 = "ab" s2 = "eidbaooo"
Output:True
Explanation: s2 contains one permutation of s1 ("ba").
Example 2:
Input:s1= "ab" s2 = "eidboaoo"
Output: False
Note:
The input strings only contain lower case letters.
The length of both given strings is in range [1, 10,000].
*/

public class Solution {
    public boolean checkInclusion(String s1, String s2) {
        //corner case
        if (s1 == null || s1.length() == 0) {
            return true;
        }
        if (s2 == null || s2.length() == 0) {
            return false;
        }
        if (s1.length() > s2.length()) {
            return false;
        }
        int[] count = new int[26];
        //creating sliding window, length is s1.length()
        for (int i = 0; i < s1.length(); i++) {
            count[s1.charAt(i) - 'a']++; //count the letters
            count[s2.charAt(i) - 'a']--; //offset
        }
        
        //check current window content see if it match
        if (isMatch(count)) {
            return true;
        }
        
        for (int i = s1.length(); i < s2.length(); i++) {
            count[s2.charAt(i) - 'a']--; //add right side elem to window set to offset
            count[s2.charAt(i - s1.length()) - 'a']++; //remove the offset of the left side elem
            if (isMatch(count)) {
                return true;
            }
        }
        return false;
    }
    
    private boolean isMatch(int[] count) {
        for (int i = 0; i < count.length; i++) {
            if (count[i] != 0) {
                return false;
            }
        }
        return true;
    }
    
    /******************************************************************************************************/
    //import java.math.BigInteger;
    public boolean checkInclusion(String s1, String s2) {
        //corner case
        if (s1 == null || s1.length() == 0) {
            return true;
        }
        if (s2 == null || s2.length() == 0) {
            return false;
        }
        if (s1.length() > s2.length()) {
            return false;
        }
        char[] chOne = s1.toCharArray();
        char[] chTwo = s2.toCharArray();
        
        //long max will overflow at 9 digits
        BigInteger target = new BigInteger("1");
        BigInteger checkSum = new BigInteger("1");
        
        //compose the big prime number
        //create the first window
        for (int i = 0; i < chOne.length; i++) {
            int nOne = chOne[i] - 'a';
            BigInteger primeOne = new BigInteger(Integer.toString(nOne * nOne + nOne + 41));
            int nTwo = chTwo[i] - 'a';
            BigInteger primeTwo = new BigInteger(Integer.toString(nTwo * nTwo + nTwo + 41));
            target = target.multiply(primeOne);
            checkSum = checkSum.multiply(primeTwo);
        }
        
        if (target.equals(checkSum)) {
            return true;
        }
        
        for (int i = chOne.length; i < chTwo.length; i++) {
            int nLast = chTwo[i - chOne.length] - 'a';
            BigInteger primeLast = new BigInteger(Integer.toString(nLast * nLast + nLast + 41));
            int nNext = chTwo[i] - 'a';
            BigInteger primeNext = new BigInteger(Integer.toString(nNext * nNext + nNext + 41));
            checkSum = checkSum.divide(primeLast);
            checkSum = checkSum.multiply(primeNext);
            
            if (target.equals(checkSum)) {
                return true;
            }
        }
        return false;
    }
}
