package work.huangdu.question_bank.difficult;

/**
 * @author huangdu
 * @version 2023/2/15
 */
public class IsGoodArray {
    // 裴蜀定理
    public boolean isGoodArray(int[] nums) {
        int gcd = nums[0];
        for (int i = 1; i < nums.length; i++) {
            gcd = gcd(gcd, nums[i]);
        }
        return gcd == 1;
    }

    public int gcd(int a, int b) {
        if (b == 0) {return a;}
        return gcd(b, a % b);
    }
}
