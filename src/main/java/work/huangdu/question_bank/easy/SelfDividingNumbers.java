package work.huangdu.question_bank.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * 728. 自除数
 * 自除数 是指可以被它包含的每一位数整除的数。
 * 例如，128 是一个 自除数 ，因为 128 % 1 == 0，128 % 2 == 0，128 % 8 == 0。
 * 自除数 不允许包含 0 。
 * 给定两个整数 left 和 right ，返回一个列表，列表的元素是范围 [left, right] 内所有的 自除数 。
 * 示例 1：
 * 输入：left = 1, right = 22
 * 输出：[1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 15, 22]
 * 示例 2：
 * 输入：left = 47, right = 85
 * 输出：[48,55,66,77]
 * 提示：
 * 1 <= left <= right <= 10^4
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2022/3/31
 */
public class SelfDividingNumbers {
    /**
     * 检查是否是自除数
     */
    private static boolean check(int num) {
        int num0 = num;
        while (num0 > 0) {
            int n = num0 % 10;
            if (n == 0 || num % n != 0) {
                return false;
            }
            num0 /= 10;
        }
        return true;
    }

    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> ans = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            if (check(i)) {
                ans.add(i);
            }
        }
        return ans;
    }
}
