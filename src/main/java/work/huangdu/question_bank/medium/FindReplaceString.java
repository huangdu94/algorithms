package work.huangdu.question_bank.medium;

/**
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2023/8/15
 */
public class FindReplaceString {
    public String findReplaceString(String s, int[] indices, String[] sources, String[] targets) {
        StringBuilder ans = new StringBuilder(s);
        for (int i = 0, m = indices.length; i < m; i++) {
            String source = sources[i], target = targets[i];
            int indice = indices[i], n = source.length();
            boolean flag = true;
            for (int j = 0; j < n; j++) {
                if (indice + j >= ans.length() || ans.charAt(indice + j) != source.charAt(j)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                ans.replace(indice, indice + n, target);
                for (int j = i + 1; j < m; j++) {
                    if (indices[j] > indice) {
                        indices[j] += target.length() - n;
                    }
                }
            }
        }
        return ans.toString();
    }
}
