package work.huangdu.solution;

/**
 * Solution
 *
 * @author huangdu
 * @version 2025/5/27
 */
public class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length, left = 0, right = 0, remain = 0;
        boolean loop = false;
        do {
            remain += gas[right] - cost[right];
            while (remain < 0) {
                remain -= gas[left] - cost[left];
                if ((left = (left + 1) % n) == 0) {
                    loop = true;
                    break;
                }
            }
            right = (right + 1) % n;
        } while (left != right);
        return loop ? -1 : left;
    }
}
