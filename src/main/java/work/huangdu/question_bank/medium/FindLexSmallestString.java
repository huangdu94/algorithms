package work.huangdu.question_bank.medium;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 1625. 执行操作后字典序最小的字符串
 * 给你一个字符串 s 以及两个整数 a 和 b 。其中，字符串 s 的长度为偶数，且仅由数字 0 到 9 组成。
 * 你可以在 s 上按任意顺序多次执行下面两个操作之一：
 * 累加：将  a 加到 s 中所有下标为奇数的元素上（下标从 0 开始）。数字一旦超过 9 就会变成 0，如此循环往复。例如，s = "3456" 且 a = 5，则执行此操作后 s 变成 "3951"。
 * 轮转：将 s 向右轮转 b 位。例如，s = "3456" 且 b = 1，则执行此操作后 s 变成 "6345"。
 * 请你返回在 s 上执行上述操作任意次后可以得到的 字典序最小 的字符串。
 * 如果两个字符串长度相同，那么字符串 a 字典序比字符串 b 小可以这样定义：在 a 和 b 出现不同的第一个位置上，字符串 a 中的字符出现在字母表中的时间早于 b 中的对应字符。例如，"0158” 字典序比 "0190" 小，因为不同的第一个位置是在第三个字符，显然 '5' 出现在 '9' 之前。
 * 示例 1：
 * 输入：s = "5525", a = 9, b = 2
 * 输出："2050"
 * 解释：执行操作如下：
 * 初态："5525"
 * 轮转："2555"
 * 累加："2454"
 * 累加："2353"
 * 轮转："5323"
 * 累加："5222"
 * 累加："5121"
 * 轮转："2151"
 * 累加："2050"
 * 无法获得字典序小于 "2050" 的字符串。
 * 示例 2：
 * 输入：s = "74", a = 5, b = 1
 * 输出："24"
 * 解释：执行操作如下：
 * 初态："74"
 * 轮转："47"
 * 累加："42"
 * 轮转："24"
 * 无法获得字典序小于 "24" 的字符串。
 * 示例 3：
 * 输入：s = "0011", a = 4, b = 2
 * 输出："0011"
 * 解释：无法获得字典序小于 "0011" 的字符串。
 * 示例 4：
 * 输入：s = "43987654", a = 7, b = 3
 * 输出："00553311"
 * 提示：
 * 2 <= s.length <= 100
 * s.length 是偶数
 * s 仅由数字 0 到 9 组成
 * 1 <= a <= 9
 * 1 <= b <= s.length - 1
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2023/3/19
 */
public class FindLexSmallestString {
    private int n;
    private int[] nums;
    private int a;
    private int b;
    private int[] smallest;
    private int aSize;
    private int bSize;

    public String findLexSmallestString(String s, int a, int b) {
        init(s, a, b);
        if ((b & 1) == 0) {
            for (int i = 0; i < aSize; i++) {
                add(1);
                for (int k = 0; k < bSize; k++) {
                    rotate();
                    findSmallest();
                }
            }
        } else {
            for (int i = 0; i < aSize; i++) {
                add(1);
                for (int j = 0; j < aSize; j++) {
                    add(0);
                    for (int k = 0; k < bSize; k++) {
                        rotate();
                        findSmallest();
                    }
                }
            }
        }
        return generateString();
    }

    private void init(String s, int a, int b) {
        this.n = s.length();
        this.nums = new int[n];
        this.a = a;
        this.b = b;
        for (int i = 0; i < n; i++) {nums[i] = s.charAt(i) - '0';}
        this.smallest = Arrays.copyOf(nums, n);
        Set<Integer> aSet = new HashSet<>(), bSet = new HashSet<>();
        int sumA = 0, sumB = 0;
        while (aSet.add(sumA)) {sumA = (sumA + a) % 10;}
        while (bSet.add(sumB)) {sumB = (sumB + b) % n;}
        this.aSize = aSet.size();
        this.bSize = bSet.size();
    }

    private void add(int start) {
        for (int i = start; i < n; i += 2) {
            nums[i] = (nums[i] + a) % 10;
        }
    }

    public void rotate() {
        reverse(0, n - b - 1);
        reverse(n - b, n - 1);
        reverse(0, n - 1);
    }

    private void reverse(int i, int j) {
        while (i < j) {
            int temp = nums[i];
            nums[i++] = nums[j];
            nums[j--] = temp;
        }
    }

    private void findSmallest() {
        boolean flag = false;
        for (int i = 0; i < n; i++) {
            if (!flag) {
                if (smallest[i] < nums[i]) {break;}
                if (smallest[i] > nums[i]) {flag = true;}
            }
            if (flag) {smallest[i] = nums[i];}
        }
    }

    private String generateString() {
        char[] chars = new char[n];
        for (int i = 0; i < n; i++) {
            chars[i] = (char)(smallest[i] + '0');
        }
        return new String(chars);
    }

    public static void main(String[] args) {
        FindLexSmallestString flss = new FindLexSmallestString();
        System.out.println(flss.findLexSmallestString("565510", 7, 2));
    }
}
