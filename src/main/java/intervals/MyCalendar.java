package intervals;

import java.util.TreeMap;

/**
 * 1065. My Calendar I
 * https://www.lintcode.com/problem/my-calendar-i/description
 *
 * Implement a MyCalendar class to store your events. A new event can be added if adding the event will not cause a double booking.
 *
 * Your class will have the method, book(int start, int end). Formally, this represents a booking on the half open interval [start, end), the range of real numbers x such that start <= x < end.
 *
 * A double booking happens when two events have some non-empty intersection (ie., there is some time that is common to both events.)
 *
 * For each call to the method MyCalendar.book, return true if the event can be added to the calendar successfully without causing a double booking. Otherwise, return false and do not add the event to the calendar.
 *
 * Your class will be called like this: MyCalendar cal = new MyCalendar(); MyCalendar.book(start, end)
 * Example
 *
 * MyCalendar();
 * MyCalendar.book(10, 20); // returns true
 * MyCalendar.book(15, 25); // returns false
 * MyCalendar.book(20, 30); // returns true
 * Explanation:
 * The first event can be booked.  The second can't because time 15 is already booked by another event.
 * The third event can be booked, as the first event takes every time less than 20, but not including 20.
 *
 * Notice
 *
 *     The number of calls to MyCalendar.book per test case will be at most 1000.
 *     In calls to MyCalendar.book(start, end), start and end are integers in the range [0, 10^9].
 *
 * Solution:
 * 方法一 brute force
 * Time O(n^2)
 * Space O(n)
 *
 * 方法二 treemap
 * maintain 已有的 数据 sorted，支持快速查找key 和快速插入 -- treemap
 * We need a data structure that keeps elements sorted and supports fast insertion. In Java, a TreeMap is the perfect candidate. In Python, we can build our own binary tree structure
 * key: start
 * value: end
 * we would like calendar.get(prev)) <= start <= end <= next for the booking to be valid (or for prev or next to be null respectively.)then insert it in O(1) time.
 *
 * Time Complexity (Java): O(NlogN), where N is the number of events booked. For each new event, we search that the event is legal in O(logN) time
 * Space Complexity: O(N), the size of the data structures used.
 *
 * lowerEntry，返回所有比给定Map.Entry小的元素
 * floorEntry，返回所有比给定Map.Entry小或相等的元素
 * ceilingEntry，返回所有比给定Map.Entry大或相等的元素
 * higherEntry，返回所有比给定Map.Entry大的元素
 *
 *
 */
public class MyCalendar {
    TreeMap<Integer, Integer> calendar;

    MyCalendar() {
        calendar = new TreeMap();
    }

    public boolean book(int start, int end) {
        Integer previous = calendar.floorKey(start), next = calendar.ceilingKey(start);
        if ((previous == null || calendar.get(previous) <= start) && (next == null || end <= next)) {
            calendar.put(start, end);
            return true;
        }
        return false;
    }
}

