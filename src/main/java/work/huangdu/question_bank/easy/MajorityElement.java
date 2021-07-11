package work.huangdu.question_bank.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * 面试题 17.10. 主要元素
 * 数组中占比超过一半的元素称之为主要元素。给你一个 整数 数组，找出其中的主要元素。若没有，返回 -1 。请设计时间复杂度为 O(N) 、空间复杂度为 O(1) 的解决方案。
 * 示例 1：
 * 输入：[1,2,5,9,5,9,5,5,5]
 * 输出：5
 * 示例 2：
 * 输入：[3,2]
 * 输出：-1
 * 示例 3：
 * 输入：[2,2,1,1,1,2,2]
 * 输出：2
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/7/9
 */
public class MajorityElement {
    public int majorityElement(int[] nums) {
        int count = 0, cur = -1;
        for (int num : nums) {
            if (count == 0) {
                cur = num;
            }
            if (cur == num) {
                count++;
            } else {
                count--;
            }
        }
        count = 0;
        for (int num : nums) {
            if (num == cur) {
                count++;
            }
        }
        return count > nums.length / 2 ? cur : -1;
    }

    public int majorityElement2(int[] nums) {
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            int count = map.merge(num, 1, Integer::sum);
            if (count > n / 2) {
                return num;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        MajorityElement me = new MajorityElement();
        int[] nums = {1, 2, 3};
        System.out.println(me.majorityElement(nums));
    }
}
