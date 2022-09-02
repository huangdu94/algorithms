package work.huangdu.question_bank.difficult;

/**
 * 793. 阶乘函数后 K 个零
 * f(x) 是 x! 末尾是 0 的数量。回想一下 x! = 1 * 2 * 3 * ... * x，且 0! = 1 。
 * 例如， f(3) = 0 ，因为 3! = 6 的末尾没有 0 ；而 f(11) = 2 ，因为 11!= 39916800 末端有 2 个 0 。
 * 给定 k，找出返回能满足 f(x) = k 的非负整数 x 的数量。
 * 示例 1：
 * 输入：k = 0
 * 输出：5
 * 解释：0!, 1!, 2!, 3!, 和 4! 均符合 k = 0 的条件。
 * 示例 2：
 * 输入：k = 5
 * 输出：0
 * 解释：没有匹配到这样的 x!，符合 k = 5 的条件。
 * 示例 3:
 * 输入: k = 3
 * 输出: 5
 * 提示:
 * 0 <= k <= 10^9
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2022/9/2
 */
public class PreimageSizeFZF {
    /*
    思路
    求出x!末尾0是k+1个的最小数 n_(k+1)
    求出x!末尾0是k个的最小数   n_k
    使用 n_(k+1) - n_k即为结果数
    使用二分查找来求n，二分查找的边界如果想不通可以直接用Long.MAX_VALUE
    想通了可以直接用5x
     */
    public int preimageSizeFZF(int k) {
        return (int)(binarySearch(k + 1) - binarySearch(k));
    }

    private long binarySearch(int x) {
        long l = 0, r = Long.MAX_VALUE;
        while (l < r) {
            long mid = l + (r - l >> 1);
            if (trailingZeroes(mid) < x) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }

    public long trailingZeroes(long n) {
        long result = 0;
        while (n >= 5) {result += (n /= 5);}
        return result;
    }
}
