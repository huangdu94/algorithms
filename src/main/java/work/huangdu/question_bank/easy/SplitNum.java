package work.huangdu.question_bank.easy;

/**
 * @author huangdu
 */
public class SplitNum {
    public int splitNum(int num) {
        int[] count = new int[10], nums = new int[2];
        while (num > 0) {
            count[num % 10]++;
            num /= 10;
        }
        int i = 1, j = 0;
        while (i < 10) {
            if (count[i] == 0) {
                i++;
                continue;
            }
            count[i]--;
            nums[j & 1] = nums[j & 1] * 10 + i;
            j++;
        }
        return nums[0] + nums[1];
    }
}
