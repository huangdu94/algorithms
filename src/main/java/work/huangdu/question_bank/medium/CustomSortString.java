package work.huangdu.question_bank.medium;

import java.util.Arrays;

/**
 * 791. 自定义字符串排序
 * 给定两个字符串 order 和 s 。order 的所有单词都是 唯一 的，并且以前按照一些自定义的顺序排序。
 * 对 s 的字符进行置换，使其与排序的 order 相匹配。更具体地说，如果在 order 中的字符 x 出现字符 y 之前，那么在排列后的字符串中， x 也应该出现在 y 之前。
 * 返回 满足这个性质的 s 的任意排列 。
 * 示例 1:
 * 输入: order = "cba", s = "abcd"
 * 输出: "cbad"
 * 解释:
 * “a”、“b”、“c”是按顺序出现的，所以“a”、“b”、“c”的顺序应该是“c”、“b”、“a”。
 * 因为“d”不是按顺序出现的，所以它可以在返回的字符串中的任何位置。“dcba”、“cdba”、“cbda”也是有效的输出。
 * 示例 2:
 * 输入: order = "cbafg", s = "abcd"
 * 输出: "cbad"
 * 提示:
 * 1 <= order.length <= 26
 * 1 <= s.length <= 200
 * order 和 s 由小写英文字母组成
 * order 中的所有字符都 不同
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2022/11/14
 */
public class CustomSortString {
    public String customSortString(String order, String s) {
        int[] indexs = new int[26];
        Arrays.fill(indexs, -1);
        for (int i = 0, n = order.length(); i < n; i++) {
            indexs[order.charAt(i) - 'a'] = i;
        }
        int n = s.length();
        char[] chars = s.toCharArray();
        quick(chars, 0, n - 1, indexs);
        return new String(chars);
    }

    private static void quick(char[] arr, int l, int r, int[] indexs) {
        if (l >= r) {return;}
        char pivot = arr[l];
        int pivotIndex = indexs[pivot - 'a'];
        int i = l, j = r;
        while (i < j) {
            // 一定要先右再左
            while (i < j && indexs[arr[j] - 'a'] >= pivotIndex) {j--;}
            while (i < j && indexs[arr[i] - 'a'] <= pivotIndex) {i++;}
            if (i < j) {
                char temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        // 基准数归位
        arr[l] = arr[i];
        arr[i] = pivot;
        quick(arr, l, i, indexs);
        quick(arr, i + 1, r, indexs);
    }
}
