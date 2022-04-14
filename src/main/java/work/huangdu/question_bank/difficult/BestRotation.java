package work.huangdu.question_bank.difficult;

/**
 * 798. 得分最高的最小轮调
 * 给你一个数组 nums，我们可以将它按一个非负整数 k 进行轮调，这样可以使数组变为 [nums[k], nums[k + 1], ... nums[nums.length - 1], nums[0], nums[1], ..., nums[k-1]] 的形式。此后，任何值小于或等于其索引的项都可以记作一分。
 * 例如，数组为 nums = [2,4,1,3,0]，我们按 k = 2 进行轮调后，它将变成 [1,3,0,2,4]。这将记为 3 分，因为 1 > 0 [不计分]、3 > 1 [不计分]、0 <= 2 [计 1 分]、2 <= 3 [计 1 分]，4 <= 4 [计 1 分]。
 * 在所有可能的轮调中，返回我们所能得到的最高分数对应的轮调下标 k 。如果有多个答案，返回满足条件的最小的下标 k 。
 * 示例 1：
 * 输入：nums = 2,3,1,4,0
 * 输出：3
 * 解释：
 * 下面列出了每个 k 的得分：
 * k = 0,  nums = [2,3,1,4,0],    score 2
 * k = 1,  nums = [3,1,4,0,2],    score 3
 * k = 2,  nums = [1,4,0,2,3],    score 3
 * k = 3,  nums = [4,0,2,3,1],    score 4
 * k = 4,  nums = [0,2,3,1,4],    score 3
 * 所以我们应当选择 k = 3，得分最高。
 * 示例 2：
 * 输入：nums = [1,3,0,2,4]
 * 输出：0
 * 解释：
 * nums 无论怎么变化总是有 3 分。
 * 所以我们将选择最小的 k，即 0。
 * 提示：
 * 1 <= nums.length <= 10^5
 * 0 <= nums[i] < nums.length
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2022/4/2
 */
public class BestRotation {
    /*
    数组长度n，按k进行轮调后，原数组下标为i，变成 (i+n-k) % n
    设每个数为x，则可以让其得分的下标为 [x,n-1] 共有n-x个，不能得分的下标有[0,x-1]，共有x个
    x <= (i+n-k) % n
    k <= (i+n-x) % n
    i<x      k <= i+n-x
    i>=x     k <= i-x
    因为可以得分的下标为n-x个
    i<x      k ->  [i+1,i+n-x]
    i==n-1   k ->  [0,i-x]
    i>=x     k ->  [0,i-x] U [i+1,n-1]
     */
    public int bestRotation(int[] nums) {
        int n = nums.length;
        int[] diff = new int[n + 1];
        for (int i = 0; i < n; i++) {
            int x = nums[i];
            if (x >= n) {continue;}
            if (i < x) {
                diff[i + 1]++;
                diff[i + n - x + 1]--;
            } else if (i == n - 1) {
                diff[0]++;
                diff[i - x + 1]--;
            } else {
                diff[0]++;
                diff[i - x + 1]--;
                diff[i + 1]++;
                diff[n]--;
            }
        }
        int ans = 0, cnt = 0, max = 0;
        for (int i = 0; i < n; i++) {
            cnt += diff[i];
            if (cnt > max) {
                max = cnt;
                ans = i;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 4, 0};
        BestRotation br = new BestRotation();
        System.out.println(br.bestRotation(nums));
    }
}
