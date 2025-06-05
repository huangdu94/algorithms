package work.huangdu.exploration.start_from_scratch.string.conversion;

/**
 * 6. Z 字形变换
 * 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
 * 比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
 * L   C   I   R
 * E T O E S I I G
 * E   D   H   N
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。
 * 请你实现这个将字符串进行指定行数变换的函数：
 * string convert(string s, int numRows);
 * 示例 1:
 * 输入: s = "LEETCODEISHIRING", numRows = 3
 * 输出: "LCIRETOESIIGEDHN"
 * 示例 2:
 * 输入: s = "LEETCODEISHIRING", numRows = 4
 * 输出: "LDREOEIIECIHNTSG"
 * 解释:
 * L     D     R
 * E   O E   I I
 * E C   I H   N
 * T     S     G
 *
 * @author huangdu
 * @version 2020/10/3 8:51
 */
public class Convert {
    public String convert(String s, int numRows) {
        if (numRows <= 1 || numRows >= s.length()) return s;
        int n = s.length(), interval = 2 * (numRows - 1);
        StringBuilder res = new StringBuilder(n);
        for (int row = 1; row <= numRows; row++) {
            int i = row - 1;
            while (i < n) {
                res.append(s.charAt(i));
                int a = 2 * (numRows - row);
                if (a > 0 && a < interval && i + a < n) {
                    res.append(s.charAt(i + a));
                }
                i += interval;
            }
        }
        return res.toString();
    }

    public String convert2(String s, int numRows) {
        int n = s.length();
        if (numRows == 1) {return s;}
        int i = 0, p = 0;
        char[] chars = new char[n];
        // 输出第一行
        int interval = numRows * 2 - 2, interval2 = 0;
        while (p < n) {
            chars[i++] = s.charAt(p);
            p += interval;
        }
        for (int row = 1; row < numRows - 1; row++) {
            interval -= 2;
            interval2 = numRows * 2 - 2 - interval;
            boolean flag = true;
            p = row;
            while (p < n) {
                chars[i++] = s.charAt(p);
                p += flag ? interval : interval2;
                flag = !flag;
            }
        }
        p = numRows - 1;
        interval += interval2;
        while (p < n && i < n) {
            chars[i++] = s.charAt(p);
            p += interval;
        }
        return new String(chars);
    }

    public String convert3(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        StringBuilder sb = new StringBuilder();
        for (int curRow = 1, interval = interval(numRows, 1), n = s.length(); curRow <= numRows; curRow++) {
            int i = curRow - 1, delta = interval(numRows, curRow);
            while (i < n) {
                sb.append(s.charAt(i));
                i += delta;
                if (delta != interval) {
                    delta = interval - delta;
                }
            }
        }
        return sb.toString();
    }

    private int interval(int numRows, int curRow) {
        if (numRows == curRow) {
            curRow = 1;
        }
        return (numRows << 1) - (curRow << 1);
    }
}
