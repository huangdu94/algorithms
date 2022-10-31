package work.huangdu.exploration.start_from_scratch.string.number_transform_string;

/**
 * 481. 神奇字符串
 * 神奇字符串 s 仅由 '1' 和 '2' 组成，并需要遵守下面的规则：
 * 神奇字符串 s 的神奇之处在于，串联字符串中 '1' 和 '2' 的连续出现次数可以生成该字符串。
 * s 的前几个元素是 s = "1221121221221121122……" 。如果将 s 中连续的若干 1 和 2 进行分组，可以得到 "1 22 11 2 1 22 1 22 11 2 11 22 ......" 。每组中 1 或者 2 的出现次数分别是 "1 2 2 1 1 2 1 2 2 1 2 2 ......" 。上面的出现次数正是 s 自身。
 * 给你一个整数 n ，返回在神奇字符串 s 的前 n 个数字中 1 的数目。
 * 示例 1：
 * 输入：n = 6
 * 输出：3
 * 解释：神奇字符串 s 的前 6 个元素是 “122112”，它包含三个 1，因此返回 3 。
 * 示例 2：
 * 输入：n = 1
 * 输出：1
 * 提示：
 * 1 <= n <= 10^5
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2020/9/28 22:07
 */
public class MagicalString {
    public int magicalString(int n) {
        if (n <= 3) {return 1;}
        char[] chars = new char[n];
        chars[0] = '1';
        chars[1] = '2';
        chars[2] = '2';
        int p1 = 2, p2 = 3, ans = 1;
        char ch = '1';
        while (p2 < n) {
            for (int k = 0, count = chars[p1] - '0'; p2 < n && k < count; k++, p2++) {
                chars[p2] = ch;
                if (ch == '1') {ans++;}
            }
            ch = ch == '1' ? '2' : '1';
            p1++;
        }
        return ans;
    }

    public int magicalString2(int n) {
        if (n <= 0) return 0;
        if (n <= 3) return 1;
        int[] ms = new int[n + 1];
        ms[0] = 1;
        ms[1] = 2;
        ms[2] = 2;
        int all = 1, i = 2, last = 2, input = 3;
        while (input < n) {
            int count = ms[i++];
            last = (last & 1) + 1;
            ms[input++] = last;
            if (count == 1) {
                if (last == 1) all++;
            } else {
                ms[input++] = last;
                if (last == 1) {
                    all += input > n ? 1 : 2;
                }
            }
        }
        return all;
    }

    public static void main(String[] args) {
        MagicalString string = new MagicalString();
        System.out.println(string.magicalString(5));
    }
}
