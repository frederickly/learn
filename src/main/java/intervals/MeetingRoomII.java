package intervals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 919. Meeting Rooms II
 * https://www.lintcode.com/problem/meeting-rooms-ii/description
 *
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.
 * Example
 *
 * Example1
 *
 * Input: intervals = [(0,30),(5,10),(15,20)]
 * Output: 2
 * Explanation:
 * We need two meeting rooms
 * room1: (0,30)
 * room2: (5,10),(15,20)
 *
 * Example2
 *
 * Input: intervals = [(2,7)]
 * Output: 1
 * Explanation:
 * Only need one meeting room
 *
 * Solution: sweepline see detailed explanation in AirPlanes
 */
public class MeetingRoomII {
    /**
     * @param intervals: an array of meeting time intervals
     * @return: the minimum number of conference rooms required
     */
    public int minMeetingRooms(List<Interval> intervals) {
        List<TimePoint> times = new ArrayList<>();
        for(Interval interval: intervals){
            times.add(new TimePoint(interval.start, true));
            times.add(new TimePoint(interval.end, false));
        }
        Collections.sort(times, new TimeComparator());
        int max=0, cur=0;
        int prev=-1;

        //System.out.println(times);
        for(int i=0;i< times.size();i++){
            TimePoint tp= times.get(i);
            if(tp.time!=prev) {
                max= Math.max(max, cur);
                prev= tp.time;
            }
            if(tp.isStart) {
                cur++;
            }else
                cur--;
        }
        max= Math.max(max, cur);
        return max;
    }

    private class TimeComparator implements Comparator<TimePoint> {
        public int compare(TimePoint a, TimePoint b) {
            int res = a.time - b.time;
            if(res!=0) return res;
            if(!a.isStart) return -1;
            else return 1;
        }
    }

    class TimePoint{
        public int time;
        public boolean isStart;

        public TimePoint(int time, boolean isStart){
            this.time = time;
            this.isStart= isStart;
        }

        public String toString(){
            return time+""+ isStart;
        }
    }
}
