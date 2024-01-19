package work.huangdu.question_bank.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 */
public class SplitWordsBySeparator {
    public List<String> splitWordsBySeparator(List<String> words, char separator) {
        List<String> ans = new ArrayList<>();
        for (String word : words) {
            int n = word.length(), pre = 0;
            for (int i = 0; i <= n; i++) {
                if (i == n || word.charAt(i) == separator) {
                    int len = i - pre;
                    if (len > 0) {
                        ans.add(word.substring(pre, i));
                    }
                    pre = i + 1;
                }
            }
        }
        return ans;
    }
}
