package work.huangdu.question_bank.difficult;

/**
 * 233. 数字 1 的个数
 * 给定一个整数 n，计算所有小于等于 n 的非负整数中数字 1 出现的个数。
 * 示例 1：
 * 输入：n = 13
 * 输出：6
 * 示例 2：
 * 输入：n = 0
 * 输出：0
 * 提示：
 * 0 <= n <= 10^9
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/8/13
 */
public class CountDigitOne {
    // 思路：统计每一位上1出现的个数
    // 对于数字n，第k位1出现的个数分为两部分求
    // 1. 循环部分 n/10^k 次循环 每次循环出现1的次数 为 10^k-1 次（例如n=100,十位1出现的个数，共循环1次每次循环 10-19，即10次）
    // 2. 剩余部分 n%10^k 分成三种情况，如果剩余部分小于10^k-1，则为0次，剩余部分大于等于10^k-1，即为n%10^k-10^k-1 + 1次，剩余部分大于等于2*10^k-1，为10^k-1次
    // 第k位上1出现的个数即为 (n/10^k*10^k-1) * Math.max(Math.min((n%10^k-10^(k-1)+1),10^(k-1)),0)
    public int countDigitOne(int n) {
        int k = 1, count = 0;
        while (n / k != 0) {
            count += n / (10 * k) * k + Math.max(Math.min(n % (10 * k) - k + 1, k), 0);
            k *= 10;
        }
        return count;
    }

    public static void main(String[] args) {
        CountDigitOne cdo = new CountDigitOne();
        System.out.println(cdo.countDigitOne(10000));
    }
}
