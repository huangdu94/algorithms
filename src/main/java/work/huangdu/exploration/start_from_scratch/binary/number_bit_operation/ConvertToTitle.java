package work.huangdu.exploration.start_from_scratch.binary.number_bit_operation;

/**
 * 168. Excel表列名称
 * 给定一个正整数，返回它在 Excel 表中相对应的列名称。
 * 例如，
 * 1 -> A
 * 2 -> B
 * 3 -> C
 * ...
 * 26 -> Z
 * 27 -> AA
 * 28 -> AB
 * ...
 * 示例 1:
 * 输入: 1
 * 输出: "A"
 * 示例 2:
 * 输入: 28
 * 输出: "AB"
 * 示例 3:
 * 输入: 701
 * 输出: "ZY"
 *
 * @author huangdu
 * @version 2020/8/11 0:34
 */
public class ConvertToTitle {
    // Math.log(Integer.MAX_VALUE) / Math.log(26) = 6.595127660011333 < 7
    public String convertToTitle(int n) {
        char[] res = new char[7];
        int start = 6;
        while (n > 0) {
            int temp = n % 26;
            if (temp == 0) {
                temp = 26;
                n -= temp;
            }
            res[start--] = (char)(temp + 64);
            n /= 26;
        }
        return new String(res, start + 1, 6 - start);
    }

    public String convertToTitle2(int columnNumber) {
        StringBuilder sb = new StringBuilder();
        do {
            int bit = (columnNumber - 1) % 26;
            columnNumber = (columnNumber - 1) / 26;
            sb.insert(0, (char)(bit + 'A'));
        } while (columnNumber != 0);
        return sb.toString();
    }

    public static void main(String[] args) {
        ConvertToTitle convertToTitle = new ConvertToTitle();
        int n = 26;
        String res = convertToTitle.convertToTitle(n);
        System.out.println(res);
    }
}
