package algorithms.UniqeInDataStream;

import java.util.HashMap;
import java.util.HashSet;

public class UniqueKeyInDataStreamII {

    public class DataStream {
        class Node {
            int val;
            Node next;
            Node pre;

            public Node(int val) {
                this.val = val;
            }
        }

        HashMap<Integer, Node> map;
        HashSet<Integer> set;
        Node head;
        Node tail;

        public DataStream() {
            map = new HashMap<>();
            set = new HashSet<>();
            head = new Node(-1);
            tail = new Node(-1);
            head.next = tail;
            tail.pre = head;
        }

        /**
         * @param num: next number in stream
         * @return: nothing
         */
        public void add(int num) {
            if (!set.contains(num)) {
                addNode(num);
            } else if (map.containsKey(num)) removeNode(num);
        }

        private void addNode(int num) {
            Node node = new Node(num);
            tail.pre.next = node;
            node.pre = tail.pre;
            node.next = tail;
            tail.pre = node;
            set.add(num);
            map.put(num, node);
        }

        private void removeNode(int num) {
            Node node = map.get(num);
            node.pre.next = node.next;
            node.next.pre = node.pre;
            map.remove(num);
        }

        /*
         * @return: the first unique number in stream
         */
        public int firstUnique() {
            return head.next.val;
        }
    }
}
