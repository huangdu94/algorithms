package work.huangdu.question_bank.easy;

import java.util.HashSet;
import java.util.Set;

/**
 * 824. 山羊拉丁文
 * 给你一个由若干单词组成的句子 sentence ，单词间由空格分隔。每个单词仅由大写和小写英文字母组成。
 * 请你将句子转换为 “山羊拉丁文（Goat Latin）”（一种类似于 猪拉丁文 - Pig Latin 的虚构语言）。山羊拉丁文的规则如下：
 * 如果单词以元音开头（'a', 'e', 'i', 'o', 'u'），在单词后添加"ma"。
 * 例如，单词 "apple" 变为 "applema" 。
 * 如果单词以辅音字母开头（即，非元音字母），移除第一个字符并将它放到末尾，之后再添加"ma"。
 * 例如，单词 "goat" 变为 "oatgma" 。
 * 根据单词在句子中的索引，在单词最后添加与索引相同数量的字母'a'，索引从 1 开始。
 * 例如，在第一个单词后添加 "a" ，在第二个单词后添加 "aa" ，以此类推。
 * 返回将 sentence 转换为山羊拉丁文后的句子。
 * 示例 1：
 * 输入：sentence = "I speak Goat Latin"
 * 输出："Imaa peaksmaaa oatGmaaaa atinLmaaaaa"
 * 示例 2：
 * 输入：sentence = "The quick brown fox jumped over the lazy dog"
 * 输出："heTmaa uickqmaaa rownbmaaaa oxfmaaaaa umpedjmaaaaaa overmaaaaaaa hetmaaaaaaaa azylmaaaaaaaaa ogdmaaaaaaaaaa"
 * 提示：
 * 1 <= sentence.length <= 150
 * sentence 由英文字母和空格组成
 * sentence 不含前导或尾随空格
 * sentence 中的所有单词由单个空格分隔
 *
 * @author huangdu
 * @version 2022/4/21
 */
public class ToGoatLatin {
    private static final Set<Character> VOWEL_SET = new HashSet<Character>(6) {
        {
            add('a');
            add('e');
            add('i');
            add('o');
            add('u');
            add('A');
            add('E');
            add('I');
            add('O');
            add('U');
        }
    };

    public String toGoatLatin(String sentence) {
        StringBuilder ans = new StringBuilder();
        String[] words = sentence.split(" ");
        int index = 1;
        for (String word : words) {
            char firstCh = word.charAt(0);
            if (VOWEL_SET.contains(firstCh)) {
                ans.append(word);
            } else {
                ans.append(word.substring(1)).append(firstCh);
            }
            ans.append("ma");
            for (int i = 0; i < index; i++) {
                ans.append('a');
            }
            index++;
            ans.append(' ');
        }
        return ans.substring(0, ans.length() - 1);
    }
}
