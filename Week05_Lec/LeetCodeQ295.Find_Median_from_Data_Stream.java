/*
295. Find Median from Data Stream
Median is the middle value in an ordered integer list. If the size of the list is even, 
there is no middle value. So the median is the mean of the two middle value.

Examples: 
[2,3,4] , the median is 3

[2,3], the median is (2 + 3) / 2 = 2.5

Design a data structure that supports the following two operations:

void addNum(int num) - Add a integer number from the data stream to the data structure.
double findMedian() - Return the median of all elements so far.
For example:

addNum(1)
addNum(2)
findMedian() -> 1.5
addNum(3) 
findMedian() -> 2
*/

public class MedianFinder {
    
    private PriorityQueue<Integer> minHeap; //store larger elements
    private PriorityQueue<Integer> maxHeap; //store smaller elements
    /** initialize your data structure here. */
    public MedianFinder() {
        minHeap = new PriorityQueue<Integer>();
        maxHeap = new PriorityQueue<Integer>(new Comparator<Integer>(){
            @Override
            public int compare(Integer a, Integer b) {
                return b - a;
            }
        });
    }
    
    public void addNum(int num) {
        maxHeap.add(num);  //offer works too
        minHeap.add(maxHeap.poll());
        if (minHeap.size() > maxHeap.size()) {
            maxHeap.add(minHeap.poll());
        }
    }
    
    public double findMedian() {
        if (minHeap.size() == maxHeap.size()) {
            return (minHeap.peek() + maxHeap.peek()) / 2.0; 
        } else {
            return maxHeap.peek();
        }
    }
}

/*
三种方法写comparator
1. maxHeap = new PriorityQueue<Integer>(Collections.reverseOrder());
2. maxHeap = new PriorityQueue<Integer>(new Comparator<Integer>(){
            @Override
            public int compare(Integer a, Integer b) {
                return b - a;
            }
        });
3. private class MyComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer a, Integer b) {  //一定要是public！！！
            return b - a;
            //return b.compareTo(a);
        }
    }
    maxHeap = new PriorityQueue<Integer>(new MyComparator());
*/

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
