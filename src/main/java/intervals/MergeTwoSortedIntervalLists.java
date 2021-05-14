package intervals;

import java.util.ArrayList;
import java.util.List;

/**
 * 839. Merge Two Sorted Interval Lists
 * https://www.lintcode.com/problem/merge-two-sorted-interval-lists/description
 *
 * Merge two sorted (ascending) lists of interval and return it as a new sorted list. The new sorted list should be made by splicing together the intervals of the two lists and sorted in ascending order.
 * Example
 *
 * Example1
 *
 * Input: list1 = [(1,2),(3,4)] and list2 = [(2,3),(5,6)]
 * Output: [(1,4),(5,6)]
 * Explanation:
 * (1,2),(2,3),(3,4) --> (1,4)
 * (5,6) --> (5,6)
 *
 * Example2
 *
 * Input: list1 = [(1,2),(3,4)] and list2 = [(4,5),(6,7)]
 * Output: [(1,2),(3,5),(6,7)]
 * Explanation:
 * (1,2) --> (1,2)
 * (3,4),(4,5) --> (3,5)
 * (6,7) --> (6,7)
 *
 * Notice
 *
 *     The intervals in the given list do not overlap.
 *     The intervals in different lists may overlap
 */
public class MergeTwoSortedIntervalLists {
    /**
     * @param list1: one of the given list
     * @param list2: another list
     * @return: the new sorted list of interval
     */
    public List<Interval> mergeTwoInterval(List<Interval> list1, List<Interval> list2) {
        // write your code here
        if (list1 == null || list1.size() == 0) {
            return list2;
        }
        if (list2 == null || list2.size() == 0) {
            return list1;
        }

        List<Interval> result = new ArrayList<>();

        do {

            Interval interval1 = list1.get(0);
            Interval interval2 = list2.get(0);

            if (interval1.end < interval2.start) {
                result.add(interval1);
                list1.remove(0);
                continue;
            }

            if (interval2.end < interval1.start) {
                result.add(interval2);
                list2.remove(0);
                continue;
            }


            if (interval2.start <= interval1.end) {

                interval1.start = interval1.start >= interval2.start ? interval2.start : interval1.start;
                interval1.end = interval1.end >= interval2.end ? interval1.end : interval2.end;

                result.add(interval1);
                list1.remove(0);
                list2.remove(0);

                while ((list1.size() > 0 && interval1.end >= list1.get(0).start) || (list2.size() > 0 && interval1.end >= list2.get(0).start)) {

                    if (list1.size() > 0 && interval1.end >= list1.get(0).start) {
                        interval1.start = interval1.start >= list1.get(0).start ? list1.get(0).start : interval1.start;
                        interval1.end = interval1.end >= list1.get(0).end ? interval1.end : list1.get(0).end;
                        list1.remove(0);
                    }

                    if (list2.size() > 0 && interval1.end >= list2.get(0).start) {
                        interval1.start = interval1.start >= list2.get(0).start ? list2.get(0).start : interval1.start;
                        interval1.end = interval1.end >= list2.get(0).end ? interval1.end : list2.get(0).end;
                        list2.remove(0);
                    }

                }
            }

        } while (list1.size() > 0 && list2.size() > 0);

        while (list1.size() > 0) {
            result.add(list1.remove(0));
        }

        while (list2.size() > 0) {
            result.add(list2.remove(0));
        }

        return result;
    }
}
