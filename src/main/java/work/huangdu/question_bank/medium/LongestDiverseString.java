package work.huangdu.question_bank.medium;

/**
 * 1405. 最长快乐字符串
 * 如果字符串中不含有任何 'aaa'，'bbb' 或 'ccc' 这样的字符串作为子串，那么该字符串就是一个「快乐字符串」。
 * 给你三个整数 a，b ，c，请你返回 任意一个 满足下列全部条件的字符串 s：
 * s 是一个尽可能长的快乐字符串。
 * s 中 最多 有a 个字母 'a'、b 个字母 'b'、c 个字母 'c' 。
 * s 中只含有 'a'、'b' 、'c' 三种字母。
 * 如果不存在这样的字符串 s ，请返回一个空字符串 ""。
 * 示例 1：
 * 输入：a = 1, b = 1, c = 7
 * 输出："ccaccbcc"
 * 解释："ccbccacc" 也是一种正确答案。
 * 示例 2：
 * 输入：a = 2, b = 2, c = 1
 * 输出："aabbc"
 * 示例 3：
 * 输入：a = 7, b = 1, c = 0
 * 输出："aabaa"
 * 解释：这是该测试用例的唯一正确答案。
 * 提示：
 * 0 <= a, b, c <= 100
 * a + b + c > 0
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2022/2/7
 */
public class LongestDiverseString {

    public String longestDiverseString(int a, int b, int c) {
        StringBuilder sb = new StringBuilder();
        char cannotChar = ' ';
        while (a > 0 || b > 0 || c > 0) {
            if (a > 0 && cannotChar != 'a' && (a >= b && a >= c || cannotChar == 'b' && a >= c || cannotChar == 'c' && a >= b)) {
                sb.append('a');
                a--;
            } else if (b > 0 && cannotChar != 'b' && (b >= a && b >= c || cannotChar == 'a' && b >= c || cannotChar == 'c')) {
                sb.append('b');
                b--;
            } else if (c > 0 && cannotChar != 'c') {
                sb.append('c');
                c--;
            } else {
                break;
            }
            if (sb.length() > 1 && sb.charAt(sb.length() - 2) == sb.charAt(sb.length() - 1)) {
                cannotChar = sb.charAt(sb.length() - 1);
            } else {
                cannotChar = ' ';
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        LongestDiverseString lds = new LongestDiverseString();
        System.out.println(lds.longestDiverseString(1, 1, 7));
    }
}
