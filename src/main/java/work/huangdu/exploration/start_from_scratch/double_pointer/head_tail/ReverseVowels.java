package work.huangdu.exploration.start_from_scratch.double_pointer.head_tail;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 345. 反转字符串中的元音字母
 * 编写一个函数，以字符串作为输入，反转该字符串中的元音字母。
 * 示例 1：
 * 输入："hello"
 * 输出："holle"
 * 示例 2：
 * 输入："leetcode"
 * 输出："leotcede"
 * 提示：
 * 元音字母不包含字母 "y" 。
 *
 * @author huangdu
 * @version 2020/9/1 10:59
 */
public class ReverseVowels {
    public String reverseVowels2(String s) {
        char[] chars = s.toCharArray();
        int i = 0, j = s.length() - 1;
        while (i < j) {
            while (i < j && !isVowel(chars[i])) {
                i++;
            }
            while (i < j && !isVowel(chars[j])) {
                j--;
            }
            if (i < j) {
                char temp = chars[i];
                chars[i] = chars[j];
                chars[j] = temp;
                i++;
                j--;
            }
        }
        return new String(chars);
    }

    private boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u'
            || c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U';
    }

    public static void main(String[] args) {
        ReverseVowels reverseVowels = new ReverseVowels();
        String s = "hello";
        System.out.println(reverseVowels.reverseVowels(s));
    }

    public String reverseVowels(String s) {
        Set<Character> vowels = new HashSet(10);
        vowels.add('a');
        vowels.add('e');
        vowels.add('i');
        vowels.add('o');
        vowels.add('u');
        vowels.add('A');
        vowels.add('E');
        vowels.add('I');
        vowels.add('O');
        vowels.add('U');
        char[] chars = s.toCharArray();
        int i = 0, j = chars.length - 1;
        while (i < j) {
            while (i < j && !vowels.contains(chars[i])) {
                i++;
            }
            while (i < j && !vowels.contains(chars[j])) {
                j--;
            }
            if (i < j) {
                char temp = chars[i];
                chars[i] = chars[j];
                chars[j] = temp;
                i++;
                j--;
            }
        }
        return new String(chars);
    }

    private static final Set<Character> vowelSet = new HashSet<>();

    static {
        vowelSet.add('a');
        vowelSet.add('e');
        vowelSet.add('i');
        vowelSet.add('o');
        vowelSet.add('u');
        vowelSet.add('A');
        vowelSet.add('E');
        vowelSet.add('I');
        vowelSet.add('O');
        vowelSet.add('U');
    }

    public String reverseVowels3(String s) {
        int n = s.length();
        char[] chars = s.toCharArray();
        List<Character> charList = new ArrayList<>();
        List<Integer> indexList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            char c = chars[i];
            if (vowelSet.contains(c)) {
                charList.add(c);
                indexList.add(i);
            }
        }
        Collections.reverse(charList);
        for (int i = 0, size = charList.size(); i < size; i++) {
            chars[indexList.get(i)] = charList.get(i);
        }
        return new String(chars);
    }

}
