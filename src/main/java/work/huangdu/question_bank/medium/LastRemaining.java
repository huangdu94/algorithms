package work.huangdu.question_bank.medium;

/**
 * 390. 消除游戏
 * 列表 arr 由在范围 [1, n] 中的所有整数组成，并按严格递增排序。请你对 arr 应用下述算法：
 * 从左到右，删除第一个数字，然后每隔一个数字删除一个，直到到达列表末尾。
 * 重复上面的步骤，但这次是从右到左。也就是，删除最右侧的数字，然后剩下的数字每隔一个删除一个。
 * 不断重复这两步，从左到右和从右到左交替进行，直到只剩下一个数字。
 * 给你整数 n ，返回 arr 最后剩下的数字。
 * 示例 1：
 * 输入：n = 9
 * 输出：6
 * 解释：
 * arr = [1, 2, 3, 4, 5, 6, 7, 8, 9]
 * arr = [2, 4, 6, 8]
 * arr = [2, 6]
 * arr = [6]
 * 示例 2：
 * 输入：n = 1
 * 输出：1
 * 提示：
 * 1 <= n <= 10^9
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2022/1/4
 */
public class LastRemaining {
    // 模拟
    public int lastRemaining(int n) {
        int count = n, head = 1, step = 1, round = 1;
        while (count > 1) {
            head += round * (count - 1 - (count & 1)) * step;
            step <<= 1;
            count >>= 1;
            round *= -1;
        }
        return head;
    }

    public static void main(String[] args) {
        LastRemaining lr = new LastRemaining();
        System.out.println(lr.lastRemaining(100));
    }
}
