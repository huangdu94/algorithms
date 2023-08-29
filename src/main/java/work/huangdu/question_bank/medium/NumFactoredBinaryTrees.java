package work.huangdu.question_bank.medium;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 823. 带因子的二叉树
 * 给出一个含有不重复整数元素的数组 arr ，每个整数 arr[i] 均大于 1。
 * 用这些整数来构建二叉树，每个整数可以使用任意次数。其中：每个非叶结点的值应等于它的两个子结点的值的乘积。
 * 满足条件的二叉树一共有多少个？答案可能很大，返回 对 109 + 7 取余 的结果。
 * 示例 1:
 * 输入: arr = [2, 4]
 * 输出: 3
 * 解释: 可以得到这些二叉树: [2], [4], [4, 2, 2]
 * 示例 2:
 * 输入: arr = [2, 4, 5, 10]
 * 输出: 7
 * 解释: 可以得到这些二叉树: [2], [4], [5], [10], [4, 2, 2], [10, 2, 5], [10, 5, 2].
 * 提示：
 * 1 <= arr.length <= 1000
 * 2 <= arr[i] <= 10^9
 * arr 中的所有值 互不相同
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2023/8/29
 */
public class NumFactoredBinaryTrees {
    public int numFactoredBinaryTrees(int[] arr) {
        int ans = 0;
        final int mod = 1000000007;
        Map<Integer, Integer> memo = new HashMap<>(arr.length);
        Arrays.sort(arr);
        for (int target : arr) {
            int result = 1, bound = (int)Math.sqrt(target);
            for (int num1 : arr) {
                if (num1 > bound) {break;}
                if (target % num1 == 0 && memo.containsKey(target / num1)) {
                    if (num1 == target / num1) {
                        result = (result + (int)((long)memo.get(num1) * memo.get(num1) % mod)) % mod;
                    } else {
                        result = (result + (int)((long)memo.get(num1) * memo.get(target / num1) * 2 % mod)) % mod;
                    }

                }
            }
            memo.put(target, result);
            ans = (ans + result) % mod;
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 87, 136, 144, 150, 240, 352, 380,
            476, 540, 544, 546, 585, 690, 799, 1056, 1078, 1296, 1400, 1457, 1968, 2162, 2205, 2730, 3128, 4410, 4488, 5040, 5550, 6000, 7056, 8704, 10912, 11616, 18720, 20774, 21645, 24150, 34496, 49588, 61600, 91872, 92928, 197472,
            557568, 1689120, 1883700, 3063808, 16262400, 32710656, 523370496};
        NumFactoredBinaryTrees nfbt = new NumFactoredBinaryTrees();
        System.out.println(nfbt.numFactoredBinaryTrees(arr));
    }
}
