package work.huangdu.solution;

/**
 * Solution
 *
 * @author huangdu
 * @version 2025/5/27
 */
public class Solution {
    private static final int N = 7;
    private static final int[] NUMS = {1000, 500, 100, 50, 10, 5, 1};
    private static final char[] CHARS = {'M', 'D', 'C', 'L', 'X', 'V', 'I'};

    public String intToRoman(int num) {
        int i = 0;
        StringBuilder sb = new StringBuilder();
        while (i < N) {
            int n = num / NUMS[i];
            num -= n * NUMS[i];
            if (n > 0) {
                if (n < 4) {
                    char ch = CHARS[i];
                    for (int k = 0; k < n; k++) {
                        sb.append(ch);
                    }
                } else if (n == 4) {
                    sb.append(CHARS[i]);
                    sb.append(CHARS[i - 1]);
                } else if (n < 9) {
                    sb.append(CHARS[i - 1]);
                    char ch = CHARS[i];
                    for (int k = 5; k < n; k++) {
                        sb.append(ch);
                    }
                } else {
                    sb.append(CHARS[i]);
                    sb.append(CHARS[i - 2]);
                }
            }
            i += 2;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
    }
}
