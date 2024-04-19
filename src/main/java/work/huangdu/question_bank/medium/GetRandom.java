package work.huangdu.question_bank.medium;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import work.huangdu.data_structure.ListNode;

/**
 * 382. 链表随机节点
 * 给你一个单链表，随机选择链表的一个节点，并返回相应的节点值。每个节点 被选中的概率一样 。
 * 实现 Solution 类：
 * Solution(ListNode head) 使用整数数组初始化对象。
 * int getRandom() 从链表中随机选择一个节点并返回该节点的值。链表中所有节点被选中的概率相等。
 * 示例：
 * 输入
 * ["Solution", "getRandom", "getRandom", "getRandom", "getRandom", "getRandom"]
 * [[[1, 2, 3]], [], [], [], [], []]
 * 输出
 * [null, 1, 3, 2, 2, 3]
 * 解释
 * Solution solution = new Solution([1, 2, 3]);
 * solution.getRandom(); // 返回 1
 * solution.getRandom(); // 返回 3
 * solution.getRandom(); // 返回 2
 * solution.getRandom(); // 返回 2
 * solution.getRandom(); // 返回 3
 * // getRandom() 方法应随机返回 1、2、3中的一个，每个元素被返回的概率相等。
 * 提示：
 * 链表中的节点数在范围 [1, 10^4] 内
 * -10^4 <= Node.val <= 10^4
 * 至多调用 getRandom 方法 10^4 次
 * 进阶：
 * 如果链表非常大且长度未知，该怎么处理？
 * 你能否在不使用额外空间的情况下解决此问题？
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(head);
 * int param_1 = obj.getRandom();
 *
 * @author huangdu
 * @version 2022/1/24
 */
public class GetRandom {
    static class Solution {
        private final int n;
        private final List<Integer> arrayList;
        private final Random rand;

        public Solution(ListNode head) {
            arrayList = new ArrayList<>();
            while (head != null) {
                arrayList.add(head.val);
                head = head.next;
            }
            n = arrayList.size();
            rand = new Random();
        }

        public int getRandom() {
            int randIndex = rand.nextInt(n);
            return arrayList.get(randIndex);
        }
    }

    // 鱼塘抽样法
    static class Solution2 {
        private final ListNode head;
        private final Random rand;

        public Solution2(ListNode head) {
            this.head = head;
            this.rand = new Random();
        }

        public int getRandom() {
            int i = 1, ans = 0;
            ListNode node = head;
            while (node != null) {
                if (rand.nextInt(i) == 0) {
                    ans = node.val;
                }
                node = node.next;
                i++;
            }
            return ans;
        }
    }
}
