package work.huangdu.question_bank.medium;

/**
 * 1997. 访问完所有房间的第一天
 * 你需要访问 n 个房间，房间从 0 到 n - 1 编号。同时，每一天都有一个日期编号，从 0 开始，依天数递增。你每天都会访问一个房间。
 * 最开始的第 0 天，你访问 0 号房间。给你一个长度为 n 且 下标从 0 开始 的数组 nextVisit 。在接下来的几天中，你访问房间的 次序 将根据下面的 规则 决定：
 * 假设某一天，你访问 i 号房间。
 * 如果算上本次访问，访问 i 号房间的次数为 奇数 ，那么 第二天 需要访问 nextVisit[i] 所指定的房间，其中 0 <= nextVisit[i] <= i 。
 * 如果算上本次访问，访问 i 号房间的次数为 偶数 ，那么 第二天 需要访问 (i + 1) mod n 号房间。
 * 请返回你访问完所有房间的第一天的日期编号。题目数据保证总是存在这样的一天。由于答案可能很大，返回对 109 + 7 取余后的结果。
 * 示例 1：
 * 输入：nextVisit = [0,0]
 * 输出：2
 * 解释：
 * - 第 0 天，你访问房间 0 。访问 0 号房间的总次数为 1 ，次数为奇数。
 * 下一天你需要访问房间的编号是 nextVisit[0] = 0
 * - 第 1 天，你访问房间 0 。访问 0 号房间的总次数为 2 ，次数为偶数。
 * 下一天你需要访问房间的编号是 (0 + 1) mod 2 = 1
 * - 第 2 天，你访问房间 1 。这是你第一次完成访问所有房间的那天。
 * 示例 2：
 * 输入：nextVisit = [0,0,2]
 * 输出：6
 * 解释：
 * 你每天访问房间的次序是 [0,0,1,0,0,1,2,...] 。
 * 第 6 天是你访问完所有房间的第一天。
 * 示例 3：
 * 输入：nextVisit = [0,1,2,0]
 * 输出：6
 * 解释：
 * 你每天访问房间的次序是 [0,0,1,1,2,2,3,...] 。
 * 第 6 天是你访问完所有房间的第一天。
 * 提示：
 * n == nextVisit.length
 * 2 <= n <= 10^5
 * 0 <= nextVisit[i] <= i
 *
 * @author huangdu
 */
public class FirstDayBeenInAllRooms {
    public int firstDayBeenInAllRooms(int[] nextVisit) {
        int mod = 1000000007;
        int len = nextVisit.length;
        int[] dp = new int[len];

        dp[0] = 2; //初始化原地待一天 + 访问下一个房间一天
        for (int i = 1; i < len; i++) {
            int to = nextVisit[i];
            dp[i] = 2 + dp[i - 1];
            if (to != 0) {
                dp[i] = (dp[i] - dp[to - 1] + mod) % mod; //避免负数
            }

            dp[i] = (dp[i] + dp[i - 1]) % mod;
        }
        return dp[len - 2]; //题目保证n >= 2
    }

    // 超时
    public int firstDayBeenInAllRooms2(int[] nextVisit) {
        long day = 0;
        int n = nextVisit.length, visited = 1, next = nextVisit[0];
        int[] freq = new int[n];
        freq[0] = 1;
        while (visited < n) {
            if (freq[next]++ == 0) {visited++;}
            if ((freq[next] & 1) == 1) {
                next = nextVisit[next];
            } else {
                next = (next + 1) % n;
            }
            day++;
        }
        return (int)(day % 1000000007);
    }
}
