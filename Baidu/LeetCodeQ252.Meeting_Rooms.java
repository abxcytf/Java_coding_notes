/*
252. Meeting Rooms
Given an array of meeting time intervals consisting of start and end 
times [[s1,e1],[s2,e2],...] (si < ei), determine if a person could attend all meetings.

For example,
Given [[0, 30],[5, 10],[15, 20]],
return false.
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
    //create IntervalComparator class implements from Comparator Interface
    public boolean canAttendMeetings(Interval[] intervals) {
        if (intervals == null || intervals.length <= 1) {
            return true;
        }
        IntervalComparator myComp = new IntervalComparator();
        Arrays.sort(intervals, myComp);
        // Arrays.sort(intervals, new IntervalComparator());
        
        Interval current = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i].start < current.end) {
                return false;
            }
            current = intervals[i];
        }
        return true;
    }
    
    private class IntervalComparator implements Comparator<Interval> {
        @Override
        public int compare(Interval a, Interval b) {
            return a.start - b.start;
        }
    }
    
    
    /***************************************************************************************/
    //override匿名类的compare方法
    public boolean canAttendMeetings(Interval[] intervals) {
        if (intervals == null || intervals.length <= 1) {
            return true;
        }    
        Arrays.sort(intervals, new Comparator<Interval>(){
           @Override
           public int compare(Interval a, Interval b) {
               return a.start - b.start;
           }
            /*a.start - b.start is short for
            if (a.start < b.start) {
                return -1;
            } else if (a.start > b.start) {
                return 1;
            } else {
                return 0;
            }
            */
        });
        
        Interval current = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i].start < current.end) {
                return false;
            }
            current = intervals[i];
        }
        return true;
    }
}
