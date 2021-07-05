package work.huangdu.question_bank.difficult;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.TreeMap;

/**
 * 726. 原子的数量
 * 给定一个化学式formula（作为字符串），返回每种原子的数量。
 * 原子总是以一个大写字母开始，接着跟随0个或任意个小写字母，表示原子的名字。
 * 如果数量大于 1，原子后会跟着数字表示原子的数量。如果数量等于 1 则不会跟数字。例如，H2O 和 H2O2 是可行的，但 H1O2 这个表达是不可行的。
 * 两个化学式连在一起是新的化学式。例如 H2O2He3Mg4 也是化学式。
 * 一个括号中的化学式和数字（可选择性添加）也是化学式。例如 (H2O2) 和 (H2O2)3 是化学式。
 * 给定一个化学式，输出所有原子的数量。格式为：第一个（按字典序）原子的名子，跟着它的数量（如果数量大于 1），然后是第二个原子的名字（按字典序），跟着它的数量（如果数量大于 1），以此类推。
 * 示例 1:
 * 输入:
 * formula = "H2O"
 * 输出: "H2O"
 * 解释:
 * 原子的数量是 {'H': 2, 'O': 1}。
 * 示例 2:
 * 输入:
 * formula = "Mg(OH)2"
 * 输出: "H2MgO2"
 * 解释:
 * 原子的数量是 {'H': 2, 'Mg': 1, 'O': 2}。
 * 示例 3:
 * 输入:
 * formula = "K4(ON(SO3)2)2"
 * 输出: "K4N2O14S4"
 * 解释:
 * 原子的数量是 {'K': 4, 'N': 2, 'O': 14, 'S': 4}。
 * 注意:
 * 所有原子的第一个字母为大写，剩余字母都是小写。
 * formula的长度在[1, 1000]之间。
 * formula只包含字母、数字和圆括号，并且题目中给定的是合法的化学式。
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/7/5
 */
public class CountOfAtoms {
    private int n;
    private String formula;
    private Map<String, String> countMap;
    private int index;
    private Deque<String> atomStack;
    private Deque<String> countStack;

    public String countOfAtoms(String formula) {
        this.n = formula.length();
        this.formula = formula;
        this.countMap = new TreeMap<>(String::compareTo);
        this.index = 0;
        this.atomStack = new ArrayDeque<>();
        this.countStack = new ArrayDeque<>();
        computer();
        return generateResult();
    }

    private void computer() {
        while (index < n) {
            char c = formula.charAt(index);
            if (c != '(' && c != ')') {
                String atom = readAtomName();
                String count = readCount();
                if (atomStack.isEmpty()) {
                    record(atom, count);
                } else {
                    atomStack.push(atom);
                    countStack.push(count);
                }
            } else if (c == '(') {
                atomStack.push("(");
                index++;
            } else {
                index++;
                String multiple = readCount();
                Deque<String> tempAtomStack = new ArrayDeque<>(), tempCountStack = new ArrayDeque<>();
                String curAtom;
                while (!(curAtom = atomStack.pop()).equals("(")) {
                    tempAtomStack.push(curAtom);
                    tempCountStack.push(multiply(countStack.pop(), multiple));
                }
                if (atomStack.isEmpty()) {
                    while (!tempAtomStack.isEmpty()) {
                        record(tempAtomStack.pop(), tempCountStack.pop());
                    }
                } else {
                    while (!tempAtomStack.isEmpty()) {
                        atomStack.push(tempAtomStack.pop());
                        countStack.push(tempCountStack.pop());
                    }
                }
            }
        }
    }

    private String readAtomName() {
        int tail = index + 1;
        char c;
        while (tail < n && (c = formula.charAt(tail)) <= 'z' && c >= 'a') {
            tail++;
        }
        String atom = formula.substring(index, tail);
        index = tail;
        return atom;
    }

    private String readCount() {
        int tail = index;
        char c;
        while (tail < n && (c = formula.charAt(tail)) <= '9' && c >= '0') {
            tail++;
        }
        if (index == tail) {
            return "1";
        }
        String count = formula.substring(index, tail);
        index = tail;
        return count;
    }

    private void record(String atom, String count) {
        if (countMap.containsKey(atom)) {
            String cur = countMap.get(atom);
            String add = cur.length() >= count.length() ? addition(count, cur) : addition(cur, count);
            countMap.put(atom, add);
        } else {
            countMap.put(atom, count);
        }
    }

    private String generateResult() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : countMap.entrySet()) {
            sb.append(entry.getKey()).append("1".equals(entry.getValue()) ? "" : entry.getValue());
        }
        return sb.toString();
    }

    // 将乘法转化为num1×num2的每一位，然后再求和的问题
    private String multiply(String num1, String num2) {
        if (num1.length() < num2.length()) { return multiply(num2, num1); }
        if ("0".equals(num1) || "0".equals(num2)) { return "0"; }
        String res = "";
        for (int i = num2.length() - 1; i >= 0; i--) {
            res = addition(res, fillZero(multiplySingleDigit(num1, charToInt(num2.charAt(i))), num2.length() - 1 - i));
        }
        return res;
    }

    // num1×(n2为 String num2中的一位)
    private String multiplySingleDigit(String num1, int n2) {
        char[] product = new char[num1.length() + 1];
        int index = product.length - 1, carry = 0;
        for (int i = num1.length() - 1; i >= 0; i--) {
            int n1 = charToInt(num1.charAt(i));
            int p = n1 * n2 + carry;
            carry = p / 10;
            product[index--] = intToChar(p % 10);
        }
        if (carry != 0) { product[index--] = intToChar(carry); }
        return new String(product, index + 1, product.length - 1 - index);
    }

    // 加法
    private String addition(String res, String next) {
        if ("".equals(res)) { return next; }
        char[] sum = new char[next.length() + 1];
        int index = sum.length - 1, carry = 0;
        for (int i = next.length() - 1, j = res.length() - 1; i >= 0; i--, j--) {
            int n1 = charToInt(next.charAt(i));
            int n2 = j >= 0 ? charToInt(res.charAt(j)) : 0;
            int s = n1 + n2 + carry;
            carry = s / 10;
            sum[index--] = intToChar(s % 10);
        }
        if (carry != 0) { sum[index--] = intToChar(carry); }
        return new String(sum, index + 1, sum.length - 1 - index);
    }

    // 末尾补0
    private String fillZero(String product, int i) {
        if (i == 0) { return product; }
        StringBuilder sb = new StringBuilder(product.length() + i);
        sb.append(product);
        for (int k = 0; k < i; k++) { sb.append('0'); }
        return sb.toString();
    }

    // 字符转数字
    private int charToInt(char c) {
        return c - '0';
    }

    // 数字转字符
    private char intToChar(int i) {
        return (char)(i + '0');
    }

    public static void main(String[] args) {
        CountOfAtoms coa = new CountOfAtoms();
        System.out.println(coa.countOfAtoms("K4(ON(SO3)2)2"));
    }
}
