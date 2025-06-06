package work.huangdu.exploration.primary_algorithms.math;

import java.util.HashMap;
import java.util.Map;

/**
 * 13. 罗马数字转整数
 * 罗马数字包含以下七种字符: I， V， X， L，C，D 和 M。
 * 字符          数值
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
 * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
 * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
 * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。
 * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
 * 给定一个罗马数字，将其转换成整数。输入确保在 1 到 3999 的范围内。
 * 示例 1:
 * 输入: "III"
 * 输出: 3
 * 示例 2:
 * 输入: "IV"
 * 输出: 4
 * 示例 3:
 * 输入: "IX"
 * 输出: 9
 * 示例 4:
 * 输入: "LVIII"
 * 输出: 58
 * 解释: L = 50, V= 5, III = 3.
 * 示例 5:
 * 输入: "MCMXCIV"
 * 输出: 1994
 * 解释: M = 1000, CM = 900, XC = 90, IV = 4.
 * 提示：
 * 1 <= s.length <= 15
 * s 仅含字符 ('I', 'V', 'X', 'L', 'C', 'D', 'M')
 * 题目数据保证 s 是一个有效的罗马数字，且表示整数在范围 [1, 3999] 内
 * 题目所给测试用例皆符合罗马数字书写规则，不会出现跨位等情况。
 * IL 和 IM 这样的例子并不符合题目要求，49 应该写作 XLIX，999 应该写作 CMXCIX 。
 * 关于罗马数字的详尽书写规则，可以参考 罗马数字 - Mathematics 。
 *
 * @author huangdu
 * @version 2020/7/1 16:21
 */
public class RomanToInt {
    private static final Map<String, Integer> romanMap = new HashMap<>();

    static {
        romanMap.put("I", 1);
        romanMap.put("IV", 4);
        romanMap.put("V", 5);
        romanMap.put("IX", 9);
        romanMap.put("X", 10);
        romanMap.put("XL", 40);
        romanMap.put("L", 50);
        romanMap.put("XC", 90);
        romanMap.put("C", 100);
        romanMap.put("CD", 400);
        romanMap.put("D", 500);
        romanMap.put("CM", 900);
        romanMap.put("M", 1000);
    }

    public int romanToInt(String s) {
        int result = 0;
        int index = 0;
        while (index < s.length()) {
            char c1 = s.charAt(index++);
            // I X C
            if (index < s.length() && (c1 == 'I' || c1 == 'X' || c1 == 'C')) {
                char c2 = s.charAt(index);
                Integer number = romanMap.get("" + c1 + c2);
                if (number != null) {
                    result += number;
                    index++;
                    continue;
                }
            }
            result += romanMap.get("" + c1);
        }
        return result;
    }

    public int romanToInt2(String s) {
        int res = 0, i = 0, len = s.length();
        while (i < len) {
            if (i + 2 <= len && romanMap.containsKey(s.substring(i, i + 2))) {
                res += romanMap.get(s.substring(i, i + 2));
                i += 2;
            } else {
                res += romanMap.get(s.substring(i, i + 1));
                i++;
            }
        }
        return res;
    }

    private static final Map<Character, Integer> romanIntMap = new HashMap<>();

    static {

        romanIntMap.put('I', 1);
        romanIntMap.put('V', 5);
        romanIntMap.put('X', 10);
        romanIntMap.put('L', 50);
        romanIntMap.put('C', 100);
        romanIntMap.put('D', 500);
        romanIntMap.put('M', 1000);
    }

    public int romanToInt3(String s) {
        int n = s.length(), i = 0, result = 0;
        char[] arr = s.toCharArray();
        while (i < n) {
            int cur = romanIntMap.get(arr[i]);
            if (i + 1 < n) {
                int next = romanIntMap.get(arr[i + 1]);
                if (next > cur) {
                    cur = next - cur;
                    i++;
                }
            }
            result += cur;
            i++;
        }
        return result;
    }

    public int romanToInt4(String s) {
        int n = s.length(), i = 0, ans = 0;
        while (i < n) {
            int num1 = convert(s.charAt(i));
            if (i + 1 < n) {
                int num2 = convert(s.charAt(i + 1));
                if (num2 > num1) {
                    ans += num2 - num1;
                    i++;
                } else {
                    ans += num1;
                }
            } else {
                ans += num1;
            }
            i++;
        }
        return ans;
    }

    private int convert(char ch) {
        switch (ch) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            default:
                // M
                return 1000;
        }
    }
}
