package work.huangdu.question_bank.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 1023. 驼峰式匹配
 * 如果我们可以将小写字母插入模式串 pattern 得到待查询项 query，那么待查询项与给定模式串匹配。（我们可以在任何位置插入每个字符，也可以插入 0 个字符。）
 * 给定待查询列表 queries，和模式串 pattern，返回由布尔值组成的答案列表 answer。只有在待查项 queries[i] 与模式串 pattern 匹配时， answer[i] 才为 true，否则为 false。
 * 示例 1：
 * 输入：queries = ["FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"], pattern = "FB"
 * 输出：[true,false,true,true,false]
 * 示例：
 * "FooBar" 可以这样生成："F" + "oo" + "B" + "ar"。
 * "FootBall" 可以这样生成："F" + "oot" + "B" + "all".
 * "FrameBuffer" 可以这样生成："F" + "rame" + "B" + "uffer".
 * 示例 2：
 * 输入：queries = ["FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"], pattern = "FoBa"
 * 输出：[true,false,true,false,false]
 * 解释：
 * "FooBar" 可以这样生成："Fo" + "o" + "Ba" + "r".
 * "FootBall" 可以这样生成："Fo" + "ot" + "Ba" + "ll".
 * 示例 3：
 * 输入：queries = ["FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"], pattern = "FoBaT"
 * 输出：[false,true,false,false,false]
 * 解释：
 * "FooBarTest" 可以这样生成："Fo" + "o" + "Ba" + "r" + "T" + "est".
 * 提示：
 * 1 <= queries.length <= 100
 * 1 <= queries[i].length <= 100
 * 1 <= pattern.length <= 100
 * 所有字符串都仅由大写和小写英文字母组成。
 *
 * @author huangdu
 * @version 2023/4/15
 */
public class CamelMatch {
    public List<Boolean> camelMatch(String[] queries, String pattern) {
        List<Boolean> ans = new ArrayList<>(queries.length);
        for (String query : queries) {
            ans.add(check(pattern, query));
        }
        return ans;
    }

    private boolean check(String pattern, String query) {
        int n = pattern.length(), m = query.length();
        if (n > m) {return false;}
        int j = 0;
        for (int i = 0; i < n; i++) {
            char target = pattern.charAt(i);
            if (Character.isUpperCase(target)) {
                while (j < m && Character.isLowerCase(query.charAt(j))) {j++;}
                if (j == m || query.charAt(j) != target) {return false;}
            } else {
                while (j < m && Character.isLowerCase(query.charAt(j)) && query.charAt(j) != target) {j++;}
                if (j == m || Character.isUpperCase(query.charAt(j))) {return false;}
            }
            j++;
        }
        while (j < m && Character.isLowerCase(query.charAt(j))) {j++;}
        return j == m;
    }

    public static void main(String[] args) {
        System.out.println(new CamelMatch().check("FB", "FooBar"));
    }
}
