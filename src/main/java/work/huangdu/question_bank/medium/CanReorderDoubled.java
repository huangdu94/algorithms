package work.huangdu.question_bank.medium;

/**
 * 954. 二倍数对数组
 * 给定一个长度为偶数的整数数组 arr，只有对 arr 进行重组后可以满足 “对于每个 0 <= i < len(arr) / 2，都有 arr[2 * i + 1] = 2 * arr[2 * i]” 时，返回 true；否则，返回 false。
 * 示例 1：
 * 输入：arr = [3,1,3,6]
 * 输出：false
 * 示例 2：
 * 输入：arr = [2,1,2,6]
 * 输出：false
 * 示例 3：
 * 输入：arr = [4,-2,2,-4]
 * 输出：true
 * 解释：可以用 [-2,-4] 和 [2,4] 这两组组成 [-2,-4,2,4] 或是 [2,4,-2,-4]
 * 提示：
 * 0 <= arr.length <= 3 * 10^4
 * arr.length 是偶数
 * -10^5 <= arr[i] <= 10^5
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2022/4/1
 */
public class CanReorderDoubled {
    public boolean canReorderDoubled(int[] arr) {
        int[] counts = new int[200001];
        for (int num : arr) {counts[num + 100000]++;}
        if ((counts[100000] & 1) != 0) {return false;}
        for (int num = -1; num >= -100000; num--) {
            int i = num + 100000;
            if (counts[i] == 0) {continue;}
            int i2 = num * 2 + 100000;
            if (i2 < 0 || counts[i] > counts[i2]) {return false;}
            counts[i2] -= counts[i];
        }
        for (int num = 1; num <= 100000; num++) {
            int i = num + 100000;
            if (counts[i] == 0) {continue;}
            int i2 = num * 2 + 100000;
            if (i2 > 200000 || counts[i] > counts[i2]) {return false;}
            counts[i2] -= counts[i];
        }
        return true;
    }
}
