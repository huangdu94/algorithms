package work.huangdu.question_bank.medium;

/**
 * 825. 适龄的朋友
 * 在社交媒体网站上有 n 个用户。给你一个整数数组 ages ，其中 ages[i] 是第 i 个用户的年龄。
 * 如果下述任意一个条件为真，那么用户 x 将不会向用户 y（x != y）发送好友请求：
 * age[y] <= 0.5 * age[x] + 7
 * age[y] > age[x]
 * age[y] > 100 && age[x] < 100
 * 否则，x 将会向 y 发送一条好友请求。
 * 注意，如果 x 向 y 发送一条好友请求，y 不必也向 x 发送一条好友请求。另外，用户不会向自己发送好友请求。
 * 返回在该社交媒体网站上产生的好友请求总数。
 * 示例 1：
 * 输入：ages = [16,16]
 * 输出：2
 * 解释：2 人互发好友请求。
 * 示例 2：
 * 输入：ages = [16,17,18]
 * 输出：2
 * 解释：产生的好友请求为 17 -> 16 ，18 -> 17 。
 * 示例 3：
 * 输入：ages = [20,30,100,110,120]
 * 输出：3
 * 解释：产生的好友请求为 110 -> 100 ，120 -> 110 ，120 -> 100 。
 * 提示：
 * n == ages.length
 * 1 <= n <= 2 * 10^4
 * 1 <= ages[i] <= 120
 *
 * @author huangdu
 * @version 2021/12/27
 */
public class NumFriendRequests {
    public int numFriendRequests(int[] ages) {
        int request = 0;
        int[] counts = new int[121];
        for (int age : ages) {
            counts[age]++;
        }
        for (int ageX = 15; ageX <= 120; ageX++) {
            int countX = counts[ageX];
            if (countX == 0) {continue;}
            for (int ageY = 15; ageY <= 120; ageY++) {
                int countY = counts[ageY];
                if (countY == 0) {continue;}
                if (ageY <= 0.5 * ageX + 7 || ageY > ageX) {continue;}
                if (ageX == ageY) {
                    request += countX * (countY - 1);
                } else {
                    request += countX * countY;
                }
            }
        }
        return request;
    }
}
