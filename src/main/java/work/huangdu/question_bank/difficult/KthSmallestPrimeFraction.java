package work.huangdu.question_bank.difficult;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 786. 第 K 个最小的素数分数
 * 给你一个按递增顺序排序的数组 arr 和一个整数 k 。数组 arr 由 1 和若干 素数  组成，且其中所有整数互不相同。
 * 对于每对满足 0 <= i < j < arr.length 的 i 和 j ，可以得到分数 arr[i] / arr[j] 。
 * 那么第 k 个最小的分数是多少呢?  以长度为 2 的整数数组返回你的答案, 这里 answer[0] == arr[i] 且 answer[1] == arr[j] 。
 * 示例 1：
 * 输入：arr = [1,2,3,5], k = 3
 * 输出：[2,5]
 * 解释：已构造好的分数,排序后如下所示:
 * 1/5, 1/3, 2/5, 1/2, 3/5, 2/3
 * 很明显第三个最小的分数是 2/5
 * 示例 2：
 * 输入：arr = [1,7], k = 1
 * 输出：[1,7]
 * 提示：
 * 2 <= arr.length <= 1000
 * 1 <= arr[i] <= 3 * 10^4
 * arr[0] == 1
 * arr[i] 是一个 素数 ，i > 0
 * arr 中的所有数字 互不相同 ，且按 严格递增 排序
 * 1 <= k <= arr.length * (arr.length - 1) / 2
 *
 * @author huangdu
 * @version 2021/12/16
 */
public class KthSmallestPrimeFraction {
    public int[] kthSmallestPrimeFraction(int[] arr, int k) {
        int n = arr.length;
        PriorityQueue<int[]> pq = new PriorityQueue<>(k, (o1, o2) -> o2[0] * o1[1] - o1[0] * o2[1]);
        for (int i = 0; i < n; i++) {
            for (int j = n - 1; j > i; j--) {
                pq.offer(new int[] {arr[i], arr[j]});
                if (pq.size() > k) { pq.poll(); }
            }
        }
        return pq.peek();
    }

    // 优化
    /*
    private int compareArray(int[] o1, int[] o2) {
        return Double.compare(1.0 * o1[0] / o1[1], 1.0 * o2[0] / o2[1]);
    }*/
    private int compareArray(int[] o1, int[] o2) {
        return o1[0] * o2[1] - o2[0] * o1[1];
    }

    public static void main(String[] args) {
        KthSmallestPrimeFraction kspf = new KthSmallestPrimeFraction();
        System.out.println(Arrays.toString(kspf.kthSmallestPrimeFraction(new int[] {1, 2, 3, 5}, 3)));
    }
}
