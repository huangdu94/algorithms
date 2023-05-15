package work.huangdu.question_bank.medium;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 1054. 距离相等的条形码
 * 在一个仓库里，有一排条形码，其中第 i 个条形码为 barcodes[i]。
 * 请你重新排列这些条形码，使其中任意两个相邻的条形码不能相等。 你可以返回任何满足该要求的答案，此题保证存在答案。
 * 示例 1：
 * 输入：barcodes = [1,1,1,2,2,2]
 * 输出：[2,1,2,1,2,1]
 * 示例 2：
 * 输入：barcodes = [1,1,1,1,2,2,3,3]
 * 输出：[1,3,1,3,2,1,2,1]
 * 提示：
 * 1 <= barcodes.length <= 10000
 * 1 <= barcodes[i] <= 10000
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2023/5/15
 */
public class RearrangeBarcodes {
    public int[] rearrangeBarcodes(int[] barcodes) {
        Map<Integer, Integer> freq = new HashMap<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o2[1], o1[1]));
        for (int barcode : barcodes) {
            freq.merge(barcode, 1, Integer::sum);
        }
        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            pq.offer(new int[] {entry.getKey(), entry.getValue()});
        }
        int n = barcodes.length, i = 0, pre = -1;
        while (i < n) {
            int[] cache = null;
            if (pq.peek()[0] == pre) {
                cache = pq.poll();
            }
            int[] data = pq.poll();
            barcodes[i++] = (pre = data[0]);
            if (--data[1] > 0) {
                pq.offer(data);
            }
            if (cache != null) {
                pq.offer(cache);
            }
        }
        return barcodes;
    }

    public static void main(String[] args) {
        int[] barcodes = {3, 7, 3, 7, 7, 7, 7, 2, 2, 2};
        RearrangeBarcodes rb = new RearrangeBarcodes();
        System.out.println(Arrays.toString(rb.rearrangeBarcodes(barcodes)));
    }
}
