package work.huangdu.question_bank.medium;

/**
 * 1954. 收集足够苹果的最小花园周长
 * 给你一个用无限二维网格表示的花园，每一个 整数坐标处都有一棵苹果树。整数坐标 (i, j) 处的苹果树有 |i| + |j| 个苹果。
 * 你将会买下正中心坐标是 (0, 0) 的一块 正方形土地 ，且每条边都与两条坐标轴之一平行。
 * 给你一个整数 neededApples ，请你返回土地的 最小周长 ，使得 至少 有 neededApples 个苹果在土地 里面或者边缘上。
 * |x| 的值定义为：
 * 如果 x >= 0 ，那么值为 x
 * 如果 x < 0 ，那么值为 -x
 * 示例 1：
 * 输入：neededApples = 1
 * 输出：8
 * 解释：边长长度为 1 的正方形不包含任何苹果。
 * 但是边长为 2 的正方形包含 12 个苹果（如上图所示）。
 * 周长为 2 * 4 = 8 。
 * 示例 2：
 * 输入：neededApples = 13
 * 输出：16
 * 示例 3：
 * 输入：neededApples = 1000000000
 * 输出：5040
 * 提示：
 * 1 <= neededApples <= 10^15
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 */
public class MinimumPerimeter {
    /**
     * 1. 变长为 x（x为偶数）和变长为x+1的正方形里的苹果数量是一样的
     * 2. 变长为0的正方形里没有苹果，同理变长为1的正方形里也没有苹果（在边界情况下也满足第一条规律）
     * 3. 递推公式：变长为x的正方形里的苹果数比变长为x - 2的正方形里的苹果数量多，(x / 2 * (x + 1) + (x - 2) / 2 * ((x - 2) / 2 + 1)) * 4
     * 化简一下上述公式得 3 * x * x，十分的简洁
     */
    public long minimumPerimeter(long neededApples) {
        long ans = 0, appleCount = 0;
        while (appleCount < neededApples) {
            ans += 2;
            appleCount += 3 * ans * ans;
        }
        return ans * 4;
    }



}
