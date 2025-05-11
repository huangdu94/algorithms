package work.huangdu.question_bank.difficult;

import java.util.HashSet;
import java.util.Set;

/**
 * 面试题 17.19. 消失的两个数字
 * 给定一个数组，包含从 1 到 N 所有的整数，但其中缺了两个数字。你能在 O(N) 时间内只用 O(1) 的空间找到它们吗？
 * 以任意顺序返回这两个数字均可。
 * 示例 1:
 * 输入: [1]
 * 输出: [2,3]
 * 示例 2:
 * 输入: [2,3]
 * 输出: [1,4]
 * 提示：
 * nums.length <= 30000
 *
 * @author huangdu
 * @version 2022/9/26
 */
public class MissingTwo {
    public int[] missingTwo(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {set.add(num);}
        int idx = 0;
        int[] ans = new int[2];
        for (int i = 1, n = nums.length + 2; i <= n; i++) {
            if (!set.contains(i)) {
                ans[idx++] = i;
                if (idx == 2) {
                    break;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(4 ^ 9);
        System.out.println(5 ^ 8);
    }

    // 学习思路
    public int[] missingTwo2(int[] nums) {
        int xorsum = 0;
        int n = nums.length + 2;
        for (int num : nums) {
            xorsum ^= num;
        }
        for (int i = 1; i <= n; i++) {
            xorsum ^= i;
        }
        // 防止溢出
        int lsb = (xorsum == Integer.MIN_VALUE ? xorsum : xorsum & (-xorsum));
        int type1 = 0, type2 = 0;
        for (int num : nums) {
            if ((num & lsb) != 0) {
                type1 ^= num;
            } else {
                type2 ^= num;
            }
        }
        for (int i = 1; i <= n; i++) {
            if ((i & lsb) != 0) {
                type1 ^= i;
            } else {
                type2 ^= i;
            }
        }
        return new int[]{type1, type2};
    }
}
