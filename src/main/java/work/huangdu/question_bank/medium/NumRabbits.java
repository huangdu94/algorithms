package work.huangdu.question_bank.medium;

import java.util.Arrays;

/**
 * 781. 森林中的兔子
 * 森林中，每个兔子都有颜色。其中一些兔子（可能是全部）告诉你还有多少其他的兔子和自己有相同的颜色。我们将这些回答放在 answers 数组里。
 * 返回森林中兔子的最少数量。
 * 示例:
 * 输入: answers = [1, 1, 2]
 * 输出: 5
 * 解释:
 * 两只回答了 "1" 的兔子可能有相同的颜色，设为红色。
 * 之后回答了 "2" 的兔子不会是红色，否则他们的回答会相互矛盾。
 * 设回答了 "2" 的兔子为蓝色。
 * 此外，森林中还应有另外 2 只蓝色兔子的回答没有包含在数组中。
 * 因此森林中兔子的最少数量是 5: 3 只回答的和 2 只没有回答的。
 * 输入: answers = [10, 10, 10]
 * 输出: 11
 * 输入: answers = []
 * 输出: 0
 * 说明:
 * answers 的长度最大为1000。
 * answers[i] 是在 [0, 999] 范围内的整数。
 *
 * @author huangdu
 * @version 2021/4/4
 */
public class NumRabbits {
    public int numRabbits(int[] answers) {
        Arrays.sort(answers);
        int n = answers.length, sum = 0, pre = -1, count = 0;
        // 跳过开头为0的，正好可以共用一个变量
        while (answers[sum] == 0) {
            sum++;
        }
        for (int i = sum; i < n; i++) {
            if (answers[i] != pre || count == 0) {
                count = answers[i];
                pre = count;
                sum += (count + 1);
            } else {
                count--;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        NumRabbits nr = new NumRabbits();
        int[] answers = {10, 10, 10};
        System.out.println(nr.numRabbits(answers));
    }
}
