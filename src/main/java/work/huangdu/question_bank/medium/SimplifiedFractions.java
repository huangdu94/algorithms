package work.huangdu.question_bank.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 1447. 最简分数
 * 给你一个整数 n ，请你返回所有 0 到 1 之间（不包括 0 和 1）满足分母小于等于  n 的 最简 分数 。分数可以以 任意 顺序返回。
 * 示例 1：
 * 输入：n = 2
 * 输出：["1/2"]
 * 解释："1/2" 是唯一一个分母小于等于 2 的最简分数。
 * 示例 2：
 * 输入：n = 3
 * 输出：["1/2","1/3","2/3"]
 * 示例 3：
 * 输入：n = 4
 * 输出：["1/2","1/3","1/4","2/3","3/4"]
 * 解释："2/4" 不是最简分数，因为它可以化简为 "1/2" 。
 * 示例 4：
 * 输入：n = 1
 * 输出：[]
 * 提示：
 * 1 <= n <= 100
 *
 * @author huangdu
 * @version 2022/2/10
 */
public class SimplifiedFractions {
    public List<String> simplifiedFractions(int n) {
        List<String> ans = new ArrayList<>();
        // 分子 numerator denominator
        for (int denominator = 2; denominator <= n; denominator++) {
            for (int numerator = 1; numerator < denominator; numerator++) {
                if (gcd(denominator, numerator) == 1) {
                    ans.add(numerator + "/" + denominator);
                }
            }
        }
        return ans;
    }

    // a > b
    private int gcd(int a, int b) {
        while (a % b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return b;
    }
}
