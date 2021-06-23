package work.huangdu.exploration.coding_interviews.easy;

/**
 * 剑指 Offer 05. 替换空格
 * 请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
 * 示例 1：
 * 输入：s = "We are happy."
 * 输出："We%20are%20happy."
 * 限制：
 * 0 <= s 的长度 <= 10000
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/6/23
 */
public class ReplaceSpace {
    public String replaceSpace(String s) {
        int n = s.length(), p = 0;
        char[] array = new char[3 * n];
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c != ' ') {
                array[p++] = c;
            } else {
                array[p++] = '%';
                array[p++] = '2';
                array[p++] = '0';
            }
        }
        return new String(array, 0, p);
    }

    public String replaceSpace2(String s) {
        return s.replace(" ", "%20");
    }
}
