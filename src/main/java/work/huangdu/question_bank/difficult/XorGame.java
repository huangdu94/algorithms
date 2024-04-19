package work.huangdu.question_bank.difficult;

/**
 * 810. 黑板异或游戏
 * 黑板上写着一个非负整数数组 nums[i] 。Alice 和 Bob 轮流从黑板上擦掉一个数字，Alice 先手。如果擦除一个数字后，剩余的所有数字按位异或运算得出的结果等于 0 的话，当前玩家游戏失败。 (另外，如果只剩一个数字，按位异或运算得到它本身；如果无数字剩余，按位异或运算结果为 0。）
 * 换种说法就是，轮到某个玩家时，如果当前黑板上所有数字按位异或运算结果等于 0，这个玩家获胜。
 * 假设两个玩家每步都使用最优解，当且仅当 Alice 获胜时返回 true。
 * 示例：
 * 输入: nums = [1, 1, 2]
 * 输出: false
 * 解释:
 * Alice 有两个选择: 擦掉数字 1 或 2。
 * 如果擦掉 1, 数组变成 [1, 2]。剩余数字按位异或得到 1 XOR 2 = 3。那么 Bob 可以擦掉任意数字，因为 Alice 会成为擦掉最后一个数字的人，她总是会输。
 * 如果 Alice 擦掉 2，那么数组变成[1, 1]。剩余数字按位异或得到 1 XOR 1 = 0。Alice 仍然会输掉游戏。
 * 提示：
 * 1 <= N <= 1000
 * 0 <= nums[i] <= 2^16
 *
 * @author huangdu
 * @version 2021/5/22
 */
public class XorGame {
    /*
        数学推导：
        记nums数组长度为n，所有数的异或为S，擦掉数nums[i]后剩余数的异或为S_i
        1. 如果S为0，则直接返回true
        2. 如果S不为0，所有S_i都为0，返回false
            针对此种情况做推导
            S_i = S ^ nums[i] (i >= 0 & i < n)
              S_0 ^ S_1 ^ ... ^ S_n-1
            = (n个S)(S ^ ... ^ S) ^ (nums[0] ^ ... ^ nums[n-1])
            = (n个S)(S ^ ... ^ S) ^ S
            当n为偶数的时候
            结果为 S
            故得出结论，当n为偶数的时候，不可能出现S不为0，所有S_i都为0的情况
            也就是说当n为偶数的时候必不可能返回false。
        3. 那么n为奇数的时候，S又不为0，就相当于角色互换，结果必不可能返回true了

     */
    public boolean xorGame(int[] nums) {
        int n = nums.length;
        if ((n & 1) == 0) {
            return true;
        }
        int xor = 0;
        for (int num : nums) {
            xor ^= num;
        }
        return xor == 0;
    }
}
