package work.huangdu.question_bank.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 830. 较大分组的位置
 * 在一个由小写字母构成的字符串 s 中，包含由一些连续的相同字符所构成的分组。
 * 例如，在字符串 s = "abbxxxxzyy" 中，就含有 "a", "bb", "xxxx", "z" 和 "yy" 这样的一些分组。
 * 分组可以用区间 [start, end] 表示，其中 start 和 end 分别表示该分组的起始和终止位置的下标。上例中的 "xxxx" 分组用区间表示为 [3,6] 。
 * 我们称所有包含大于或等于三个连续字符的分组为 较大分组 。
 * 找到每一个 较大分组 的区间，按起始位置下标递增顺序排序后，返回结果。
 * 示例 1：
 * 输入：s = "abbxxxxzzy"
 * 输出：[[3,6]]
 * 解释："xxxx" 是一个起始于 3 且终止于 6 的较大分组。
 * 示例 2：
 * 输入：s = "abc"
 * 输出：[]
 * 解释："a","b" 和 "c" 均不是符合要求的较大分组。
 * 示例 3：
 * 输入：s = "abcdddeeeeaabbbcd"
 * 输出：[[3,5],[6,9],[12,14]]
 * 解释：较大分组为 "ddd", "eeee" 和 "bbb"
 * 示例 4：
 * 输入：s = "aba"
 * 输出：[]
 * 提示：
 * 1 <= s.length <= 1000
 * s 仅含小写英文字母
 *
 * @author huangdu
 * @version 2021/1/5 17:18
 */
public class LargeGroupPositions {
    public List<List<Integer>> largeGroupPositions(String s) {
        if (s.length() < 3) return new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        s = s.concat(".");
        int n = s.length(), pre = s.charAt(0), count = 1;
        for (int i = 1; i < n; i++) {
            int cur = s.charAt(i);
            if (cur == pre) {
                count++;
            } else {
                if (count >= 3) {
                    result.add(Arrays.asList(i - count, i - 1));
                }
                pre = cur;
                count = 1;
            }
        }
        return result;
    }

    public List<List<Integer>> largeGroupPositions2(String s) {
        List<List<Integer>> ans = new ArrayList<>();
        int n = s.length(), start = -1, end = -1;
        char cur = '\000';
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (ch == cur) {
                end = i;
            }
            if (i == n - 1 || ch != cur) {
                if (end - start + 1 >= 3) {
                    ans.add(Arrays.asList(start, end));
                }
                start = end = i;
                cur = ch;
            }
        }
        return ans;
    }
}
