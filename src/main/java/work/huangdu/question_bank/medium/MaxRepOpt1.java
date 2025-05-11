package work.huangdu.question_bank.medium;

/**
 * 1156. 单字符重复子串的最大长度
 * 如果字符串中的所有字符都相同，那么这个字符串是单字符重复的字符串。
 * 给你一个字符串 text，你只能交换其中两个字符一次或者什么都不做，然后得到一些单字符重复的子串。返回其中最长的子串的长度。
 * 示例 1：
 * 输入：text = "ababa"
 * 输出：3
 * 示例 2：
 * 输入：text = "aaabaaa"
 * 输出：6
 * 示例 3：
 * 输入：text = "aaabbaaa"
 * 输出：4
 * 示例 4：
 * 输入：text = "aaaaa"
 * 输出：5
 * 示例 5：
 * 输入：text = "abcdef"
 * 输出：1
 * 提示：
 * 1 <= text.length <= 20000
 * text 仅由小写英文字母组成。
 *
 * @author huangdu
 * @version 2023/6/4
 */
public class MaxRepOpt1 {
    public int maxRepOpt1(String text) {
        int ans = 0;
        for (char ch = 'a'; ch <= 'z'; ch++) {
            ans = Math.max(ans, compute(text, ch));
        }
        return ans;
    }

    /**
     * 1. target相同字母最长连续子串
     * 2. target相同字母最长连续子串（中间允许有一个不一样的）
     * 2. text中连续的target总共有多少段
     */
    private int compute(String text, char target) {
        /*
         max 最长连续子串
         twoSectionMax 最长连续子串（允许中间断一次字母）
         section 连续段落的数量（不允许断）
         preLen 上一段的长度
         curLen 当前段的长度
         flag 0 初始化状态 1 连续寻找状态 2 已经断了一次了
         */
        int max = 0, twoSectionMax = 0, section = 0, preLen = 0, curLen = 0, flag = 0;
        for (int i = 0, n = text.length(); i < n; i++) {
            char ch = text.charAt(i);
            if (ch == target) {
                if (flag != 1) {
                    flag = 1;
                    section++;
                }
                curLen++;
            }
            if (i == n - 1 || ch != target) {
                max = Math.max(max, curLen);
                twoSectionMax = Math.max(twoSectionMax, preLen + curLen);
                if (ch != target) {
                    if (flag == 1) {
                        flag = 2;
                        preLen = curLen;
                        curLen = 0;
                    } else if (flag == 2) {
                        flag = 0;
                        preLen = 0;
                    }
                }
            }
        }
        return Math.max(twoSectionMax + (section > 2 ? 1 : 0), max + (section == 2 ? 1 : 0));
    }

    public static void main(String[] args) {
        MaxRepOpt1 mpo = new MaxRepOpt1();
        String text = "acbaaa";
        System.out.println(mpo.maxRepOpt1(text));
    }
}
