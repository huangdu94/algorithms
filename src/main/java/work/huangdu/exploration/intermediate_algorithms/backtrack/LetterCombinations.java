package work.huangdu.exploration.intermediate_algorithms.backtrack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 17. 电话号码的字母组合
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 * 示例 1：
 * 输入：digits = "23"
 * 输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
 * 示例 2：
 * 输入：digits = ""
 * 输出：[]
 * 示例 3：
 * 输入：digits = "2"
 * 输出：["a","b","c"]
 * 提示：
 * 0 <= digits.length <= 4
 * digits[i] 是范围 ['2', '9'] 的一个数字。
 *
 * @author huangdu
 * @version 2020/7/9 0:19
 */
public class LetterCombinations {
    private static final char[][] dictionary = {
        {'a', 'b', 'c'},
        {'d', 'e', 'f'},
        {'g', 'h', 'i'},
        {'j', 'k', 'l'},
        {'m', 'n', 'o'},
        {'p', 'q', 'r', 's'},
        {'t', 'u', 'v'},
        {'w', 'x', 'y', 'z'}
    };

    public List<String> letterCombinations(String digits) {
        List<String> resultList = new ArrayList<>();
        if (digits == null || digits.length() == 0) { return resultList; }
        //this.backtrack(digits.toCharArray(), 0, resultList, "");
        this.backtrack2(digits.toCharArray(), 0, resultList, new char[digits.length()]);
        return resultList;
    }

    /**
     * 回溯算法实现
     *
     * @param numArr     数字字符数组
     * @param depth      当前深度,深度大于numArr-1停止
     * @param resultList 结果list
     * @param pre        上一个深度生成的字符串
     */
    private void backtrack(char[] numArr, int depth, List<String> resultList, String pre) {
        if (depth > numArr.length - 1) {
            resultList.add(pre);
            return;
        }
        for (char c : dictionary[numArr[depth++] - '2']) { this.backtrack(numArr, depth, resultList, pre + c); }
    }

    /**
     * 回溯算法实现
     *
     * @param numArr     数字字符数组
     * @param depth      当前深度,深度大于numArr-1停止
     * @param resultList 结果list
     * @param pre        字符数组
     */
    private void backtrack2(char[] numArr, int depth, List<String> resultList, char[] pre) {
        if (depth > numArr.length - 1) {
            resultList.add(new String(pre));
            return;
        }
        for (char c : dictionary[numArr[depth] - '2']) {
            pre[depth] = c;
            this.backtrack2(numArr, depth + 1, resultList, pre);
        }
    }

    private static final String[][] map = {
        {"a", "b", "c"},
        {"d", "e", "f"},
        {"g", "h", "i"},
        {"j", "k", "l"},
        {"m", "n", "o"},
        {"p", "q", "r", "s"},
        {"t", "u", "v"},
        {"w", "x", "y", "z"}
    };

    public List<String> letterCombinations2(String digits) {
        LinkedList<String> queue = new LinkedList<>();
        if (digits.length() == 0) {return queue;}
        queue.addLast("");
        for (char digit : digits.toCharArray()) {
            int size = queue.size();
            while (size-- > 0) {
                String cur = queue.removeFirst();
                for (String next : map[digit - '2']) {
                    queue.addLast(cur.concat(next));
                }
            }
        }
        return queue;
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        List<String> result = null;
        result = new LetterCombinations().letterCombinations("23");
        long end = System.currentTimeMillis();
        System.out.println("结果：" + result);
        System.out.println("时间(ms)：" + (end - start));
    }
}
