package work.huangdu.question_bank.difficult;

/**
 * 440. 字典序的第K小数字
 * 给定整数 n 和 k，返回  [1, n] 中字典序第 k 小的数字。
 * 示例 1:
 * 输入: n = 13, k = 2
 * 输出: 10
 * 解释: 字典序的排列是 [1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9]，所以第二小的数字是 10。
 * 示例 2:
 * 输入: n = 1, k = 1
 * 输出: 1
 * 提示:
 * 1 <= k <= n <= 10^9
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2022/3/29
 */
public class FindKthNumber {
    // TODO 未提交
    public int findKthNumber(int n, int k) {
        int cur = 1;
        while (k > 1) {
            int count = count(cur, n);
            if (count < k) {
                cur++;
                k -= count;
            } else {
                cur *= 10;
                k--;
            }
        }
        return cur;
    }

    private int count(long root, int n) {
        int count = 1;
        long left = root * 10, right = left + 9;
        while (left <= n) {
            count += Math.min(n, right) - left + 1;
            left *= 10;
            right = right * 10 + 9;
        }
        return count;
    }

    public static void main(String[] args) {
        FindKthNumber fkn = new FindKthNumber();
        System.out.println(fkn.findKthNumber(13, 2));
    }
}
