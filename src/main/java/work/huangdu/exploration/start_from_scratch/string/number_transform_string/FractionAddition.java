package work.huangdu.exploration.start_from_scratch.string.number_transform_string;

import java.util.ArrayList;
import java.util.List;

/**
 * 592. 分数加减运算
 * 给定一个表示分数加减运算表达式的字符串，你需要返回一个字符串形式的计算结果。 这个结果应该是不可约分的分数，即最简分数。 如果最终结果是一个整数，例如 2，你需要将它转换成分数形式，其分母为 1。所以在上述例子中, 2 应该被转换为 2/1。
 * 示例 1:
 * 输入:"-1/2+1/2"
 * 输出: "0/1"
 * 示例 2:
 * 输入:"-1/2+1/2+1/3"
 * 输出: "1/3"
 * 示例 3:
 * 输入:"1/3-1/2"
 * 输出: "-1/6"
 * 示例 4:
 * 输入:"5/3+1/3"
 * 输出: "2/1"
 * 说明:
 * 输入和输出字符串只包含 '0' 到 '9' 的数字，以及 '/', '+' 和 '-'。
 * 输入和输出分数格式均为 ±分子/分母。如果输入的第一个分数或者输出的分数是正数，则 '+' 会被省略掉。
 * 输入只包含合法的最简分数，每个分数的分子与分母的范围是  [1,10]。 如果分母是1，意味着这个分数实际上是一个整数。
 * 输入的分数个数范围是 [1,10]。
 * 最终结果的分子与分母保证是 32 位整数范围内的有效整数。
 *
 * @author huangdu
 * @version 2020/9/27 9:23
 */
public class FractionAddition {
    public String fractionAddition(String expression) {
        List<String> numStrList = getNumStrList(expression);
        int num = 0, den = 1;
        for (int i = 0, len = numStrList.size(); i < len; i += 2) {
            int curNum = Integer.parseInt(numStrList.get(i));
            int curDen = Integer.parseInt(numStrList.get(i + 1));
            if (curDen == den) {
                num += curNum;
            } else {
                int lcm = lcm(den, curDen);
                num = lcm / den * num + lcm / curDen * curNum;
                den = lcm;
            }
        }
        return generate(num, den);
    }

    private List<String> getNumStrList(String expression) {
        List<String> numStrList = new ArrayList<>();
        int start = expression.indexOf('/'), end;
        numStrList.add(expression.substring(0, start));
        while ((end = expression.indexOf('/', start + 1)) != -1) {
            int mid = -1;
            for (int i = start + 2; i < end; i++) {
                int c = expression.charAt(i);
                if (c == '+' || c == '-') {
                    mid = i;
                    break;
                }
            }
            numStrList.add(expression.substring(start + 1, mid));
            numStrList.add(expression.substring(mid, end));
            start = end;
        }
        numStrList.add(expression.substring(start + 1));
        return numStrList;
    }

    private int lcm(int a, int b) {
        return a / gcd(a, b) * b;
    }

    private int gcd(int a, int b) {
        if (a == b) return b;
        boolean aEven = (a & 1) == 0;
        boolean bEven = (b & 1) == 0;
        if (aEven && bEven) {
            return gcd(a >> 1, b >> 1) << 1;
        } else if (aEven) {
            return gcd(a >> 1, b);
        } else if (bEven) {
            return gcd(a, b >> 1);
        } else {
            return gcd(Math.abs(a - b), Math.min(a, b));
        }
    }

    private String generate(int a, int b) {
        if (a == 0) {
            b = 1;
        } else {
            int gcd = gcd(Math.abs(a), b);
            a /= gcd;
            b /= gcd;
        }
        return new StringBuilder().append(a).append('/').append(b).toString();
    }

    public static void main(String[] args) {
        FractionAddition addition = new FractionAddition();
        String expression = "1/3-1/2";
        System.out.println(addition.fractionAddition(expression));
    }
}
