package work.huangdu.question_bank.medium;

/**
 * 面试题 05.02. 二进制数转字符串
 * 二进制数转字符串。给定一个介于0和1之间的实数（如0.72），类型为double，打印它的二进制表达式。如果该数字无法精确地用32位以内的二进制表示，则打印“ERROR”。
 * 示例1:
 * 输入：0.625
 * 输出："0.101"
 * 示例2:
 * 输入：0.1
 * 输出："ERROR"
 * 提示：0.1无法被二进制准确表示
 * 提示：
 * 32位包括输出中的 "0." 这两位。
 * 题目保证输入用例的小数位数最多只有 6 位
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2023/3/2
 */
public class PrintBin {
    public String printBin(double num) {
        StringBuilder ans = new StringBuilder("0.");
        double base = 0.5;
        while (num > 0 && ans.length() <= 32) {
            if (num >= base) {
                num -= base;
                ans.append('1');
            } else {
                ans.append('0');
            }
            base *= 0.5;
        }
        return ans.length() > 32 ? "ERROR" : ans.toString();
    }

    public static void main(String[] args) {
        PrintBin pb = new PrintBin();
        System.out.println(pb.printBin(0.1));
    }
}
