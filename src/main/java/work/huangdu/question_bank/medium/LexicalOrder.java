package work.huangdu.question_bank.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 386. 字典序排数
 * 给你一个整数 n ，按字典序返回范围 [1, n] 内所有整数。
 * 你必须设计一个时间复杂度为 O(n) 且使用 O(1) 额外空间的算法。
 * 示例 1：
 * 输入：n = 13
 * 输出：[1,10,11,12,13,2,3,4,5,6,7,8,9]
 * 示例 2：
 * 输入：n = 2
 * 输出：[1,2]
 * 提示：
 * 1 <= n <= 5 * 10^4
 *
 * @author huangdu
 * @version 2022/4/18
 */
public class LexicalOrder {
    public List<Integer> lexicalOrder(int n) {
        int num = 1;
        List<Integer> ans = new ArrayList<>(n);
        while (num <= n) {
            while (num <= n) {
                ans.add(num);
                num *= 10;
            }
            do {
                num /= 10;
            } while (num % 10 == 9 || num + 1 > n);
            if (num == 0) {break;}
            num++;
        }
        return ans;
    }

    public static void main(String[] args) {
        LexicalOrder lo = new LexicalOrder();
        System.out.println(lo.lexicalOrder(100));
    }

    public List<Integer> lexicalOrder2(int n) {
        List<Integer> ans = new ArrayList<>(n);
        for (int i = 1; i < 10; i++) {
            if (!generate(ans, i, n)) {
                break;
            }
        }
        return ans;
    }

    private boolean generate(List<Integer> ans, int prefix, int limit) {
        if (prefix > limit) {
            return false;
        }
        ans.add(prefix);
        for (int i = 0; i < 10; i++) {
            if (!generate(ans, prefix * 10 + i, limit)) {
                return false;
            }
        }
        return true;
    }
}
