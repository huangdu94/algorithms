package work.huangdu.question_bank.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 1780. 判断一个数字是否可以表示成三的幂的和
 * 给你一个整数 n ，如果你可以将 n 表示成若干个不同的三的幂之和，请你返回 true ，否则请返回 false 。
 * 对于一个整数 y ，如果存在整数 x 满足 y == 3^x ，我们称这个整数 y 是三的幂。
 * 示例 1：
 * 输入：n = 12
 * 输出：true
 * 解释：12 = 31 + 32
 * 示例 2：
 * 输入：n = 91
 * 输出：true
 * 解释：91 = 30 + 32 + 34
 * 示例 3：
 * 输入：n = 21
 * 输出：false
 * 提示：
 * 1 <= n <= 10^7
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2022/12/9
 */
public class CheckPowersOfThree {
    public boolean checkPowersOfThree(int n) {
        List<Integer> numList = new ArrayList<>(15);
        int num = 1;
        while (num <= n) {
            numList.add(num);
            num *= 3;
        }
        int size = numList.size(), statusLimit = 1 << size;
        for (int status = 1; status < statusLimit; status++) {
            int sum = 0;
            for (int i = 0; i < size; i++) {
                if (((status >> i) & 1) != 0) {
                    sum += numList.get(i);
                }
            }
            if (sum == n) {
                return true;
            }
        }
        return false;
    }
}
