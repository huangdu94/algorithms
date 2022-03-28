package work.huangdu.question_bank.medium;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/**
 * 838. 推多米诺
 * n 张多米诺骨牌排成一行，将每张多米诺骨牌垂直竖立。在开始时，同时把一些多米诺骨牌向左或向右推。
 * 每过一秒，倒向左边的多米诺骨牌会推动其左侧相邻的多米诺骨牌。同样地，倒向右边的多米诺骨牌也会推动竖立在其右侧的相邻多米诺骨牌。
 * 如果一张垂直竖立的多米诺骨牌的两侧同时有多米诺骨牌倒下时，由于受力平衡， 该骨牌仍然保持不变。
 * 就这个问题而言，我们会认为一张正在倒下的多米诺骨牌不会对其它正在倒下或已经倒下的多米诺骨牌施加额外的力。
 * 给你一个字符串 dominoes 表示这一行多米诺骨牌的初始状态，其中：
 * dominoes[i] = 'L'，表示第 i 张多米诺骨牌被推向左侧，
 * dominoes[i] = 'R'，表示第 i 张多米诺骨牌被推向右侧，
 * dominoes[i] = '.'，表示没有推动第 i 张多米诺骨牌。
 * 返回表示最终状态的字符串。
 * 示例 1：
 * 输入：dominoes = "RR.L"
 * 输出："RR.L"
 * 解释：第一张多米诺骨牌没有给第二张施加额外的力。
 * 示例 2：
 * 输入：dominoes = ".L.R...LR..L.."
 * 输出："LL.RR.LLRRLL.."
 * 提示：
 * n == dominoes.length
 *
 * dominoes[i] 为 'L'、'R' 或 '.'
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2022/2/21
 */
public class PushDominoes {
    // TODO 未提交 每日一题
    public String pushDominoes(String dominoes) {
        int n = dominoes.length(), p = 0;
        Queue<Integer> lQueue = new ArrayDeque<>(), rQueue = new ArrayDeque<>();
        char[] ans = dominoes.toCharArray();
        for (int i = 0; i < n; i++) {
            if (ans[i] == 'L') {
                lQueue.offer(i);
            } else if (ans[i] == 'R') {
                rQueue.offer(i);
            }
        }
        while (p < n) {
            Integer left = lQueue.peek(), right = rQueue.peek();
            if (left == null && right == null) { break; }
            if (left == null) {
                Arrays.fill(ans, right, n, 'R');
                break;
            }
            if (right == null) {
                while (!lQueue.isEmpty()) { left = lQueue.poll(); }
                Arrays.fill(ans, p, left + 1, 'L');
                break;
            }
            if (left < right) {
                Arrays.fill(ans, p, left + 1, 'L');
            } else {
                int right2 = -1;
                while (!rQueue.isEmpty() && rQueue.peek() < left) {right2 = rQueue.poll();}
                Arrays.fill(ans, right, right2, 'R');
                int len = left - right2 + 1, mid = (right2 + left) / 2;
                Arrays.fill(ans, right2, mid + ((len & 1) == 0 ? 1 : 0), 'R');
                Arrays.fill(ans, mid + 1, left + 1, 'L');
            }
            lQueue.poll();
            p = left + 1;
            while (!lQueue.isEmpty() && lQueue.peek() < p) { lQueue.poll(); }
            while (!rQueue.isEmpty() && rQueue.peek() < p) { rQueue.poll(); }
        }
        return new String(ans);
    }
}
