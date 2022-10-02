package work.huangdu.question_bank.medium;

/**
 * 777. 在LR字符串中交换相邻字符
 * 在一个由 'L' , 'R' 和 'X' 三个字符组成的字符串（例如"RXXLRXRXL"）中进行移动操作。一次移动操作指用一个"LX"替换一个"XL"，或者用一个"XR"替换一个"RX"。现给定起始字符串start和结束字符串end，请编写代码，当且仅当存在一系列移动操作使得start可以转换成end时， 返回True。
 * 示例 :
 * 输入: start = "RXXLRXRXL", end = "XRLXXRRLX"
 * 输出: True
 * 解释:
 * 我们可以通过以下几步将start转换成end:
 * RXXLRXRXL ->
 * XRXLRXRXL ->
 * XRLXRXRXL ->
 * XRLXXRRXL ->
 * XRLXXRRLX
 * 提示：
 * 1 <= len(start) = len(end) <= 10000。
 * start和end中的字符串仅限于'L', 'R'和'X'。
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2022/10/2
 */
public class CanTransform {
    public boolean canTransform(String start, String end) {
        int n = start.length(), startIdx = 0, endIdx = 0;
        while (startIdx < n && endIdx < n) {
            while (startIdx < n && start.charAt(startIdx) == 'X') {startIdx++;}
            while (endIdx < n && end.charAt(endIdx) == 'X') {endIdx++;}
            if (startIdx < n && endIdx < n) {
                if (start.charAt(startIdx) != end.charAt(endIdx)
                    || start.charAt(startIdx) == 'L' && startIdx < endIdx
                    || end.charAt(endIdx) == 'R' && startIdx > endIdx) {
                    return false;
                }
                startIdx++;
                endIdx++;
            }
        }
        while (startIdx < n) {
            if (start.charAt(startIdx) != 'X') {
                return false;
            }
            startIdx++;
        }
        while (endIdx < n) {
            if (end.charAt(endIdx) != 'X') {
                return false;
            }
            endIdx++;
        }
        return true;
    }

    public boolean canTransform2(String start, String end) {
        int n = start.length(), startIdx = 0, endIdx = 0;
        boolean startFlag = false, endFlag = false;
        while (startIdx < n && endIdx < n) {
            while (startIdx < n && start.charAt(startIdx) == 'X') {startIdx++;}
            if (startIdx < n) {startFlag = true;}
            while (endIdx < n && end.charAt(endIdx) == 'X') {endIdx++;}
            if (endIdx < n) {endFlag = true;}
            if (startIdx == n || endIdx == n) {
                break;
            }
            if (start.charAt(startIdx) != end.charAt(endIdx)) {
                return false;
            } else if (start.charAt(startIdx) == 'L') {
                if (startIdx < endIdx) {
                    return false;
                }
            } else {
                if (startIdx > endIdx) {
                    return false;
                }
            }
            startFlag = false;
            endFlag = false;
            startIdx++;
            endIdx++;
        }
        if (startFlag != endFlag) {return false;}
        if (startIdx == n) {
            while (++endIdx < n) {
                if (end.charAt(endIdx) != 'X') {
                    return false;
                }
            }
        } else {
            while (++startIdx < n) {
                if (start.charAt(startIdx) != 'X') {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean canTransform3(String start, String end) {
        StringBuilder startNoX = new StringBuilder(), endNoX = new StringBuilder();
        for (int i = 0, n = start.length(); i < n; i++) {
            if (start.charAt(i) != 'X') {
                startNoX.append(start.charAt(i));
            }
            if (end.charAt(i) != 'X') {
                endNoX.append(end.charAt(i));
            }
        }
        if (!startNoX.toString().equals(endNoX.toString())) {return false;}
        int[] startXCount = new int[startNoX.length()], endXCount = new int[endNoX.length()];
        int startXCnt = 0, startIdx = 0, endXCnt = 0, endIdx = 0;
        for (int i = 0, n = start.length(); i < n; i++) {
            if (start.charAt(i) == 'X') {
                startXCnt++;
            } else {
                startXCount[startIdx++] = startXCnt;
            }
            if (end.charAt(i) == 'X') {
                endXCnt++;
            } else {
                endXCount[endIdx++] = endXCnt;
            }
        }
        for (int i = 0, n = startNoX.length(); i < n; i++) {
            if (startNoX.charAt(i) == 'L') {
                if (startXCount[i] < endXCount[i]) {
                    return false;
                }
            } else {
                if (startXCount[i] > endXCount[i]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        CanTransform ct = new CanTransform();
        System.out.println(ct.canTransform("RXXLRXRXL", "XRLXXRRLX"));
    }
}
