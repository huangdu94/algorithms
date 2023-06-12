package work.huangdu.question_bank.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * 1171. 从链表中删去总和值为零的连续节点
 * 给你一个链表的头节点 head，请你编写代码，反复删去链表中由 总和 值为 0 的连续节点组成的序列，直到不存在这样的序列为止。
 * 删除完毕后，请你返回最终结果链表的头节点。
 * 你可以返回任何满足题目要求的答案。
 * （注意，下面示例中的所有序列，都是对 ListNode 对象序列化的表示。）
 * 示例 1：
 * 输入：head = [1,2,-3,3,1]
 * 输出：[3,1]
 * 提示：答案 [1,2,1] 也是正确的。
 * 示例 2：
 * 输入：head = [1,2,3,-3,4]
 * 输出：[1,2,4]
 * 示例 3：
 * 输入：head = [1,2,3,-3,-2]
 * 输出：[1]
 * 提示：
 * 给你的链表中可能有 1 到 1000 个节点。
 * 对于链表中的每个节点，节点的值：-1000 <= node.val <= 1000.
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2023/6/12
 */
public class RemoveZeroSumSublists {
    public ListNode removeZeroSumSublists(ListNode head) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        int sum = 0;
        Map<Integer, ListNode> map = new HashMap<>();
        map.put(0, dummy);
        while (head != null) {
            sum += head.val;
            if (map.containsKey(sum)) {
                ListNode start = map.get(sum), p = start.next;
                int s = sum;
                while (p != head) {
                    map.remove(s += p.val);
                    p = p.next;
                }
                start.next = head.next;
            } else {
                map.put(sum, head);
            }
            head = head.next;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        //[1,3,2,-3,-2,5,5,-5,1]
        ListNode head = new ListNode(1);
        ListNode n1 = new ListNode(3);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(-3);
        ListNode n4 = new ListNode(-2);
        ListNode n5 = new ListNode(5);
        ListNode n6 = new ListNode(5);
        ListNode n7 = new ListNode(-5);
        ListNode n8 = new ListNode(1);
        head.next = n1;
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        n6.next = n7;
        n7.next = n8;
        System.out.println(new RemoveZeroSumSublists().removeZeroSumSublists(head));
    }
}
