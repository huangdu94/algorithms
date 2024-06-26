package work.huangdu.question_bank.easy;

/**
 * 717. 1 比特与 2 比特字符
 * 有两种特殊字符：
 * 第一种字符可以用一比特 0 表示
 * 第二种字符可以用两比特（10 或 11）表示
 * 给你一个以 0 结尾的二进制数组 bits ，如果最后一个字符必须是一个一比特字符，则返回 true 。
 * 示例 1:
 * 输入: bits = [1, 0, 0]
 * 输出: true
 * 解释: 唯一的解码方式是将其解析为一个两比特字符和一个一比特字符。
 * 所以最后一个字符是一比特字符。
 * 示例 2:
 * 输入：bits = [1,1,1,0]
 * 输出：false
 * 解释：唯一的解码方式是将其解析为两比特字符和两比特字符。
 * 所以最后一个字符不是一比特字符。
 * 提示:
 * 1 <= bits.length <= 1000
 * bits[i] 为 0 或 1
 *
 * @author huangdu
 * @version 2022/2/21
 */
public class IsOneBitCharacter {
    public boolean isOneBitCharacter(int[] bits) {
        int n = bits.length;
        // 0 true
        // 0 0 true
        // 1 0 false
        // 0 1 0 false
        // 0 1 1 0 true
        // 0 1 1 1 0 false
        // 找规律发现，从倒数第二个数开始，统计1的个数，直到到了数组的开头或者遇到了第一个0
        // 如果1的个数为偶数个，则返回true，1的个数为奇数个，则返回false
        int i = n - 2;
        while (i >= 0 && bits[i] == 1) { i--; }
        return ((n - 2 - i) & 1) == 0;
    }
}
