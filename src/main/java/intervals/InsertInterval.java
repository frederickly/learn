package intervals;

import java.util.List;

/**
 * 30. Insert Interval
 * https://www.lintcode.com/problem/insert-interval/description
 *
 * Given a non-overlapping interval list which is sorted by start point.
 *
 * Insert a new interval into it, make sure the list is still in order and non-overlapping (merge intervals if necessary).
 * Example
 *
 * Example 1:
 *
 * Input:
 * (2, 5) into [(1,2), (5,9)]
 * Output:
 * [(1,9)]
 *
 * Example 2:
 *
 * Input:
 * (3, 4) into [(1,2), (5,9)]
 * Output:
 * [(1,2), (3,4), (5,9)]
 */
public class InsertInterval {
    /**
     * @param intervals: Sorted interval list.
     * @param newInterval: new interval.
     * @return: A new interval list.
     */
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {

        int start=0;
        int end =  intervals.size()-1;
        int middle=0;
        while(start +1 <end){
            middle = start + (end-start)/2;
            if (intervals.get(middle).start<=newInterval.start){
                start= middle;
                if(intervals.get(middle+1).start>=newInterval.start)
                    end= middle+1;
            }else
                end = middle;
        }

        if(isOverlap(intervals.get(start), newInterval)||
                isOverlap(newInterval,intervals.get(end))) {
            intervals.add(start+1, newInterval);
            while(end+1< intervals.size() &&isOverlap(intervals.get(end), intervals.get(end+1))) {
                merge(intervals.get(end), intervals.get(end+1));
                intervals.remove(end+1);
            }
            while(start>=0 && start+1< intervals.size()&&isOverlap(intervals.get(start), intervals.get(start+1))) {
                merge(intervals.get(start), intervals.get(start+1));
                intervals.remove(start+1);
            }
        }else
        if(newInterval.start< intervals.get(start).start)
            intervals.add(start, newInterval);
        else if(newInterval.start> intervals.get(end).end)
            intervals.add(end+1, newInterval);
        else
            intervals.add(start+1, newInterval);
        return intervals;
    }

    private void merge(Interval interval1, Interval interval2) {
        interval1.start = Math.min(interval1.start, interval2.start);
        interval1.end = Math.max(interval1.end, interval2.end);
    }

    private boolean isOverlap(Interval interval1, Interval interval2){
        return !(interval2.start> interval1.end || interval2.end< interval1.start);
    }
}
