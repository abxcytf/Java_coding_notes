/*
pattern match between two strings


("tree", "loaa") => true
("matter", "essare") => false
("paper", "mime") => false
("acquaintance", "mlswmodqmdlp") => true
("tree", "aoaa") => false
*/


public class Solution {
    public static void main(String[] argus) {
        String s1 = "abc";
        String s2 = "aac";
        System.out.println(sameStringPattern(s1, s2));
    }

    private static boolean sameStringPattern(String s1, String s2) {
        if (s1 == null && s2 == null) {
            return true;
        }

        if (s1 == null || s2 == null) {
            return false;
        }

        if (s1.length() != s2.length()) {
            return false;
        }
        int n = s1.length();
        Map<Character, Character> map = new HashMap<>();

        char[] cs1 = s1.toCharArray();
        char[] cs2 = s2.toCharArray();

        for (int i = 0; i < n; i++) {
            char ch1 = cs1[i];
            char ch2 = cs2[i];

            if (!map.containsKey(ch1)) {
                if (map.containsValue(ch2)) {
                    return false;
                }
                map.put(ch1, ch2);
            } else {
                if (ch2 != map.get(ch1)) {
                    return false;
                }
            }
        }
        return true;
    }
}
