package work.huangdu.question_bank.easy;

/**
 * 374. 猜数字大小
 * 猜数字游戏的规则如下：
 * 每轮游戏，我都会从 1 到 n 随机选择一个数字。 请你猜选出的是哪个数字。
 * 如果你猜错了，我会告诉你，你猜测的数字比我选出的数字是大了还是小了。
 * 你可以通过调用一个预先定义好的接口 int guess(int num) 来获取猜测结果，返回值一共有 3 种可能的情况（-1，1 或 0）：
 * -1：我选出的数字比你猜的数字小 pick < num
 * 1：我选出的数字比你猜的数字大 pick > num
 * 0：我选出的数字和你猜的数字一样。恭喜！你猜对了！pick == num
 * 返回我选出的数字。
 * 示例 1：
 * 输入：n = 10, pick = 6
 * 输出：6
 * 示例 2：
 * 输入：n = 1, pick = 1
 * 输出：1
 * 示例 3：
 * 输入：n = 2, pick = 1
 * 输出：1
 * 示例 4：
 * 输入：n = 2, pick = 2
 * 输出：2
 * 提示：
 * 1 <= n <= 2^31 - 1
 * 1 <= pick <= n
 * *Forward declaration of guess API.
 * *@param  num   your guess
 * *@return 	     -1 if num is lower than the guess number
 * *			      1 if num is higher than the guess number
 * *              otherwise return 0
 * *int guess(int num);
 *
 * @author huangdu
 * @version 2020/9/5 22:14
 */
public class GuessNumber {

    public int guessNumber(int n) {
        int i = 1, j = n;
        while (true) {
            int guess = i + ((j - i) >> 1);
            if (guess(guess) == 0) {
                return guess;
            }
            if (guess(guess) == 1) {
                i = guess + 1;
            } else {
                j = guess - 1;
            }
        }
    }

    public static void main(String[] args) {
        GuessNumber gn = new GuessNumber();
        System.out.println(gn.guessNumber(10));
    }

    private int guess(int num) {
        int ans = 4;
        return Integer.compare(num, ans);
    }

    public int guessNumber2(int n) {
        int l = 1, r = n;
        while (true) {
            int m = l + ((r - l) >> 1);
            int res = guess(m);
            if (res == 0) {
                return m;
            } else if (res == 1) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
    }
}
