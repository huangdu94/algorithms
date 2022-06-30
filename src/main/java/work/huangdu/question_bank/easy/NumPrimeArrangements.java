package work.huangdu.question_bank.easy;

/**
 * 1175. 质数排列
 * 请你帮忙给从 1 到 n 的数设计排列方案，使得所有的「质数」都应该被放在「质数索引」（索引从 1 开始）上；你需要返回可能的方案总数。
 * 让我们一起来回顾一下「质数」：质数一定是大于 1 的，并且不能用两个小于它的正整数的乘积来表示。
 * 由于答案可能会很大，所以请你返回答案 模 mod 10^9 + 7 之后的结果即可。
 * 示例 1：
 * 输入：n = 5
 * 输出：12
 * 解释：举个例子，[1,2,5,4,3] 是一个有效的排列，但 [5,2,3,4,1] 不是，因为在第二种情况里质数 5 被错误地放在索引为 1 的位置上。
 * 示例 2：
 * 输入：n = 100
 * 输出：682289015
 * 提示：
 * 1 <= n <= 100
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2022/6/30
 */
public class NumPrimeArrangements {
    private static final int BASE = 1000000007;

    public int numPrimeArrangements(int n) {
        int primeCount = 0;
        for (int i = 1; i <= n; i++) {
            if (isPrime(i)) {primeCount++;}
        }
        return (int)(factorial(primeCount) * factorial(n - primeCount) % BASE);
    }

    /**
     * 判断n是否为质数
     *
     * @param n 参数
     * @return true 是质数
     */
    private boolean isPrime(int n) {
        if (n <= 3) {return n > 1;}
        if (n % 2 == 0) {return false;}
        /* 6X+1 6X+2 6X+3 6X+4 6X+5
           X=0 6X+1 6X+2 6X+3 6X+4 都排除了
           X>=1 6X+2 6X+4 为偶数排除 6X+3 = 3(2X+1) 排除
           故只需要考虑6X+1 6X+5
         */
        if (n % 6 != 1 && n % 6 != 5) {return false;}
        for (int i = 3; i <= Math.sqrt(n); i += 2) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 求n阶乘
     */
    private static long factorial(int n) {
        long result = 1;
        while (n > 1) {result = result * n-- % BASE;}
        return result;
    }
}
