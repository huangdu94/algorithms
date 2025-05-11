package work.huangdu.question_bank.medium;

/**
 * 537. 复数乘法
 * 复数 可以用字符串表示，遵循 "实部+虚部i" 的形式，并满足下述条件：
 * 实部 是一个整数，取值范围是 [-100, 100]
 * 虚部 也是一个整数，取值范围是 [-100, 100]
 * i^2 == -1
 * 给你两个字符串表示的复数 num1 和 num2 ，请你遵循复数表示形式，返回表示它们乘积的字符串。
 * 示例 1：
 * 输入：num1 = "1+1i", num2 = "1+1i"
 * 输出："0+2i"
 * 解释：(1 + i) * (1 + i) = 1 + i^2 + 2 * i = 2i ，你需要将它转换为 0+2i 的形式。
 * 示例 2：
 * 输入：num1 = "1+-1i", num2 = "1+-1i"
 * 输出："0+-2i"
 * 解释：(1 - i) * (1 - i) = 1 + i2 - 2 * i = -2i ，你需要将它转换为 0+-2i 的形式。
 * 提示：
 * num1 和 num2 都是有效的复数表示。
 *
 * @author huangdu
 * @version 2022/2/25
 */
public class ComplexNumberMultiply {
    public String complexNumberMultiply(String num1, String num2) {
        String[] arr1 = num1.split("\\+"), arr2 = num2.split("\\+");
        int real1 = Integer.parseInt(arr1[0]), imaginary1 = Integer.parseInt(arr1[1].substring(0, arr1[1].length() - 1)),
            real2 = Integer.parseInt(arr2[0]), imaginary2 = Integer.parseInt(arr2[1].substring(0, arr2[1].length() - 1)),
            real = real1 * real2 - imaginary1 * imaginary2,
            imaginary = real1 * imaginary2 + real2 * imaginary1;
        return String.format("%s+%si", real, imaginary);
    }
}
