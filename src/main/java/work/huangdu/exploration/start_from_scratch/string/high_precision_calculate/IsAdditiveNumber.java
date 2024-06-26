package work.huangdu.exploration.start_from_scratch.string.high_precision_calculate;

import java.math.BigInteger;

/**
 * 306. 累加数
 * 累加数 是一个字符串，组成它的数字可以形成累加序列。
 * 一个有效的 累加序列 必须 至少 包含 3 个数。除了最开始的两个数以外，字符串中的其他数都等于它之前两个数相加的和。
 * 给你一个只包含数字 '0'-'9' 的字符串，编写一个算法来判断给定输入是否是 累加数 。如果是，返回 true ；否则，返回 false 。
 * 说明：累加序列里的数 不会 以 0 开头，所以不会出现 1, 2, 03 或者 1, 02, 3 的情况。
 * 示例 1：
 * 输入："112358"
 * 输出：true
 * 解释：累加序列为: 1, 1, 2, 3, 5, 8 。1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8
 * 示例 2：
 * 输入："199100199"
 * 输出：true
 * 解释：累加序列为: 1, 99, 100, 199。1 + 99 = 100, 99 + 100 = 199
 * 提示：
 * 1 <= num.length <= 35
 * num 仅由数字（0 - 9）组成
 * 进阶：你计划如何处理由过大的整数输入导致的溢出?
 *
 * @author huangdu
 * @version 2020/10/2 11:20
 */
public class IsAdditiveNumber {
    public boolean isAdditiveNumber(String num) {
        if (num.length() < 3) { return false; }
        int n = num.length();
        for (int j = 2; j < n; j++) {
            for (int i = 1; i < j; i++) {
                if (i + 1 < j && num.charAt(i) == '0') { continue; }
                int index = 0, start = 0, mid = i, end = j;
                while (index != -1) {
                    String target = addStrings(num.substring(start, mid), num.substring(mid, end));
                    index = next(num, end, target);
                    if (index == n) {
                        return true;
                    }
                    start = mid;
                    mid = end;
                    end = index;
                }
            }
        }
        return false;
    }

    private String addStrings(String num1, String num2) {
        int n1 = num1.length(), n2 = num2.length(), i = n1 - 1, j = n2 - 1, add = 0;
        StringBuilder res = new StringBuilder();
        while (i >= 0 || j >= 0 || add != 0) {
            int unit1 = i >= 0 ? num1.charAt(i) - '0' : 0;
            int unit2 = j >= 0 ? num2.charAt(j) - '0' : 0;
            int sum = unit1 + unit2 + add;
            res.append(sum % 10);
            add = sum / 10;
            i--;
            j--;
        }
        return res.reverse().toString();
    }

    private int next(String num, int start, String target) {
        int end = start + target.length();
        if (end > num.length()) { return -1; }
        String next = num.substring(start, end);
        if (target.equals(next)) {
            return end;
        }
        return -1;
    }

    public static void main(String[] args) {
        IsAdditiveNumber add = new IsAdditiveNumber();
        String num = "112358";
        System.out.println(add.isAdditiveNumber(num));
    }

    static class Solution {
        private int n;
        private String num;

        public boolean isAdditiveNumber(String num) {
            this.n = num.length();
            this.num = num;
            return dfs(0, null, null);
        }

        private boolean dfs(int index, BigInteger a, BigInteger b) {
            if (index == n) { return true; }
            final boolean aIsNull = a == null, bIsNull = b == null;
            final int limit = aIsNull ? n - 2 : (bIsNull ? n - 1 : n);
            if (num.charAt(index) == '0') {
                if (aIsNull) {
                    return index < limit && dfs(index + 1, BigInteger.ZERO, null);
                }
                if (bIsNull) {
                    return index < limit && dfs(index + 1, a, BigInteger.ZERO);
                }
                return a.add(b).equals(BigInteger.ZERO);
            }
            for (int i = index; i < limit; i++) {
                BigInteger cur = new BigInteger(num.substring(index, i + 1));
                if (aIsNull) {
                    if (dfs(i + 1, cur, null)) { return true; }
                } else if (bIsNull) {
                    if (dfs(i + 1, a, cur)) {return true;}
                } else {
                    if (a.add(b).equals(cur) && dfs(i + 1, b, cur)) {
                        return true;
                    }
                }
            }
            return false;
        }
    }
}
