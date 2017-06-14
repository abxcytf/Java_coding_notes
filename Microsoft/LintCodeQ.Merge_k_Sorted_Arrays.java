/*
Merge k Sorted Arrays

Given k sorted integer arrays, merge them into one sorted array.

Example 

Given 3 sorted arrays:
[
  [1, 3, 5, 7],
  [2, 4, 6],
  [0, 8, 9, 10, 11]
]


return [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11].

*/

public class Solution {
    /**
     * @param arrays k sorted integer arrays
     * @return a sorted array
     */
    
    class Element {
        public int row;
        public int col;
        public int val;
        public Element(int row, int col, int val) {
            this.row = row;
            this.col = col;
            this.val = val;
        }
    } 
    
    // public class ElemComparator implements Comparator<Element> {
    //     @Override
    //     public int compare(Element a, Element b) {
    //         return a.val - b.val;
    //     }
    // }
    
    private Comparator<Element> ElemComparator = new Comparator<Element>(){
        @Override
        public int compare(Element a, Element b) {
            return a.val - b.val;
        }
    };
     
    public List<Integer> mergekSortedArrays(int[][] arrays) {
        // Write your code here
        List<Integer> result = new ArrayList<>();
        if (arrays == null) {
            return result;
        }
        // Comparator<Element> myComp = new ElemComparator();
        // PriorityQueue<Element> minHeap = new PriorityQueue<>(new ElemComparator()); //java 8 works
        // PriorityQueue<Element> minHeap = new PriorityQueue<>(arrays.length, myComp);
         //PriorityQueue<Element> minHeap = new PriorityQueue<>(ElemComparator); //java 8 works
         PriorityQueue<Element> minHeap = new PriorityQueue<>(arrays.length, ElemComparator);
        
        for (int i = 0; i < arrays.length; i++) {
            if (arrays[i].length > 0) {
                Element elem = new Element(i, 0, arrays[i][0]);
                minHeap.offer(elem);
            }
        }
        
        while (!minHeap.isEmpty()) {
            Element current = minHeap.poll();
            result.add(current.val);
            if (current.col + 1 < arrays[current.row].length) {
                int newCol = current.col + 1;
                int newValue = arrays[current.row][newCol];
                minHeap.offer(new Element(current.row, newCol, newValue));
            }
        }
        return result;
    }
}
