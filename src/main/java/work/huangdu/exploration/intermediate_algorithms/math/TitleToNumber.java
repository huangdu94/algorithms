package work.huangdu.exploration.intermediate_algorithms.math;

/**
 * 171. Excel表列序号
 * 给你一个字符串 columnTitle ，表示 Excel 表格中的列名称。返回该列名称对应的列序号。
 * 例如，
 * A -> 1
 * B -> 2
 * C -> 3
 * ...
 * Z -> 26
 * AA -> 27
 * AB -> 28
 * ...
 * 示例 1:
 * 输入: columnTitle = "A"
 * 输出: 1
 * 示例 2:
 * 输入: columnTitle = "AB"
 * 输出: 28
 * 示例 3:
 * 输入: columnTitle = "ZY"
 * 输出: 701
 * 示例 4:
 * 输入: columnTitle = "FXSHRXW"
 * 输出: 2147483647
 * 提示：
 * 1 <= columnTitle.length <= 7
 * columnTitle 仅由大写英文组成
 * columnTitle 在范围 ["A", "FXSHRXW"] 内
 *
 * @author huangdu
 * @version 2020/7/22 0:16
 */
public class TitleToNumber {
    public int titleToNumber(String s) {
        int sum = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            sum = sum * 26 + (c - 64);
        }
        return sum;
    }
    public int titleToNumber2(String columnTitle) {
        int result = 0;
        for (char c : columnTitle.toCharArray()) {
            result = result * 26 + (c - '@');
        }
        return result;
    }

    public static void main(String[] args) {
        TitleToNumber ttn = new TitleToNumber();
        System.out.println(ttn.titleToNumber("FXSHRXW"));
    }
}
