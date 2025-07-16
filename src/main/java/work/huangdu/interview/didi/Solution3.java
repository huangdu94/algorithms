package work.huangdu.interview.didi;

/**
 * @author huangdu
 */
public class Solution3 {

    /**
     * 数组
     * 有一个元素超过整个数组长度的一半。
     * 时间复杂度o(n) 空间复杂度o(1)
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] arr = {1, 1, 2, 2, 3, 3, 1, 1, 1};
        int count = 0, ans = -1;
        for (int num : arr) {
            if (count == 0) {
                ans = num;
                count++;
            } else if (ans == num) {
                count++;
            } else {
                count--;
            }
        }
        System.out.println(ans);
    }
}