package algorithms.slidingwindow;

import java.util.ArrayList;

import java.util.List;
import java.util.PriorityQueue;

public class SlidingWindowMedianV1 {

    PriorityQueue<Integer> left, right;

    public List<Integer> medianSlidingWindow(int[] a, int k) {
        left = new PriorityQueue<>((a0, b)->Integer.compare(b, a0));
        right = new PriorityQueue<>();

        List<Integer> res = new ArrayList<>();
        for(int i = 0; i < a.length; i++) {
            add(a[i]);
            if(i >= k) remove(a[i - k]);
            if(i >= k-1) res.add(getMedian());

        }
        return res;
    }

    int getMedian() {
        return left.peek();
    }

    void add(int n) {
        if(left.size() > right.size()) right.offer(n);
        else left.offer(n);

        if(!left.isEmpty() && !right.isEmpty() && left.peek() > right.peek()) {
            int l = left.poll();
            int r = right.poll();
            left.offer(r);
            right.offer(l);
        }
    }

    void remove(int n) {
        if(left.remove(n)) {
            if(left.size() < right.size()) left.offer(right.poll());
        } else {
            right.remove(n);
            if(left.size() - right.size() > 1) right.offer(left.poll());
        }
    }
}
