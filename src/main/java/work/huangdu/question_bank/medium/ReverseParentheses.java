package work.huangdu.question_bank.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 1190. 反转每对括号间的子串
 * 给出一个字符串 s（仅含有小写英文字母和括号）。
 * 请你按照从括号内到外的顺序，逐层反转每对匹配括号中的字符串，并返回最终的结果。
 * 注意，您的结果中 不应 包含任何括号。
 * 示例 1：
 * 输入：s = "(abcd)"
 * 输出："dcba"
 * 示例 2：
 * 输入：s = "(u(love)i)"
 * 输出："iloveu"
 * 示例 3：
 * 输入：s = "(ed(et(oc))el)"
 * 输出："leetcode"
 * 示例 4：
 * 输入：s = "a(bcdefghijkl(mno)p)q"
 * 输出："apmnolkjihgfedcbq"
 * 提示：
 * 0 <= s.length <= 2000
 * s 中只有小写英文字母和括号
 * 我们确保所有括号都是成对出现的
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/5/26
 */
public class ReverseParentheses {
    private char[] chars;
    private int n;
    private int i = 0;

    public String reverseParentheses(String s) {
        chars = s.toCharArray();
        n = s.length();
        i = 0;
        return visited(helper(0, 0));
    }

    private Element helper(int level, int start) {
        Element node = new Element(level, null);
        while (i < n && chars[i] != ')') {
            if (chars[i] == '(') {
                if (i > start) {
                    node.addSon(level, Arrays.copyOfRange(chars, start, i));
                }
                i++;
                node.addSon(helper(level + 1, i));
                start = i + 1;
            }
            i++;
        }
        if (node.listIsNull()) {
            node.chars = Arrays.copyOfRange(chars, start, i);
        } else if (i > start) {
            node.addSon(level, Arrays.copyOfRange(chars, start, i));
        }
        return node;
    }

    private String visited(Element node) {
        boolean reverseFlag = (node.level & 1) == 1;
        if (node.listIsNull()) {
            return reverseFlag ? reverse(node.chars) : String.valueOf(chars);
        }
        if (reverseFlag) {
            Collections.reverse(node.list);
        }
        StringBuilder result = new StringBuilder();
        for (Element element : node.list) {
            result.append(visited(element));
        }
        return result.toString();
    }

    private String reverse(char[] chars) {
        for (int i = 0, j = chars.length - 1; i < j; i++, j--) {
            char temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;
        }
        return String.valueOf(chars);
    }

    private static class Element {
        int level;
        char[] chars;
        List<Element> list;

        Element(int level, char[] chars) {
            this.level = level;
            this.chars = chars;
        }

        boolean listIsNull() {
            return list == null;
        }

        void initList() {
            if (listIsNull()) {list = new ArrayList<>();}
        }

        void addSon(int level, char[] sub) {
            initList();
            list.add(new Element(level, sub));
        }

        void addSon(Element element) {
            initList();
            list.add(element);
        }
    }

    public static void main(String[] args) {
        ReverseParentheses rp = new ReverseParentheses();
        System.out.println(rp.reverseParentheses("((abcd))"));
    }
}
