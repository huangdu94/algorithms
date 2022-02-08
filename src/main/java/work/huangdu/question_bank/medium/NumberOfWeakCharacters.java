package work.huangdu.question_bank.medium;

/**
 * 1996. 游戏中弱角色的数量
 * 你正在参加一个多角色游戏，每个角色都有两个主要属性：攻击 和 防御 。给你一个二维整数数组 properties ，其中 properties[i] = [attacki, defensei] 表示游戏中第 i 个角色的属性。
 * 如果存在一个其他角色的攻击和防御等级 都严格高于 该角色的攻击和防御等级，则认为该角色为 弱角色 。更正式地，如果认为角色 i 弱于 存在的另一个角色 j ，那么 attackj > attacki 且 defensej > defensei 。
 * 返回 弱角色 的数量。
 * 示例 1：
 * 输入：properties = [[5,5],[6,3],[3,6]]
 * 输出：0
 * 解释：不存在攻击和防御都严格高于其他角色的角色。
 * 示例 2：
 * 输入：properties = [[2,2],[3,3]]
 * 输出：1
 * 解释：第一个角色是弱角色，因为第二个角色的攻击和防御严格大于该角色。
 * 示例 3：
 * 输入：properties = [[1,5],[10,4],[4,3]]
 * 输出：1
 * 解释：第三个角色是弱角色，因为第二个角色的攻击和防御严格大于该角色。
 * 提示：
 * 2 <= properties.length <= 10^5
 * properties[i].length == 2
 * 1 <= attacki, defensei <= 10^5
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2022/2/8
 */
public class NumberOfWeakCharacters {
    // TODO 未提交
    public int numberOfWeakCharacters(int[][] properties) {
        // index: 攻击 value: 防御 （该攻击力中防御力最大值）
        // dp中记录 大于该攻击力的最大的防御力
        int[] maxDefArray = new int[100001], dp = new int[100001];
        for (int[] property : properties) {
            int atk = property[0], def = property[1];
            maxDefArray[atk] = Math.max(maxDefArray[atk], def);
        }
        for (int atk = 99999; atk >= 0; atk--) {
            dp[atk] = Math.max(dp[atk + 1], maxDefArray[atk + 1]);
        }
        int count = 0;
        for (int[] property : properties) {
            int atk = property[0], def = property[1];
            if (dp[atk] > def) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[][] properties = {{1, 1}, {2, 1}, {2, 2}, {1, 2}};
        NumberOfWeakCharacters nowc = new NumberOfWeakCharacters();
        System.out.println(nowc.numberOfWeakCharacters(properties));
    }
}
