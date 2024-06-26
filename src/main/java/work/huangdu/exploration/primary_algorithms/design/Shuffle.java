package work.huangdu.exploration.primary_algorithms.design;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * 384. 打乱数组
 * 打乱一个没有重复元素的数组。
 * 示例:
 * // 以数字集合 1, 2 和 3 初始化数组。
 * int[] nums = {1,2,3};
 * Solution solution = new Solution(nums);
 * // 打乱数组 [1,2,3] 并返回结果。任何 [1,2,3]的排列返回的概率应该相同。
 * solution.shuffle();
 * // 重设数组到它的初始状态[1,2,3]。
 * solution.reset();
 * // 随机返回数组[1,2,3]打乱后的结果。
 * solution.shuffle();
 *
 * @author huangdu
 * @version 2020/6/29 13:31
 */
public class Shuffle {
    private final int[] origin;
    private final int[] shuffle;

    public Shuffle(int[] nums) {
        this.origin = nums;
        this.shuffle = new int[nums.length];
        System.arraycopy(origin, 0, shuffle, 0, origin.length);
    }

    /**
     * Resets the array to its original configuration and return it.
     */
    public int[] reset() {
        return origin;
    }

    /**
     * Returns a random shuffling of the array.
     */
    public int[] shuffle() {
        Random random = new Random();
        int n = shuffle.length;
        while (n > 1) {
            int index = random.nextInt(n);
            int temp = shuffle[index];
            shuffle[index] = shuffle[n - 1];
            shuffle[n - 1] = temp;
            n--;
        }
        return shuffle;
    }

    public static void main(String[] args) {
        Shuffle shuffle = new Shuffle(new int[] {1, 2, 3});
        System.out.println(Arrays.toString(shuffle.shuffle()));
        System.out.println(Arrays.toString(shuffle.reset()));
        System.out.println(Arrays.toString(shuffle.shuffle()));
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int[] param_1 = obj.reset();
 * int[] param_2 = obj.shuffle();
 */

class Solution {
    private final int[] origin;

    public Solution(int[] nums) {
        this.origin = nums;
    }

    public int[] reset() {
        return origin;
    }

    public int[] shuffle() {
        List<Integer> list = new ArrayList<>(origin.length);
        for (int num : origin) {
            list.add(num);
        }
        Collections.shuffle(list);
        int i = 0;
        int[] result = new int[origin.length];
        for (int num : list) {
            result[i++] = num;
        }
        return result;
    }
}

class Solution2 {
    private final int[] nums;

    public Solution2(int[] nums) {
        if (nums == null) { this.nums = new int[0]; } else { this.nums = nums; }
    }

    public int[] reset() {
        return nums;
    }

    public int[] shuffle() {
        new Random().nextInt(1);
        int len = nums.length;
        int[] result = new int[len];
        boolean[] flagArr = new boolean[len];
        for (int i = 0; i < len; i++) {
            int index;
            do {
                index = (int)(Math.random() * len);
            } while (flagArr[index]);
            flagArr[index] = true;
            result[i] = nums[index];
        }
        return result;
    }

    public static void main(String[] args) {
        Shuffle shuffle = new Shuffle(new int[] {1, 2, 3});
        System.out.println(Arrays.toString(shuffle.shuffle()));
        System.out.println(Arrays.toString(shuffle.reset()));
        System.out.println(Arrays.toString(shuffle.shuffle()));
    }
}