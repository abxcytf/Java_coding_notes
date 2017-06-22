/*
71. Simplify Path

Given an absolute path for a file (Unix-style), simplify it.

For example,
path = "/home/", => "/home"
path = "/a/./b/../../c/", => "/c"

Corner Cases:
. Did you consider the case where path = "/../"?
  In this case, you should return "/".
. Another corner case is the path might contain multiple slashes '/' together, 
  such as "/home//foo/".
  In this case, you should ignore redundant slashes and return "/home/foo".
*/
// ！！！！ "/."表示当前目录 current directory
//！！！！ "/.." 表示上一级目录 last directory

public class Solution {
    //time O(n), space O(n)
    public String simplifyPath(String path) {
        if (path == null || path.length() == 0) {
            return "";
        }
        String result = "/";
        String[] stubs = path.split("/");
        List<String> paths = new LinkedList<>();
        for (String s : stubs) {
            if (s.equals("..")) {
                if (paths.size() > 0) {
                    paths.remove(paths.size() - 1);
                }
            } else if (!s.equals(".") && !s.equals("")) {
                paths.add(s);
            }
        }
        
        for (String s : paths) {
            result += s + "/";
        }
        if (result.length() > 1) {
            result = result.substring(0, result.length() - 1); //remove the last '/'
        }
        return result;
    }

    /*****************************************************************************************/
    //optimized implementation, using string[] to simulate stack
    public String simplifyPath(String path) {
        if (path == null || path.length() == 0) {
            return "";
        }
        int len = path.length();
        char[] pathFinder = path.toCharArray();
        String[] stack = new String[len];
        
        int push = -1;
        int index = 0;
        while (index < len) {
            if (pathFinder[index] == '/') {
                index++;
                continue;
            }
            int tail = index;
            while (tail < len && pathFinder[tail] != '/') {
                tail++;
            }
            
            String paths = path.substring(index, tail);
            if (paths.equals("..")) {
                if (push >= 0) {
                    push--;
                }
            } else if (!paths.equals(".")) {
                    stack[++push] = paths;
            }
            index = tail + 1;
        }
        
        if (push == -1) {
            return "/";
        }
        StringBuilder simplifiedPath = new StringBuilder();
        for (int i = 0; i <= push; i++) {
            simplifiedPath.append('/');
            simplifiedPath.append(stack[i]);
        }
        return simplifiedPath.toString();
    }
}
