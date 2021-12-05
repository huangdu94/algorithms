package work.huangdu.exploration.start_from_scratch.string.character_statistics;

import java.util.HashMap;
import java.util.Map;

/**
 * 383. 赎金信
 * 为了不在赎金信中暴露字迹，从杂志上搜索各个需要的字母，组成单词来表达意思。
 * 给你一个赎金信 (ransomNote) 字符串和一个杂志(magazine)字符串，判断 ransomNote 能不能由 magazines 里面的字符构成。
 * 如果可以构成，返回 true ；否则返回 false 。
 * magazine 中的每个字符只能在 ransomNote 中使用一次。
 * 示例 1：
 * 输入：ransomNote = "a", magazine = "b"
 * 输出：false
 * 示例 2：
 * 输入：ransomNote = "aa", magazine = "ab"
 * 输出：false
 * 示例 3：
 * 输入：ransomNote = "aa", magazine = "aab"
 * 输出：true
 * 提示：
 * 1 <= ransomNote.length, magazine.length <= 10^5
 * ransomNote 和 magazine 由小写英文字母组成
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2020/9/23 21:53
 */
public class CanConstruct {
    public boolean canConstruct(String ransomNote, String magazine) {
        if (ransomNote.length() > magazine.length()) { return false; }
        int[] records = new int[26];
        int rLen = ransomNote.length(), mLen = magazine.length(), remain = rLen;
        for (int i = 0; i < rLen; i++) {
            records[ransomNote.charAt(i) - 'a']++;
        }
        for (int i = 0; i < mLen && remain > 0; i++) {
            int m = magazine.charAt(i) - 'a';
            if (records[m] != 0) {
                records[m]--;
                remain--;
            }
        }
        return remain == 0;
    }

    public boolean canConstruct2(String ransomNote, String magazine) {
        if (ransomNote.length() > magazine.length()) { return false; }
        Map<Character, Integer> characterIntegerMap = new HashMap<>();
        int rLen = ransomNote.length(), mLen = magazine.length();
        for (int i = 0; i < rLen; i++) {
            characterIntegerMap.merge(ransomNote.charAt(i), 1, Integer::sum);
        }
        for (int i = 0; i < mLen && !characterIntegerMap.isEmpty(); i++) {
            char m = magazine.charAt(i);
            Integer count = characterIntegerMap.get(m);
            if (count != null) {
                if (--count == 0) { characterIntegerMap.remove(m); } else { characterIntegerMap.put(m, count); }
            }
        }
        return characterIntegerMap.isEmpty();
    }

    public boolean canConstruct3(String ransomNote, String magazine) {
        int[] counts = new int[26];
        for (int i = 0, n = ransomNote.length(); i < n; i++) {
            counts[ransomNote.charAt(i) - 'a']++;
        }
        int remain = 0;
        for (int count : counts) {
            if (count > 0) { remain++; }
        }
        for (int i = 0, n = magazine.length(); i < n; i++) {
            if (--counts[magazine.charAt(i) - 'a'] == 0) {
                remain--;
            }
            if (remain == 0) { return true;}
        }
        return false;
    }
}
