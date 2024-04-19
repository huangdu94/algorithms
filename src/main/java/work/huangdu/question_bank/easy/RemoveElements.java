package work.huangdu.question_bank.easy;

import work.huangdu.data_structure.ListNode;

/**
 * 203. 移除链表元素
 * 给你一个链表的头节点 head 和一个整数 val ，请你删除链表中所有满足 Node.val == val 的节点，并返回 新的头节点 。
 * 示例 1：
 * 输入：head = [1,2,6,3,4,5,6], val = 6
 * 输出：[1,2,3,4,5]
 * 示例 2：
 * 输入：head = [], val = 1
 * 输出：[]
 * 示例 3：
 * 输入：head = [7,7,7,7], val = 7
 * 输出：[]
 * 提示：
 * 列表中的节点在范围 [0, 10^4] 内
 * 1 <= Node.val <= 50
 * 0 <= k <= 50
 *
 * @author huangdu
 * @version 2020/8/14 0:54
 * @see RemoveElement
 */
public class RemoveElements {
    // 可以尝试递归解法
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode(), cur = dummy;
        while (head != null) {
            if (head.val != val) {
                cur.next = head;
                cur = cur.next;
            }
            head = head.next;
        }
        cur.next = null;
        return dummy.next;
    }

    public ListNode removeElements2(ListNode head, int val) {
        if (head == null) { return null; }
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode cur = dummy;
        while (cur.next != null) {
            if (cur.next.val == val) { cur.next = cur.next.next; } else { cur = cur.next; }
        }
        return dummy.next;
    }

    public ListNode removeElements3(ListNode head, int val) {
        ListNode dummy = new ListNode(-1), prev = dummy;
        dummy.next = head;
        while (head != null) {
            if (head.val == val) {
                prev.next = head.next;
            } else {
                prev = prev.next;
            }
            head = head.next;
        }
        return dummy.next;
    }
}
