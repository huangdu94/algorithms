package work.huangdu.question_bank.medium;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 788. 旋转数字
 * 我们称一个数 X 为好数, 如果它的每位数字逐个地被旋转 180 度后，我们仍可以得到一个有效的，且和 X 不同的数。要求每位数字都要被旋转。
 * 如果一个数的每位数字被旋转以后仍然还是一个数字， 则这个数是有效的。0, 1, 和 8 被旋转后仍然是它们自己；2 和 5 可以互相旋转成对方（在这种情况下，它们以不同的方向旋转，换句话说，2 和 5 互为镜像）；6 和 9 同理，除了这些以外其他的数字旋转以后都不再是有效的数字。
 * 现在我们有一个正整数 N, 计算从 1 到 N 中有多少个数 X 是好数？
 * 示例：
 * 输入: 10
 * 输出: 4
 * 解释:
 * 在[1, 10]中有四个好数： 2, 5, 6, 9。
 * 注意 1 和 10 不是好数, 因为他们在旋转之后不变。
 * 提示：
 * N 的取值范围是 [1, 10000]。
 *
 * @author huangdu
 * @version 2022/9/27
 */
public class RotatedDigits {
    private int lastKey = 0;
    private final int[] cache = new int[10001];
    private final Set<Integer> variantSet = new HashSet<>(Arrays.asList(2, 5, 6, 9));
    private final Set<Integer> invariantSet = new HashSet<>(Arrays.asList(0, 1, 8));

    public int rotatedDigits(int n) {
        if (n > lastKey) {
            for (int num = lastKey + 1; num <= n; num++) {
                cache[num] = cache[num - 1] + check(num);
            }
        }
        lastKey = n;
        return cache[n];
    }

    public int rotatedDigits2(int n) {
        int ans = 0;
        for (int num = 1; num <= n; num++) {
            ans += check(num);
        }
        return ans;
    }

    private int check(int num) {
        int flag = 0;
        while (num > 0) {
            int n = num % 10;
            num /= 10;
            if (variantSet.contains(n)) {
                flag = 1;
                continue;
            }
            if (!invariantSet.contains(n)) {
                return 0;
            }
        }
        return flag;
    }

    public static void main(String[] args) {
        RotatedDigits rd = new RotatedDigits();
        System.out.println(rd.rotatedDigits(10000));
    }
}
