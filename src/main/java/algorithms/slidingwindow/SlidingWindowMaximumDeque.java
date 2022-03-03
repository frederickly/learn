package algorithms.slidingwindow;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class SlidingWindowMaximumDeque {

    /**
     * @param nums: A list of integers.
     * @param k: An integer
     * @return: The maximum number inside the window at each moving.
     * stack or queue
     * single order , increase or decrease
     * Deque -> LinkedList
     * deque 的用法，使用打擂台的策略储存必要的最大值，得到一个降序的list; 存储下标即可。
     * 类似于用最大堆，但是加入了排序功能所以只用线性的的数据结构 就可以找到最大值。
     * Not priority queue, Not simple set, had to create new class map for (val, pos)
     * hashCode
     * As much as is reasonably practical, the hashCode method defined by class Object does return distinct integers for distinct objects.
     * (This is typically implemented by converting the internal address of the object into an integer,
     * but this implementation technique is not required by the JavaTM programming language.)
     *
     * 三种解法
     *
     * 算法1: PriorityQueue 存放index，注意优化删除堆顶元素的条件
     *
     *     堆顶元素表示的index 在 sliding window 外
     *     堆顶元素比当前要插入的元素大
     *
     * O(n log n)
     *
     * 算法2: 用 TreeSet存index，由于是Set，所以一定要保证插入进去的元素是不重复的。
     *
     * TreeSet<Integer> ts = new TreeSet<>((i1, i2) -> {
     *             if (nums[i1] != nums[i2]) {
     *                 return nums[i1] - nums[i2];
     *             } else {
     *                 return i1 - i2;
     *             }
     *         });
     *
     * O(n log k)
     *
     * 算法3: 维护一个单调的双端队列存放数组index，保证队头永远是当前队列中最大的元素，在队尾插入的时候把比要插入元素小的元素都pop掉再插入。
     *
     * 只有当队头的index超出sliding window的范围的时候再pop掉它
     *
     * O(n) - 由于每一个元素只进出队列一次
     * */
    public List<Integer> maxSlidingWindow(int[] nums, int k) {
        Deque<Integer> dq = new ArrayDeque<>();
        List<Integer> res = new ArrayList<>();
        int start = k-1;
        for (int i = 0; i < nums.length; i++) {
            int curr = nums[i];
            while (!dq.isEmpty() && curr > nums[dq.peekLast()]) {
                dq.pollLast();
            }
            dq.offerLast(i);
            if (i < start) {
                continue;
            }
            res.add(nums[dq.peekFirst()]);
            if (nums[dq.peek()] == nums[i - k + 1]) {
                dq.pollFirst();
            }
        }
        return res;
    }
}
