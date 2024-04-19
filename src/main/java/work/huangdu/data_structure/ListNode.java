package work.huangdu.data_structure;

/**
 * Definition for singly-linked list.
 *
 * @author huangdu
 * @version 2020/8/13 13:45
 */
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode() {
    }

    public ListNode(int x) {
        val = x;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
