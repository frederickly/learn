package intervals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 391. Number of Airplanes in the Sky
 * https://www.lintcode.com/problem/number-of-airplanes-in-the-sky/description
 * <p>
 * Given an list interval, which are taking off and landing time of the flight. How many airplanes are there at most at the same time in the sky?
 * Example
 * <p>
 * Example 1:
 * <p>
 * Input: [(1, 10), (2, 3), (5, 8), (4, 7)]
 * Output: 3
 * Explanation:
 * The first airplane takes off at 1 and lands at 10.
 * The second ariplane takes off at 2 and lands at 3.
 * The third ariplane takes off at 5 and lands at 8.
 * The forth ariplane takes off at 4 and lands at 7.
 * During 5 to 6, there are three airplanes in the sky.
 * <p>
 * Example 2:
 * <p>
 * Input: [(1, 2), (2, 3), (3, 4)]
 * Output: 1
 * Explanation: Landing happen before taking off.
 * <p>
 * Notice
 * <p>
 * If landing and taking off of different planes happen at the same time, we consider landing should happen at first.
 * <p>
 * Solution 1:
 * 类似会议室2, 优先队列根据时间和起降落的优先级排序. 遍历优先队列,扫描排好序的时间块. 起飞就+1, 降落就-1,求当前最大状态即可
 * 检查端点即可. 因为没有事件是不可能变化的.
 * <p>
 * 起飞事件: +事件;
 * 降落事件: -事件;
 * <p>
 * 可以把这个过程看做是一个线从左到右扫描.
 * <p>
 * 左端点, 右端点分开, 排序.
 * <p>
 * 事件复杂度就是O(nlogn)
 * <p>
 * 隐含要求是同时起飞降落时, 降落在前面.
 * <p>
 * 问区间覆盖: 扫描线.
 * <p>
 * 算法：
 * <p>
 * 典型的sweep line的题目。一般sweep line的题目有几个特点：
 * (a) 题目中给出来区间
 * (b) 区间的起点和终点一般表示某个event的开始和结束。换个说法就是如果我们要track一个系统的状态，那么一个event开始到其结束中对这个状态的影响是不变的。
 * (c) 题目要求基本都是找overlap。简单的对event按开始或者结束进行排序不行。比如这个题目，一种最直观的想法就是看interval最多有多少overlap。但是仅仅按interval的排序来找overlap很麻烦，因为下一个interval的start可能跟前面n个intervals有overlap，我们需要不停的去遍历，复杂度是o(n^2)
 * <p>
 * 那么这种题目我们就可以考虑用sweep line。
 * (1) 把所有event的开始点和结束点全部放到一个list中。
 * (2) 然后对这些点进行排序。
 * (3) 最后遍历这些点，根据点的状态（开始或者结束）来update系统状态
 * <p>
 * 这个题目是看在空中最多同时有多少飞机。那么我们就把飞机起飞和降落的点全部放入list，然后排序。最后用sweep line扫描这些点，如果是起飞的点，那么cnt ++；如果是降落的点，那么cnt --。这样就可以找到cnt最大的值。
 * sweep line容易出错的地方在于status值的选择。比如这个题目，起飞的status是1，降落的status是0。这是因为题目中说如果时刻相同的时候，降落先发生，所以我们降落的status要小于起飞，这样排序的时候才能在前面。
 * <p>
 * 实现：
 * <p>
 * 把所有起飞和降落的点放入points
 * 排序
 * sweep line扫描，得到最大值。
 */
public class AirPlanes {
    /**
     * @param airplanes: An interval array
     * @return: Count of airplanes are in the sky.
     */
    public int countOfAirplanes(List<Interval> airplanes) {
        List<TimePoint> times = new ArrayList<>();
        for (Interval interval : airplanes) {
            times.add(new TimePoint(interval.start, true));
            times.add(new TimePoint(interval.end, false));
        }
        Collections.sort(times, new TimeComparator());
        int max = 0, cur = 0;
        int prev = -1;

        //System.out.println(times);
        for (int i = 0; i < times.size(); i++) {
            TimePoint tp = times.get(i);
            if (tp.time != prev) {
                max = Math.max(max, cur);
                prev = tp.time;
            }
            if (tp.isStart) {
                cur++;
            } else
                cur--;
        }
        max = Math.max(max, cur);
        return max;
    }

    private class TimeComparator implements Comparator<TimePoint> {
        public int compare(TimePoint a, TimePoint b) {
            int res = a.time - b.time;
            if (res != 0) return res;
            if (!a.isStart) return -1;
            else return 1;
        }
    }

    class TimePoint {
        public int time;
        public boolean isStart;

        public TimePoint(int time, boolean isStart) {
            this.time = time;
            this.isStart = isStart;
        }

        public String toString() {
            return time + "" + isStart;
        }
    }
}
