package work.huangdu.question_bank.medium;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import work.huangdu.data_structure.ListNode;

/**
 * 147. 对链表进行插入排序
 * 对链表进行插入排序。
 * 插入排序的动画演示如上。从第一个元素开始，该链表可以被认为已经部分排序（用黑色表示）。
 * 每次迭代时，从输入数据中移除一个元素（用红色表示），并原地将其插入到已排好序的链表中。
 * 插入排序算法：
 * 插入排序是迭代的，每次只移动一个元素，直到所有元素可以形成一个有序的输出列表。
 * 每次迭代中，插入排序只从输入数据中移除一个待排序的元素，找到它在序列中适当的位置，并将其插入。
 * 重复直到所有输入数据插入完为止。
 * 示例 1：
 * 输入: 4->2->1->3
 * 输出: 1->2->3->4
 * 示例 2：
 * 输入: -1->5->3->4->0
 * 输出: -1->0->3->4->5
 *
 * @author huangdu
 * @version 2020/11/20 12:45
 */
public class InsertionSortList {
    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) {return head;}
        // 排序完链表的头，从大到小，排序完后需要翻转链表
        ListNode sortedHead = head;
        head = head.next;
        sortedHead.next = null;
        while (head != null) {
            ListNode next = head.next, insert = sortedHead;
            if (head.val >= insert.val) {
                sortedHead = head;
                head.next = insert;
            } else {
                while (insert.next != null && head.val < insert.next.val) {
                    insert = insert.next;
                }
                head.next = insert.next;
                insert.next = head;
            }
            head = next;
        }
        // 翻转sortedHead
        ListNode pre = null, cur = sortedHead;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    public ListNode insertionSortList2(ListNode head) {
        if (head == null || head.next == null) {return head;}
        // 排序完链表的头，从小到大
        ListNode sortedHead = head;
        head = head.next;
        sortedHead.next = null;
        while (head != null) {
            ListNode next = head.next, insert = sortedHead;
            if (head.val <= insert.val) {
                sortedHead = head;
                head.next = insert;
            } else {
                while (insert.next != null && head.val > insert.next.val) {
                    insert = insert.next;
                }
                head.next = insert.next;
                insert.next = head;
            }
            head = next;
        }
        return sortedHead;
    }

    public ListNode insertionSortList3(ListNode head) {
        if (head == null || head.next == null) {return head;}
        List<ListNode> list = new ArrayList<>();
        while (head != null) {
            list.add(head);
            head = head.next;
        }
        list.sort(Comparator.comparingInt(o -> o.val));
        ListNode dummy = new ListNode(-1), cur = dummy;
        for (ListNode node : list) {
            cur.next = node;
            cur = cur.next;
        }
        cur.next = null;
        return dummy.next;
    }
}
