package work.huangdu.interview.didi;

/**
 * @author huangdu
 * @version 2025/5/27
 */
public class Solution {

    public static void main(String[] args) {
        Node head = new Node(1);
        System.out.println(copy(head));
    }

    public static Node copy(Node head) {
        Node cur = head;
        while (cur != null) {
            Node copyNode = new Node(cur.val);
            Node next = cur.next;
            cur.next = copyNode;
            copyNode.next = next;
            cur = next;
        }
        cur = head;
        while (cur != null) {
            Node copy = cur.next;
            if (cur.random != null) {
                copy.random = cur.random.next;
            }
            cur = copy.next;
        }
        Node dummy = new Node(-1), point = dummy;
        cur = head;
        while (cur != null) {
            Node copy = cur.next;
            cur.next = null;
            Node next = copy.next;
            point.next = copy;
            copy.next = null;
            point = point.next;
            cur = next;
        }
        return dummy.next;
    }

    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
        }
    }
}

