package work.huangdu.question_bank.easy;

/**
 * 917. 仅仅反转字母
 * 给你一个字符串 s ，根据下述规则反转字符串：
 * 所有非英文字母保留在原有位置。
 * 所有英文字母（小写或大写）位置反转。
 * 返回反转后的 s 。
 * 示例 1：
 * 输入：s = "ab-cd"
 * 输出："dc-ba"
 * 示例 2：
 * 输入：s = "a-bC-dEf-ghIj"
 * 输出："j-Ih-gfE-dCba"
 * 示例 3：
 * 输入：s = "Test1ng-Leet=code-Q!"
 * 输出："Qedo1ct-eeLg=ntse-T!"
 * 提示
 * 1 <= s.length <= 100
 * s 仅由 ASCII 值在范围 [33, 122] 的字符组成
 * s 不含 '\"' 或 '\\'
 *
 * @author huangdu
 * @version 2022/2/23
 */
public class ReverseOnlyLetters {
    public String reverseOnlyLetters(String s) {
        int n = s.length(), i = 0, j = n - 1;
        char[] chars = s.toCharArray();
        while (i < j) {
            while (i < j && !(chars[i] >= 'A' && chars[i] <= 'Z' || chars[i] >= 'a' && chars[i] <= 'z')) {
                i++;
            }
            while (i < j && !(chars[j] >= 'A' && chars[j] <= 'Z' || chars[j] >= 'a' && chars[j] <= 'z')) {
                j--;
            }
            if (i < j) {
                char temp = chars[i];
                chars[i] = chars[j];
                chars[j] = temp;
                i++;
                j--;
            }
        }
        return new String(chars);
    }
}
