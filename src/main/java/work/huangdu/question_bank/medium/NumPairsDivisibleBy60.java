package work.huangdu.question_bank.medium;

/**
 * 1010. 总持续时间可被 60 整除的歌曲
 * 在歌曲列表中，第 i 首歌曲的持续时间为 time[i] 秒。
 * 返回其总持续时间（以秒为单位）可被 60 整除的歌曲对的数量。形式上，我们希望下标数字 i 和 j 满足  i < j 且有 (time[i] + time[j]) % 60 == 0。
 * 示例 1：
 * 输入：time = [30,20,150,100,40]
 * 输出：3
 * 解释：这三对的总持续时间可被 60 整除：
 * (time[0] = 30, time[2] = 150): 总持续时间 180
 * (time[1] = 20, time[3] = 100): 总持续时间 120
 * (time[1] = 20, time[4] = 40): 总持续时间 60
 * 示例 2：
 * 输入：time = [60,60,60]
 * 输出：3
 * 解释：所有三对的总持续时间都是 120，可以被 60 整除。
 * 提示：
 * 1 <= time.length <= 6 * 10^4
 * 1 <= time[i] <= 500
 *
 * @author huangdu
 * @version 2023/5/8
 */
public class NumPairsDivisibleBy60 {
    public int numPairsDivisibleBy60(int[] time) {
        int n = time.length, ans = 0;
        int[] freq = new int[60];
        freq[time[n - 1] % 60]++;
        for (int i = n - 2; i >= 0; i--) {
            int num = time[i] % 60;
            ans += freq[(60 - num) % 60];
            freq[num]++;
        }
        return ans;
    }

    public static void main(String[] args) {
        NumPairsDivisibleBy60 npdb60 = new NumPairsDivisibleBy60();
        int[] time = {30, 20, 150, 100, 40};
        System.out.println(npdb60.numPairsDivisibleBy60(time));
    }
}
