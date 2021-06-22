package work.huangdu.question_bank.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 剑指 Offer 38. 字符串的排列
 * 输入一个字符串，打印出该字符串中字符的所有排列。
 * 你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。
 * 示例:
 * 输入：s = "abc"
 * 输出：["abc","acb","bac","bca","cab","cba"]
 * 限制：
 * 1 <= s 的长度 <= 8
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/6/22
 */
public class Permutation {
    private int n;
    private char[] chars;
    private Set<String> set;

    public String[] permutation2(String s) {
        this.n = s.length();
        this.chars = s.toCharArray();
        this.set = new HashSet<>();
        backtrack2(0);
        return set.toArray(new String[0]);
    }

    private void backtrack2(int index) {
        if (index == n) {
            set.add(new String(chars));
            return;
        }
        for (int i = index; i < n; i++) {
            swap(index, i);
            backtrack2(index + 1);
            swap(index, i);
        }
    }

    private void swap(int a, int b) {
        char temp = chars[a];
        chars[a] = chars[b];
        chars[b] = temp;
    }

    private char[] temp;
    private boolean[] visited;
    private List<String> list;

    public String[] permutation(String s) {
        this.n = s.length();
        this.chars = s.toCharArray();
        this.temp = new char[this.n];
        this.visited = new boolean[this.n];
        this.list = new ArrayList<>();
        Arrays.sort(this.chars);
        backtrack(0);
        return list.toArray(new String[0]);
    }

    private void backtrack(int index) {
        if (index == n) {
            list.add(new String(temp));
            return;
        }
        for (int i = 0; i < n; i++) {
            if (visited[i] || i > 0 && chars[i] == chars[i - 1] && !visited[i - 1]) {continue;}
            temp[index] = chars[i];
            visited[i] = true;
            backtrack(index + 1);
            visited[i] = false;
        }
    }

    public static void main(String[] args) {
        Permutation permutation = new Permutation();
        System.out.println(Arrays.toString(permutation.permutation("aabc")));
    }
}
