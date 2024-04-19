package work.huangdu.question_bank.difficult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 1157. 子数组中占绝大多数的元素
 * 设计一个数据结构，有效地找到给定子数组的 多数元素 。
 * 子数组的 多数元素 是在子数组中出现 threshold 次数或次数以上的元素。
 * 实现 MajorityChecker 类:
 * MajorityChecker(int[] arr) 会用给定的数组 arr 对 MajorityChecker 初始化。
 * int query(int left, int right, int threshold) 返回子数组中的元素  arr[left...right] 至少出现 threshold 次数，如果不存在这样的元素则返回 -1。
 * 示例 1：
 * 输入:
 * ["MajorityChecker", "query", "query", "query"]
 * [[[1, 1, 2, 2, 1, 1]], [0, 5, 4], [0, 3, 3], [2, 3, 2]]
 * 输出：
 * [null, 1, -1, 2]
 * 解释：
 * MajorityChecker majorityChecker = new MajorityChecker([1,1,2,2,1,1]);
 * majorityChecker.query(0,5,4); // 返回 1
 * majorityChecker.query(0,3,3); // 返回 -1
 * majorityChecker.query(2,3,2); // 返回 2
 * 提示：
 * 1 <= arr.length <= 2 * 10^4
 * 1 <= arr[i] <= 2 * 10^4
 * 0 <= left <= right < arr.length
 * threshold <= right - left + 1
 * 2 * threshold > right - left + 1
 * 调用 query 的次数最多为 10^4
 *
 * @author huangdu
 * @version 2023/4/16
 */
public class MajorityChecker {
    public static final int K = 20;
    private final int[] arr;
    private final Map<Integer, List<Integer>> loc;
    private final Random random;

    public MajorityChecker(int[] arr) {
        this.arr = arr;
        this.loc = new HashMap<>();
        for (int i = 0; i < arr.length; ++i) {
            loc.putIfAbsent(arr[i], new ArrayList<>());
            loc.get(arr[i]).add(i);
        }
        this.random = new Random();
    }

    public int query(int left, int right, int threshold) {
        int length = right - left + 1;
        for (int i = 0; i < K; ++i) {
            int x = arr[left + random.nextInt(length)];
            List<Integer> pos = loc.get(x);
            int occ = binarySearch(pos, right, false) - binarySearch(pos, left, true);
            if (occ >= threshold) {
                return x;
            } else if (occ * 2 >= length) {
                return -1;
            }
        }
        return -1;
    }

    private int binarySearch(List<Integer> pos, int target, boolean equal) {
        int low = 0, high = pos.size();
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (pos.get(mid) > target || equal && pos.get(mid) == target) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
}
