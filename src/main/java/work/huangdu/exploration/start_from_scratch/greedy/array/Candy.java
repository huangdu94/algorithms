package work.huangdu.exploration.start_from_scratch.greedy.array;

/**
 * 135. 分发糖果
 * 老师想给孩子们分发糖果，有 N 个孩子站成了一条直线，老师会根据每个孩子的表现，预先给他们评分。
 * 你需要按照以下要求，帮助老师给这些孩子分发糖果：
 * 每个孩子至少分配到 1 个糖果。
 * 相邻的孩子中，评分高的孩子必须获得更多的糖果。
 * 那么这样下来，老师至少需要准备多少颗糖果呢？
 * 示例 1:
 * 输入: [1,0,2]
 * 输出: 5
 * 解释: 你可以分别给这三个孩子分发 2、1、2 颗糖果。
 * 示例 2:
 * 输入: [1,2,2]
 * 输出: 4
 * 解释: 你可以分别给这三个孩子分发 1、2、1 颗糖果。
 * 第三个孩子只得到 1 颗糖果，这已满足上述两个条件。
 *
 * @author huangdu
 * @version 2020/11/10 12:58
 */
public class Candy {
    public int candy(int[] ratings) {
        int n = ratings.length, count = n, i = 0;
        while (i < n - 1) {
            while (i < n - 1 && ratings[i] == ratings[i + 1]) {
                i++;
            }
            int asc = 0, desc = 0;
            while (i < n - 1 && ratings[i] < ratings[i + 1]) {
                asc++;
                i++;
            }
            while (i < n - 1 && ratings[i] > ratings[i + 1]) {
                desc++;
                i++;
            }
            if (asc >= desc) {
                count += (asc + 1) * asc / 2;
                count += (desc - 1) * desc / 2;
            } else {
                count += (desc + 1) * desc / 2;
                count += (asc - 1) * asc / 2;
            }
        }
        return count;
    }

    /**
     * 找到所有极小值点和所有极大值点，对于极小值点肯定为1，极大值点的值取从左往右计算，和从右往左计算最大的
     * 对于等值：等值可以重置糖果数，连续的两个等值，另一个可以为1（按照贪心算法，能变成1肯定变成1）
     */
    public int candy2(int[] ratings) {
        int n = ratings.length, i = 0, ans = 0;
        int[] candy = new int[n];
        candy[0] = 1;
        while (i + 1 < n) {
            while (i + 1 < n && ratings[i] > ratings[i + 1]) {
                i++;
            }
            candy[i] = Math.max(candy[i], 1);
            for (int k = i; k >= 1 && ratings[k - 1] > ratings[k]; k--) {
                candy[k - 1] = Math.max(candy[k - 1], candy[k] + 1);
            }
            while (i + 1 < n && ratings[i] == ratings[i + 1]) {
                candy[++i] = 1;
            }
            while (i + 1 < n && ratings[i] < ratings[i + 1]) {
                i++;
                candy[i] = Math.max(candy[i], candy[i - 1] + 1);
            }
        }
        for (int amount : candy) {
            ans += amount;
        }
        return ans;
    }
}
