package work.huangdu.question_bank.easy;

import work.huangdu.data_structure.ListNode;

/**
 * 剑指 Offer 22. 链表中倒数第k个节点
 * 输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。
 * 例如，一个链表有 6 个节点，从头节点开始，它们的值依次是 1、2、3、4、5、6。这个链表的倒数第 3 个节点是值为 4 的节点。
 * 示例：
 * 给定一个链表: 1->2->3->4->5, 和 k = 2.
 * 返回链表 4->5.
 *
 * @author huangdu
 * @version 2021/9/2
 */
public class GetKthFromEnd {
    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode tail = head;
        while (k-- > 0) {
            // maybe NullPointerException
            tail = tail.next;
        }
        while (tail != null) {
            head = head.next;
            tail = tail.next;
        }
        return head;
    }
}
