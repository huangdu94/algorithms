package work.huangdu.question_bank.difficult;

import java.util.Arrays;

/**
 * 691. 贴纸拼词
 * 我们有 n 种不同的贴纸。每个贴纸上都有一个小写的英文单词。
 * 您想要拼写出给定的字符串 target ，方法是从收集的贴纸中切割单个字母并重新排列它们。如果你愿意，你可以多次使用每个贴纸，每个贴纸的数量是无限的。
 * 返回你需要拼出 target 的最小贴纸数量。如果任务不可能，则返回 -1 。
 * 注意：在所有的测试用例中，所有的单词都是从 1000 个最常见的美国英语单词中随机选择的，并且 target 被选择为两个随机单词的连接。
 * 示例 1：
 * 输入： stickers = ["with","example","science"], target = "thehat"
 * 输出：3
 * 解释：
 * 我们可以使用 2 个 "with" 贴纸，和 1 个 "example" 贴纸。
 * 把贴纸上的字母剪下来并重新排列后，就可以形成目标 “thehat“ 了。
 * 此外，这是形成目标字符串所需的最小贴纸数量。
 * 示例 2:
 * 输入：stickers = ["notice","possible"], target = "basicbasic"
 * 输出：-1
 * 解释：我们不能通过剪切给定贴纸的字母来形成目标“basicbasic”。
 * 提示:
 * n == stickers.length
 * 1 <= n <= 50
 * 1 <= stickers[i].length <= 10
 * 1 <= target.length <= 15
 * stickers[i] 和 target 由小写英文单词组成
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2022/5/23
 */
public class MinStickers {
    private int tn;
    private int[][] ss;
    private Integer[] memo;
    private int complete;
    private String target;

    public int minStickers(String[] stickers, String target) {
        int sn = stickers.length;
        this.tn = target.length();
        this.ss = new int[sn][26];
        this.memo = new Integer[1 << tn];
        this.complete = (1 << tn) - 1;
        this.target = target;
        for (int i = 0; i < sn; i++) {
            String sticker = stickers[i];
            int[] s = ss[i];
            for (int j = 0, n = sticker.length(); j < n; j++) {
                s[sticker.charAt(j) - 'a']++;
            }
        }
        return search(0);
    }

    private int search(int status) {
        if (status == complete) {return 0;}
        if (memo[status] != null) {return memo[status];}
        int count = Integer.MAX_VALUE;
        for (int[] s : ss) {
            int newStatus = status;
            int[] copyS = Arrays.copyOf(s, s.length);
            for (int i = 0, mask = 1; i < tn; i++, mask <<= 1) {
                if ((status & mask) != 0) {continue;}
                int c = target.charAt(i) - 'a';
                if (copyS[c] > 0) {
                    newStatus |= mask;
                    copyS[c]--;
                }
            }
            if (newStatus != status) {
                int nextCount = search(newStatus);
                if (nextCount != -1) {
                    count = Math.min(count, 1 + nextCount);
                }
            }
        }
        return memo[status] = count == Integer.MAX_VALUE ? -1 : count;
    }

    public static void main(String[] args) {
        MinStickers ms = new MinStickers();
        String[] stickers = {"with", "example", "science"};
        String target = "thehat";
        System.out.println(ms.minStickers(stickers, target));
    }
}
