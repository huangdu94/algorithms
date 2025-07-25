package work.huangdu.exploration.start_from_scratch.string.conversion;

import java.util.ArrayList;
import java.util.List;

/**
 * 68. 文本左右对齐
 * 给定一个单词数组和一个长度 maxWidth，重新排版单词，使其成为每行恰好有 maxWidth 个字符，且左右两端对齐的文本。
 * 你应该使用“贪心算法”来放置给定的单词；也就是说，尽可能多地往每行中放置单词。必要时可用空格 ' ' 填充，使得每行恰好有 maxWidth 个字符。
 * 要求尽可能均匀分配单词间的空格数量。如果某一行单词间的空格不能均匀分配，则左侧放置的空格数要多于右侧的空格数。
 * 文本的最后一行应为左对齐，且单词之间不插入额外的空格。
 * 说明:
 * 单词是指由非空格字符组成的字符序列。
 * 每个单词的长度大于 0，小于等于 maxWidth。
 * 输入单词数组 words 至少包含一个单词。
 * 示例:
 * 输入:
 * words = ["This", "is", "an", "example", "of", "text", "justification."]
 * maxWidth = 16
 * 输出:
 * [
 * "This    is    an",
 * "example  of text",
 * "justification.  "
 * ]
 * 示例 2:
 * 输入:
 * words = ["What","must","be","acknowledgment","shall","be"]
 * maxWidth = 16
 * 输出:
 * [
 * "What   must   be",
 * "acknowledgment  ",
 * "shall be        "
 * ]
 * 解释: 注意最后一行的格式应为 "shall be    " 而不是 "shall     be",
 * 因为最后一行应为左对齐，而不是左右两端对齐。
 * 第二行同样为左对齐，这是因为这行只包含一个单词。
 * 示例 3:
 * 输入:
 * words = ["Science","is","what","we","understand","well","enough","to","explain",
 * "to","a","computer.","Art","is","everything","else","we","do"]
 * maxWidth = 20
 * 输出:
 * [
 * "Science  is  what we",
 * "understand      well",
 * "enough to explain to",
 * "a  computer.  Art is",
 * "everything  else  we",
 * "do                  "
 * ]
 *
 * @author huangdu
 * @version 2021/9/9
 */
public class FullJustify {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new ArrayList<>();
        int n = words.length, i = 0;
        while (i < n) {
            int start = i, wordLen = words[i].length(), spaceCount = 0;
            // 核心部分，贪心算法求本行最多能容纳的单词数量
            while (i < n - 1 && wordLen + words[i + 1].length() + spaceCount < maxWidth) {
                wordLen += words[i + 1].length();
                spaceCount++;
                i++;
            }
            StringBuilder row = new StringBuilder(maxWidth);
            // 非最后一行，或者一行只有一个单词，当成最后一行处理
            if (i < n - 1 && spaceCount > 0) {
                int spaceLen = (maxWidth - wordLen) / spaceCount;
                int addOneCount = (maxWidth - wordLen) % spaceCount;
                StringBuilder space = new StringBuilder(spaceLen);
                for (int k = 0; k < spaceLen; k++) {
                    space.append(' ');
                }
                for (int remainCount = spaceCount; start <= i; start++, remainCount--) {
                    row.append(words[start]);
                    if (remainCount > 0) {
                        row.append(space);
                        if (addOneCount-- > 0) {
                            row.append(' ');
                        }
                    }
                }
            } else {
                for (; start <= i; start++) {
                    row.append(words[start]);
                    if (row.length() < maxWidth) {
                        row.append(' ');
                    }
                }
                while (row.length() < maxWidth) {
                    row.append(' ');
                }
            }
            res.add(row.toString());
            i++;
        }
        return res;
    }

    public List<String> fullJustify2(String[] words, int maxWidth) {
        List<String> ans = new ArrayList<>();
        int n = words.length, end = 0;
        while (end < n) {
            int start = end, width = 0, blankCount = 0;
            while (end < n && width + blankCount + words[end].length() <= maxWidth) {
                width += words[end++].length();
                blankCount++;
            }
            blankCount--;
            StringBuilder line = new StringBuilder();
            if (end < n) {
                int remain = maxWidth - width;
                if (blankCount == 0) {
                    line.append(words[start]);
                    for (int k = 0; k < remain; k++) {
                        line.append(' ');
                    }
                } else {
                    int minBlankWidth = remain / blankCount, bigBlankCount = remain % blankCount;
                    for (int i = start; i < end; i++) {
                        line.append(words[i]);
                        if (i < end - 1) {
                            for (int k = 0; k < minBlankWidth; k++) {
                                line.append(' ');
                            }
                            if (bigBlankCount-- > 0) {
                                line.append(' ');
                            }
                        }
                    }
                }
            } else {
                for (int i = start; i < end; i++) {
                    line.append(words[i]);
                    if (i < end - 1) {
                        line.append(' ');
                    }
                }
                int remain = maxWidth - width - blankCount;
                for (int k = 0; k < remain; k++) {
                    line.append(' ');
                }
            }
            ans.add(line.toString());
        }
        return ans;
    }

    public static void main(String[] args) {
        FullJustify fullJustify = new FullJustify();
        String[] words = {"ask", "not", "what", "your", "country", "can", "do", "for", "you", "ask", "what", "you", "can", "do", "for", "your", "country"};
        int maxWidth = 16;
        System.out.println(fullJustify.fullJustify(words, maxWidth));
    }

    private static final char BLANK = ' ';

    public List<String> fullJustify3(String[] words, int maxWidth) {
        List<String> ans = new ArrayList<>();
        int n = words.length, pos = 0, buffer = 0;
        for (int i = 0; ; i++) {
            String word = words[i];
            if (buffer + word.length() + i - pos > maxWidth) {
                ans.add(createRow(words, maxWidth, pos, i, buffer, false));
                pos = i;
                buffer = 0;
            }
            if (i == n - 1) {
                ans.add(createRow(words, maxWidth, pos, n, buffer, true));
                return ans;
            }
            buffer += word.length();
        }
    }

    private String createRow(String[] words, int maxWidth, int left, int right, int buffer, boolean last) {
        StringBuilder sb = new StringBuilder();
        if (last) {
            for (int i = left; i < right; i++) {
                sb.append(words[i]);
                if (i < right - 1) {
                    sb.append(BLANK);
                }
            }
            while (sb.length() < maxWidth) {
                sb.append(BLANK);
            }
            return sb.toString();
        }
        int count = right - left, blank = maxWidth - buffer;
        if (count == 1) {
            sb.append(words[left]);
            for (int k = 0; k < blank; k++) {
                sb.append(BLANK);
            }
            return sb.toString();
        }
        // a表示平均每个空格的长度，b表示有b个空格要多一个
        int a = blank / (count - 1), b = blank % (count - 1);
        for (int i = left; i < right; i++) {
            sb.append(words[i]);
            if (i < right - 1) {
                for (int k = 0; k < a; k++) {
                    sb.append(BLANK);
                }
                if (i - left < b) {
                    sb.append(BLANK);
                }
            }
        }
        return sb.toString();
    }
}
