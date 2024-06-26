package work.huangdu.exploration.start_from_scratch.graph_search.backtrack;

/**
 * 526. 优美的排列
 * 假设有从 1 到 N 的 N 个整数，如果从这 N 个数字中成功构造出一个数组，使得数组的第 i 位 (1 <= i <= N) 满足如下两个条件中的一个，我们就称这个数组为一个优美的排列。条件：
 * 第 i 位的数字能被 i 整除
 * i 能被第 i 位上的数字整除
 * 现在给定一个整数 N，请问可以构造多少个优美的排列？
 * 示例1:
 * 输入: 2
 * 输出: 2
 * 解释:
 * 第 1 个优美的排列是 [1, 2]:
 * 第 1 个位置（i=1）上的数字是1，1能被 i（i=1）整除
 * 第 2 个位置（i=2）上的数字是2，2能被 i（i=2）整除
 * 第 2 个优美的排列是 [2, 1]:
 * 第 1 个位置（i=1）上的数字是2，2能被 i（i=1）整除
 * 第 2 个位置（i=2）上的数字是1，i（i=2）能被 1 整除
 * 说明:
 * N 是一个正整数，并且不会超过15。
 *
 * @author huangdu
 * @version 2021/3/26
 */
public class CountArrangement {
    private int count;
    private int n;

    public int countArrangement(int n) {
        this.count = 0;
        this.n = n;
        backtrack(new boolean[n + 1], 1);
        return this.count;
    }

    private void backtrack(boolean[] used, int index) {
        if (index > n) {
            count++;
            return;
        }
        for (int num = 1; num <= n; num++) {
            if (!used[num] && (num % index == 0 || index % num == 0)) {
                used[num] = true;
                backtrack(used, index + 1);
                used[num] = false;
            }
        }
    }

    public static void main(String[] args) {
        CountArrangement ca = new CountArrangement();
        System.out.println(ca.countArrangement(15));
    }
}
class CountArrangement2 {
    private int count;
    private int n;
    private int[] arrangement;

    public int countArrangement(int n) {
        this.count = 0;
        this.n = n;
        this.arrangement = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            this.arrangement[i] = i;
        }
        backtrack(1);
        return this.count;
    }

    private void backtrack(int index) {
        if (index > n) {
            this.count++;
            return;
        }
        for (int next = index; next <= n; next++) {
            if (arrangement[next] % index != 0 && index % arrangement[next] != 0) { continue; }
            swap(index, next);
            backtrack(index + 1);
            swap(index, next);
        }
    }

    private void swap(int a, int b) {
        int temp = arrangement[a];
        arrangement[a] = arrangement[b];
        arrangement[b] = temp;
    }
}