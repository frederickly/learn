package algorithms.slidingwindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SlidingWindowMedianHashHeap {
    /**
     * @param nums: A list of integers
     * @param k:    An integer
     * @return: The median of the element inside the window at each moving
     */
    public List<Integer> medianSlidingWindow(int[] nums, int k) {
        List<Integer> ans = new ArrayList<>();
        if (nums == null || nums.length < k)
            return ans;

        HashHeap sm = new HashHeap(false);  // max heap
        HashHeap lg = new HashHeap(true);   // min heap

        for (int i = 0; i < nums.length; i++) {
            // Add new element to pqs for window
            sm.add(nums[i]);
            lg.add(sm.poll());
            if (lg.size() > sm.size())
                sm.add(lg.poll());

            if (i >= k - 1) {
                // Add median
                ans.add(sm.peek());

                // Remove old element from window
                int old = nums[i - k + 1];
                if (sm.contains(old)) {
                    sm.remove(old);
                    if (lg.size() > sm.size())
                        sm.add(lg.poll());
                } else if (lg.contains(old)) {
                    lg.remove(old);
                    if (sm.size() > lg.size() + 1)
                        lg.add(sm.poll());
                }
            }
        }
        return ans;
    }
}

class HashHeap {
    protected static class Node {
        public int id;
        public int cnt;

        public Node(int id, int cnt) {
            this.id = id;
            this.cnt = cnt;
        }

        public Node(Node node) {
            this.id = node.id;
            this.cnt = node.cnt;
        }
    }

    private boolean isMinHeap;
    private int size;
    private List<Integer> heap;
    private Map<Integer, Node> map;  // key=heap val

    public HashHeap(boolean isMinHeap) {
        this.isMinHeap = isMinHeap;
        size = 0;
        heap = new ArrayList<>();
        map = new HashMap<>();
    }

    public void add(int val) {
        size++;

        if (map.containsKey(val))
            map.get(val).cnt++;
        else {
            heap.add(val);
            map.put(val, new Node(heap.size() - 1, 1));
            bubbleUp(heap.size() - 1);
        }
    }

    public int peek() {
        return heap.get(0);
    }

    public int poll() {
        return remove(heap.get(0));
    }

    public int remove(int val) {
        if (!map.containsKey(val))
            throw new IllegalArgumentException(val + " not found.");

        size--;
        Node node = map.get(val);
        int id = node.id;

        if (node.cnt > 1)
            node.cnt--;
        else {
            swap(id, heap.size() - 1);
            map.remove(val);
            heap.remove(heap.size() - 1);
            if (id < heap.size())
                bubble(id);
        }

        return val;
    }

    public boolean contains(int val) {
        return map.containsKey(val);
    }

    public int size() {
        return size;
    }

    public boolean empty() {
        return size == 0;
    }

    public String toString() {
        return heap.toString();
    }

    protected int parent(int id) {
        return id == 0 ? -1 : (id - 1) / 2;
    }

    protected int left(int id) {
        return id * 2 + 1;
    }

    protected int right(int id) {
        return id * 2 + 2;
    }

    protected int compare(int a, int b) {
        if (isMinHeap)
            return Integer.compare(a, b);
        else
            return Integer.compare(b, a);
    }

    protected void swap(int ida, int idb) {
        if (ida == idb) return;

        int vala = heap.get(ida);
        int valb = heap.get(idb);

        Node nodea = map.get(vala);
        Node nodeb = map.get(valb);

        heap.set(ida, valb);
        heap.set(idb, vala);

        nodea.id = idb;
        nodeb.id = ida;
    }

    protected void bubbleUp(int id) {
        while (parent(id) != -1) {
            int pid = parent(id);
            if (compare(heap.get(id), heap.get(pid)) >= 0)
                break;
            swap(id, pid);
            id = pid;
        }
    }

    protected void bubbleDown(int id) {
        while (left(id) < heap.size()) {
            int lid = left(id);
            int rid = right(id);
            int min = lid;

            if (rid < heap.size() && compare(heap.get(rid), heap.get(lid)) < 0)
                min = rid;

            if (compare(heap.get(id), heap.get(min)) <= 0)
                break;
            swap(id, min);
            id = min;
        }
    }

    protected void bubble(int id) {
        int pid = parent(id);
        if (pid != -1 && compare(heap.get(id), heap.get(pid)) < 0)
            bubbleUp(id);
        else
            bubbleDown(id);
    }
}

