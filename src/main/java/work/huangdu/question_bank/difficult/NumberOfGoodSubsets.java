package work.huangdu.question_bank.difficult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 1994. 好子集的数目
 * 给你一个整数数组 nums 。如果 nums 的一个子集中，所有元素的乘积可以表示为一个或多个 互不相同的质数 的乘积，那么我们称它为 好子集 。
 * 比方说，如果 nums = [1, 2, 3, 4] ：
 * [2, 3] ，[1, 2, 3] 和 [1, 3] 是 好 子集，乘积分别为 6 = 2*3 ，6 = 2*3 和 3 = 3 。
 * [1, 4] 和 [4] 不是 好 子集，因为乘积分别为 4 = 2*2 和 4 = 2*2 。
 * 请你返回 nums 中不同的 好 子集的数目对 10^9 + 7 取余 的结果。
 * nums 中的 子集 是通过删除 nums 中一些（可能一个都不删除，也可能全部都删除）元素后剩余元素组成的数组。如果两个子集删除的下标不同，那么它们被视为不同的子集。
 * 示例 1：
 * 输入：nums = [1,2,3,4]
 * 输出：6
 * 解释：好子集为：
 * - [1,2]：乘积为 2 ，可以表示为质数 2 的乘积。
 * - [1,2,3]：乘积为 6 ，可以表示为互不相同的质数 2 和 3 的乘积。
 * - [1,3]：乘积为 3 ，可以表示为质数 3 的乘积。
 * - [2]：乘积为 2 ，可以表示为质数 2 的乘积。
 * - [2,3]：乘积为 6 ，可以表示为互不相同的质数 2 和 3 的乘积。
 * - [3]：乘积为 3 ，可以表示为质数 3 的乘积。
 * 示例 2：
 * 输入：nums = [4,2,3,15]
 * 输出：5
 * 解释：好子集为：
 * - [2]：乘积为 2 ，可以表示为质数 2 的乘积。
 * - [2,3]：乘积为 6 ，可以表示为互不相同质数 2 和 3 的乘积。
 * - [2,15]：乘积为 30 ，可以表示为互不相同质数 2，3 和 5 的乘积。
 * - [3]：乘积为 3 ，可以表示为质数 3 的乘积。
 * - [15]：乘积为 15 ，可以表示为互不相同质数 3 和 5 的乘积。
 * 提示：
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 30
 *
 * @author huangdu
 * @version 2022/2/22
 */
public class NumberOfGoodSubsets {
    private static final int BASE = 1000000007;
    private static final Set<Integer> EXCLUDE = new HashSet<>(Arrays.asList(4, 8, 9, 12, 16, 18, 20, 24, 25, 27, 28));
    private static final Map<Integer, Integer> PRIME_MASK_MAP = new HashMap<>();

    static {
        PRIME_MASK_MAP.put(2, 1);
        PRIME_MASK_MAP.put(3, 1 << 1);
        PRIME_MASK_MAP.put(5, 1 << 2);
        PRIME_MASK_MAP.put(7, 1 << 3);
        PRIME_MASK_MAP.put(11, 1 << 4);
        PRIME_MASK_MAP.put(13, 1 << 5);
        PRIME_MASK_MAP.put(17, 1 << 6);
        PRIME_MASK_MAP.put(19, 1 << 7);
        PRIME_MASK_MAP.put(23, 1 << 8);
        PRIME_MASK_MAP.put(29, 1 << 9);
        PRIME_MASK_MAP.put(6, 1 + (1 << 1));
        PRIME_MASK_MAP.put(10, 1 + (1 << 2));
        PRIME_MASK_MAP.put(14, 1 + (1 << 3));
        PRIME_MASK_MAP.put(15, (1 << 1) + (1 << 2));
        PRIME_MASK_MAP.put(21, (1 << 1) + (1 << 3));
        PRIME_MASK_MAP.put(22, 1 + (1 << 4));
        PRIME_MASK_MAP.put(26, 1 + (1 << 5));
        PRIME_MASK_MAP.put(30, 1 + (1 << 1) + (1 << 2));
    }

    /**
     * 1
     * 排除在外的：4,8,9,12,16,18,20,24,25,27,28
     * 6 = 2*3
     * 10 = 2*5
     * 14 = 2*7
     * 15 = 3*5
     * 21 = 3*7
     * 22 = 2*11
     * 26 = 2*13
     * 30 = 2*3*5
     *
     * 最后结果要乘 2^(1的个数)
     */
    public int numberOfGoodSubsets(int[] nums) {
        int[] counts = new int[31];
        for (int num : nums) {counts[num]++;}
        List<Integer> existNumList = new ArrayList<>();
        for (int num = 2; num <= 30; num++) {
            if (EXCLUDE.contains(num)) {continue;}
            if (counts[num] > 0) {existNumList.add(num);}
        }
        if (existNumList.isEmpty()) {return 0;}
        int amount = existNumList.size(), boundary = 1 << amount;
        long ans = 0;
        for (int selected = 1; selected < boundary; selected++) {
            int visited = 0;
            boolean valid = true;
            for (int offset = 0; offset < amount; offset++) {
                if ((selected & 1 << offset) != 0) {
                    int num = existNumList.get(offset);
                    int mask = PRIME_MASK_MAP.get(num);
                    if ((visited & mask) != 0) {
                        valid = false;
                        break;
                    }
                    visited |= mask;
                }
            }
            if (valid) {
                long res = 1;
                for (int offset = 0; offset < amount; offset++) {
                    if ((selected & 1 << offset) != 0) {
                        res = res * counts[existNumList.get(offset)] % BASE;
                    }
                }
                ans = (ans + res) % BASE;
            }
        }
        return (int)(ans * twoPow(counts[1]) % BASE);
    }

    private int twoPow(int n) {
        if (n == 0) {return 1;}
        long res = 1, multiple = 2;
        while (n != 0) {
            if ((n & 1) == 1) {
                res = res * multiple % BASE;
            }
            multiple = multiple * multiple % BASE;
            n >>= 1;
        }
        return (int)res;
    }

    public static void main(String[] args) {
        NumberOfGoodSubsets nogs = new NumberOfGoodSubsets();
        // int[] nums={1,2,3,4};
        int[] nums = {2, 17, 8, 1, 30, 26, 6, 2, 5, 10, 28, 15, 11, 15, 25, 24, 24, 13, 23, 27, 23, 24, 20, 1, 25, 1, 21, 23, 10, 21, 12, 14, 13, 26, 18, 21, 12, 14, 26, 8, 16, 11, 21, 8, 9, 5, 3, 25, 2, 14, 23, 23, 16, 8, 19, 5, 9, 26, 17, 15, 15,
            17, 9, 18, 25, 14, 10, 30, 20, 21, 23, 19, 11, 21, 25, 8, 25, 1, 5, 17, 30, 4, 6, 2, 22, 18, 10, 18, 30, 12, 8, 6, 18, 23, 22, 4, 23, 27, 23, 27, 19, 11, 25, 20, 30, 16, 29, 1, 22, 26, 19, 2, 13, 8, 19, 23, 3, 27, 20, 18, 11, 2, 23, 3, 5,
            16, 3, 23, 22, 7, 24, 25, 16, 13, 5, 17, 24, 14, 10, 19, 11, 29, 8, 16, 5, 14, 8, 26, 15, 7, 3, 11, 19, 12, 27, 24, 1, 6, 4, 16, 8, 7, 25, 12, 21, 20, 15, 25, 29, 6, 25, 5, 28, 10, 3, 27, 19, 12, 1, 13, 22, 5, 13, 5, 18, 6, 2, 10, 24, 7,
            14, 7, 26, 10, 4, 17, 4, 24, 6, 5, 5, 1, 7, 5, 14, 18, 23, 10, 20, 7, 2, 28, 15, 30, 16, 8, 24, 11, 2, 4, 13, 10, 29, 11, 20, 8, 3, 17, 8, 4, 26, 26, 5, 16, 30, 7, 7, 22, 22, 14, 3, 29, 20, 17, 27, 2, 20, 23, 1, 7, 4, 16, 9, 15, 15, 23,
            8, 11, 14, 18, 12, 3, 26, 27, 13, 17, 20, 27, 11, 5, 14, 12, 18, 19, 21, 20, 4, 27, 5, 17, 19, 5, 1, 18, 22, 16, 8, 29, 21, 3, 20, 17, 3, 15, 29, 4, 27, 2, 10};
        // 894122736
        System.out.println(nogs.numberOfGoodSubsets(nums));
    }
}

/*
 * TODO 使用以下思路解一遍
 * 方法一：状态压缩动态规划
 * 思路与算法
 * 注意到题目规定数组 nums 中的元素不超过 30，因此我们可以将 [1,30] 中的整数分成如下三类：
 * 1：对于任意一个好子集而言，我们添加任意数目的 1，得到的新子集仍然是好子集；
 * 2,3,5,6,7,10,11,13,14,15,17,19,21,22,23,26,29,30： 这些数均不包含平方因子，因此每个数在好子集中至多出现一次；
 * 4,8,9,12,16,18,20,24,25,27,284,8,9,12,16,18,20,24,25,27,28：这些数包含平方因子，因此一定不能在好子集中出现。
 * 我们可以通过硬编码的方式把 [1,30] 中的整数按照上述分类，也可以先预处理出所有 [1,30] 中质数 2,3,5,7,11,13,17,19,23,29，再通过试除的方式动态分类。
 * 分类完成后，我们就可以考虑动态规划了。由于每个质因数只能出现一次，并且 [1,30] 中一共有 10 个质数，因此我们可以用一个长度为 10 的二进制数 mask 表示这些质因数的使用情况，其中 mask 的第 i 位为 1 当且仅当第 i 个质数已经被使用过。
 * 这样一来，我们定义 f[i][mask] 表示当我们只选择 [2,i] 范围内的数，并且选择的数的质因数使用情况为 mask 时的方案数。如果 i 本身包含平方因子，那么我们无法选择 i，相当于在 [2,i−1] 范围内选择，状态转移方程为：
 * f[i][mask]=f[i−1][mask]
 * 如果 i 本身不包含平方因子，记其包含的质因子的二进制表示为 subset（同样可以通过试除的方法得到），那么状态转移方程为：
 * f[i][mask]=f[i−1][mask]+f[i−1][mask\subset]×freq[i]
 * 其中：
 * freq[i] 表示数组 nums 中 i 出现的次数；
 * mask\subset 表示从二进制表示 mask 中去除所有在 subset 中出现的 1，可以使用按位异或运算实现。这里需要保证 subset 是 mask 的子集，可以使用按位与运算来判断。
 * 动态规划的边界条件为：
 * f[1][0]=2^freq[1]
 * 即每一个在数组 nums 中出现的 1 都可以选或不选。最终的答案即为所有 f[30][..] 中除了 f[30][0] 以外的项的总和。
 * 细节
 * 注意到 f[i][mask] 只会从 f[i−1][..] 转移而来，并且 f[i−1][..] 中的下标总是小于 mask，因此我们可以使用类似 0−1 背包的空间优化方法，在遍历 mask 时从 2^10-1
 * 到 1 逆序遍历，这样就只需要使用一个长度为 2^10
 * 的一维数组做状态转移了。
 */