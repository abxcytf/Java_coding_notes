/*
253. Meeting Rooms II
Given an array of meeting time intervals consisting of start and end 
times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.

For example,
Given [[0, 30],[5, 10],[15, 20]],
return 2.
*/

/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
public class Solution {
    public int minMeetingRooms(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        int[] starts = new int[intervals.length];
        int[] ends = new int[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            starts[i] = intervals[i].start;
            ends[i] = intervals[i].end;
        }
        
        Arrays.sort(starts);
        Arrays.sort(ends);
        
        int room = 0;
        int endIndex = 0;
        
        for (int i = 0; i < intervals.length; i++) {
            if (starts[i] < ends[endIndex]) {
                room++;
            } else {
                endIndex++;
            }
        }
        return room;
    }
    
    /*************************************************************************/
    //heap implementation
    public int minMeetingRooms(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        //sort according to the meeting start time
        Arrays.sort(intervals, (a, b) -> (a.start - b.start));
        
        PriorityQueue<Interval> minHeap = new PriorityQueue<>(intervals.length, (a, b) -> (a.end - b.end)); 
        //heap sort according to the meeting end time
        
        minHeap.offer(intervals[0]);
        int room = 1;
        for (int i = 1; i < intervals.length; i++) {
            //always compare the earlest ending meeting in the current minHeap
            if (intervals[i].start >= minHeap.peek().end) {
                minHeap.poll();
            }
            minHeap.offer(intervals[i]);
            room = Math.max(room, minHeap.size());
        }
        return room;
    }
}
