package numbers;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 920. Meeting Rooms
 * https://www.lintcode.com/problem/meeting-rooms/description
 *
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), determine if a person could attend all meetings.
 * Example
 *
 * Example1
 *
 * Input: intervals = [(0,30),(5,10),(15,20)]
 * Output: false
 * Explanation:
 * (0,30), (5,10) and (0,30),(15,20) will conflict
 *
 * Example2
 *
 * Input: intervals = [(5,8),(9,15)]
 * Output: true
 * Explanation:
 * Two times will not conflict
 *
 * Notice
 *
 * (0,8),(8,10) is not conflict at 8
 */
public class MeetingRooms {
    /**
     * @param intervals: an array of meeting time intervals
     * @return: if a person could attend all meetings
     */
    public boolean canAttendMeetings(List<Interval> intervals) {
        Collections.sort(intervals, new IntervalComparator());
        int prevEnd = 0;
        for (Interval curr : intervals) {
            if (prevEnd > curr.start) return false;
            prevEnd = curr.end;
        }
        return true;
    }

    private static class IntervalComparator implements Comparator<Interval> {
        public int compare(Interval i1, Interval i2) {
            return Integer.compare(i1.start, i2.start);
        }
    }

    public class Interval {
      int start, end;
      Interval(int start, int end) {
          this.start = start;
          this.end = end;
      }
  }
}
