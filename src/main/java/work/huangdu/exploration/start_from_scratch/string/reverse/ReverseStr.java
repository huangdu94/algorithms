package work.huangdu.exploration.start_from_scratch.string.reverse;

/**
 * 541. 反转字符串 II
 * 给定一个字符串 s 和一个整数 k，你需要对从字符串开头算起的每隔 2k 个字符的前 k 个字符进行反转。
 * 如果剩余字符少于 k 个，则将剩余字符全部反转。
 * 如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。
 * 示例:
 * 输入: s = "abcdefg", k = 2
 * 输出: "bacdfeg"
 * 提示：
 * 该字符串只包含小写英文字母。
 * 给定字符串的长度和 k 在 [1, 10000] 范围内。
 *
 * @author huangdu
 * @version 2020/9/21 13:48
 */
public class ReverseStr {
    public String reverseStr(String s, int k) {
        char[] chars = s.toCharArray();
        int len = chars.length, i, j;
        for (int index = 0; index < len; index += 2 * k) {
            i = index;
            j = Math.min(index + k - 1, len - 1);
            while (i < j) {
                char temp = chars[i];
                chars[i] = chars[j];
                chars[j] = temp;
                i++;
                j--;
            }
        }
        return new String(chars);
    }

    public String reverseStr2(String s, int k) {
        int n = s.length();
        char[] chars = s.toCharArray();
        for (int i = 0; i < n; i += 2 * k) {
            reverseChars(chars, i, Math.min(i + k, n));
        }
        return new String(chars);
    }

    public void reverseChars(char[] chars, int i, int j) {
        j--;
        while (i < j) {
            char temp = chars[i];
            chars[i++] = chars[j];
            chars[j--] = temp;
        }
    }

    public static void main(String[] args) {
        ReverseStr rs = new ReverseStr();
        System.out.println(rs.reverseStr("abcdefg", 2));
    }
}
