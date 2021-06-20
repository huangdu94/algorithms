package work.huangdu.question_bank.difficult;

/**
 * 65. 有效数字
 * 有效数字（按顺序）可以分成以下几个部分：
 * 一个 小数 或者 整数
 * （可选）一个 'e' 或 'E' ，后面跟着一个 整数
 * 小数（按顺序）可以分成以下几个部分：
 * （可选）一个符号字符（'+' 或 '-'）
 * 下述格式之一：
 * 至少一位数字，后面跟着一个点 '.'
 * 至少一位数字，后面跟着一个点 '.' ，后面再跟着至少一位数字
 * 一个点 '.' ，后面跟着至少一位数字
 * 整数（按顺序）可以分成以下几个部分：
 * （可选）一个符号字符（'+' 或 '-'）
 * 至少一位数字
 * 部分有效数字列举如下：
 * ["2", "0089", "-0.1", "+3.14", "4.", "-.9", "2e10", "-90E3", "3e+7", "+6e-1", "53.5e93", "-123.456e789"]
 * 部分无效数字列举如下：
 * ["abc", "1a", "1e", "e3", "99e2.5", "--6", "-+3", "95a54e53"]
 * 给你一个字符串 s ，如果 s 是一个 有效数字 ，请返回 true 。
 * 示例 1：
 * 输入：s = "0"
 * 输出：true
 * 示例 2：
 * 输入：s = "e"
 * 输出：false
 * 示例 3：
 * 输入：s = "."
 * 输出：false
 * 示例 4：
 * 输入：s = ".1"
 * 输出：true
 * 提示：
 * 1 <= s.length <= 20
 * s 仅含英文字母（大写和小写），数字（0-9），加号 '+' ，减号 '-' ，或者点 '.' 。
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/6/17
 */
public class IsNumber {
    // 有限状态机题目 把各个状态及状态间的跳转理清楚就行
    public boolean isNumber(String s) {
        // finite-state machine 有限状态机
        int[][] fsm = {
            // 初始状态
            {1, 2, 3, -1, -1},
            // 非指数部分，选了正负号
            {-1, 2, 3, -1, -1},
            // 非指数部分，没选数字直接选了点
            {-1, -1, 4, -1, -1},
            // 非指数部分，选了数字还没有选点
            {-1, 4, 3, 5, -1},
            // 非指数部分，选了数字选了点
            {-1, -1, 4, 5, -1},
            // 指数部分初始状态
            {6, -1, 7, -1, -1},
            // 指数部分，选了正负号，还没选数字
            {-1, -1, 7, -1, -1},
            // 指数部分，选了数字
            {-1, -1, 7, -1, -1}
        };
        int n = s.length(), state = 0;
        for (int i = 0; i < n; i++) {
            state = fsm[state][getIndexByChar(s.charAt(i))];
            if (state == -1) { return false; }
        }
        return state == 3 || state == 4 || state == 7;
    }

    private int getIndexByChar(char c) {
        if (c == '+' || c == '-') {
            return 0;
        }
        if (c == '.') {
            return 1;
        }
        if (c == '0' || c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == '8' || c == '9') {
            return 2;
        }
        if (c == 'e' || c == 'E') {
            return 3;
        }
        return 4;
    }

    public static void main(String[] args) {
        IsNumber is = new IsNumber();
        String s = "005047e+6";
        System.out.println(is.isNumber(s));
    }
}
