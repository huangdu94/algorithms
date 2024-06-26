package work.huangdu.question_bank.medium;

/**
 * 2048. 下一个更大的数值平衡数
 * 如果整数  x 满足：对于每个数位 d ，这个数位 恰好 在 x 中出现 d 次。那么整数 x 就是一个 数值平衡数 。
 * 给你一个整数 n ，请你返回 严格大于 n 的 最小数值平衡数 。
 * 示例 1：
 * 输入：n = 1
 * 输出：22
 * 解释：
 * 22 是一个数值平衡数，因为：
 * - 数字 2 出现 2 次
 * 这也是严格大于 1 的最小数值平衡数。
 * 示例 2：
 * 输入：n = 1000
 * 输出：1333
 * 解释：
 * 1333 是一个数值平衡数，因为：
 * - 数字 1 出现 1 次。
 * - 数字 3 出现 3 次。
 * 这也是严格大于 1000 的最小数值平衡数。
 * 注意，1022 不能作为本输入的答案，因为数字 0 的出现次数超过了 0 。
 * 示例 3：
 * 输入：n = 3000
 * 输出：3133
 * 解释：
 * 3133 是一个数值平衡数，因为：
 * - 数字 1 出现 1 次。
 * - 数字 3 出现 3 次。
 * 这也是严格大于 3000 的最小数值平衡数。
 * 提示：
 * 0 <= n <= 10^6
 *
 * @author huangdu
 */
public class NextBeautifulNumber {
    /*
        1,
        22,
        122, 212, 221,
        333,
        1333, 3133, 3313, 3331,
        4444,
        14444, 41444, 44144, 44414, 44441,
        22333, 23233, 23323, 23332, 32233, 32323, 32332, 33223, 33232, 33322,
        55555,
        122333, 123233, 123323, 123332, 132233, 132323, 132332, 133223, 133232, 133322,
        212333, 213233, 213323, 213332, 312233, 312323, 312332, 313223, 313232, 313322,
        221333, 231233, 231323, 231332, 321233, 321323, 321332, 331223, 331232, 331322,
        223133, 232133, 233123, 233132, 322133, 323123, 323132, 332123, 332132, 333122,
        223313, 232313, 233213, 233312, 322313, 323213, 323312, 332213, 332312, 333212,
        223331, 232331, 233231, 233321, 322331, 323231, 323321, 332231, 332321, 333221,
        155555, 515555, 551555, 555155, 555515, 555551,
        224444, 242444, 244244, 244424, 244442, 422444, 424244, 424424, 424442, 442244, 442424, 442442, 444224, 444242, 444422,
        666666,
        1224444
     */
    private static final int[] table = {1, 22, 122, 212, 221, 333, 1333, 3133, 3313, 3331, 4444, 14444, 22333, 23233, 23323, 23332, 32233, 32323, 32332, 33223, 33232, 33322, 41444, 44144, 44414, 44441, 55555, 122333, 123233, 123323, 123332,
        132233, 132323, 132332, 133223, 133232, 133322, 155555, 212333, 213233, 213323, 213332, 221333, 223133, 223313, 223331, 224444, 231233, 231323, 231332, 232133, 232313, 232331, 233123, 233132, 233213, 233231, 233312, 233321, 242444,
        244244, 244424, 244442, 312233, 312323, 312332, 313223, 313232, 313322, 321233, 321323, 321332, 322133, 322313, 322331, 323123, 323132, 323213, 323231, 323312, 323321, 331223, 331232, 331322, 332123, 332132, 332213, 332231, 332312,
        332321, 333122, 333212, 333221, 422444, 424244, 424424, 424442, 442244, 442424, 442442, 444224, 444242, 444422, 515555, 551555, 555155, 555515, 555551, 666666, 1224444};


    public int nextBeautifulNumber(int n) {
        int left = -1, right = table.length;
        while (left + 1 < right) {
            int mid = left + (right - left >> 1);
            if (table[mid] <= n) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return table[right];
    }
}
