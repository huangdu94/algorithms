package work.huangdu.question_bank.difficult;

/**
 * 1147. 段式回文
 * 你会得到一个字符串 text 。你应该把它分成 k 个子字符串 (subtext1, subtext2，…， subtextk) ，要求满足:
 * subtexti 是 非空 字符串
 * 所有子字符串的连接等于 text ( 即subtext1 + subtext2 + ... + subtextk == text )
 * 对于所有 i 的有效值( 即 1 <= i <= k ) ，subtexti == subtextk - i + 1 均成立
 * 返回k可能最大值。
 * 示例 1：
 * 输入：text = "ghiabcdefhelloadamhelloabcdefghi"
 * 输出：7
 * 解释：我们可以把字符串拆分成 "(ghi)(abcdef)(hello)(adam)(hello)(abcdef)(ghi)"。
 * 示例 2：
 * 输入：text = "merchant"
 * 输出：1
 * 解释：我们可以把字符串拆分成 "(merchant)"。
 * 示例 3：
 * 输入：text = "antaprezatepzapreanta"
 * 输出：11
 * 解释：我们可以把字符串拆分成 "(a)(nt)(a)(pre)(za)(tpe)(za)(pre)(a)(nt)(a)"。
 * 提示：
 * 1 <= text.length <= 1000
 * text 仅由小写英文字符组成
 *
 * @author huangdu
 * @version 2023/4/12
 */
public class LongestDecomposition {
    private String text;

    public int longestDecomposition(String text) {
        this.text = text;
        return dfs(0, text.length());
    }

    private int dfs(int left, int right) {
        if (left == right) {return 0;}
        for (int len = 1, limit = (right - left) / 2; len <= limit; len++) {
            if (equals(left, right - len, right)) {
                return dfs(left + len, right - len) + 2;
            }
        }
        return 1;
    }

    private boolean equals(int p1, int p2, int end) {
        while (p2 < end) {
            if (text.charAt(p1) != text.charAt(p2)) {return false;}
            p1++;
            p2++;
        }
        return true;
    }

    public static void main(String[] args) {
        LongestDecomposition ld = new LongestDecomposition();
        System.out.println(ld.longestDecomposition("ghiabcdefhelloadamhelloabcdefghi"));
    }
}
