package work.huangdu.question_bank.difficult;

/**
 * 1220. 统计元音字母序列的数目
 * 给你一个整数 n，请你帮忙统计一下我们可以按下述规则形成多少个长度为 n 的字符串：
 * 字符串中的每个字符都应当是小写元音字母（'a', 'e', 'i', 'o', 'u'）
 * 每个元音 'a' 后面都只能跟着 'e'
 * 每个元音 'e' 后面只能跟着 'a' 或者是 'i'
 * 每个元音 'i' 后面 不能 再跟着另一个 'i'
 * 每个元音 'o' 后面只能跟着 'i' 或者是 'u'
 * 每个元音 'u' 后面只能跟着 'a'
 * 由于答案可能会很大，所以请你返回 模 10^9 + 7 之后的结果。
 * 示例 1：
 * 输入：n = 1
 * 输出：5
 * 解释：所有可能的字符串分别是："a", "e", "i" , "o" 和 "u"。
 * 示例 2：
 * 输入：n = 2
 * 输出：10
 * 解释：所有可能的字符串分别是："ae", "ea", "ei", "ia", "ie", "io", "iu", "oi", "ou" 和 "ua"。
 * 示例 3：
 * 输入：n = 5
 * 输出：68
 * 提示：
 * 1 <= n <= 2 * 10^4
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2022/2/9
 */
public class CountVowelPermutation {
    private final static int BASE = (int)1e9 + 7;

    public int countVowelPermutation(int n) {
        int a = 1, e = 1, i = 1, o = 1, u = 1;
        for (int k = 1; k < n; k++) {
            int a0 = ((e + i) % BASE + u) % BASE;
            int e0 = (a + i) % BASE;
            int i0 = (e + o) % BASE;
            int o0 = i;
            int u0 = (i + o) % BASE;
            a = a0;
            e = e0;
            i = i0;
            o = o0;
            u = u0;
        }
        return ((((a + e) % BASE + i) % BASE + o) % BASE + u) % BASE;
    }

    public static void main(String[] args) {
        CountVowelPermutation cvp = new CountVowelPermutation();
        System.out.println(cvp.countVowelPermutation(5));
    }
}
