package work.huangdu.exploration.start_from_scratch.graph_search.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * 93. 复原 IP 地址
 * 有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
 * 例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 和 "192.168@1.1" 是 无效 IP 地址。
 * 给定一个只包含数字的字符串 s ，用以表示一个 IP 地址，返回所有可能的有效 IP 地址，这些地址可以通过在 s 中插入 '.' 来形成。你 不能 重新排序或删除 s 中的任何数字。你可以按 任何 顺序返回答案。
 * 示例 1：
 * 输入：s = "25525511135"
 * 输出：["255.255.11.135","255.255.111.35"]
 * 示例 2：
 * 输入：s = "0000"
 * 输出：["0.0.0.0"]
 * 示例 3：
 * 输入：s = "101023"
 * 输出：["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
 * 提示：
 * 1 <= s.length <= 20
 * s 仅由数字组成
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2020/8/9 0:43
 */
public class RestoreIpAddresses {
    public List<String> restoreIpAddresses(String s) {
        int n = s.length();
        List<String> ans = new ArrayList<>();
        if (n <= 12) {backtrack(ans, s, 0, n, new ArrayList<>(3));}
        return ans;
    }

    private void backtrack(List<String> ans, String s, int index, int n, List<Integer> indexList) {
        if (indexList.size() == 3) {
            int lastIndex = indexList.get(2);
            if (lastIndex < n && n - lastIndex <= 3) {
                String last = s.substring(lastIndex);
                int lastNum = Integer.parseInt(last);
                if (last.equals("0") || !last.startsWith("0") && lastNum > 0 && lastNum < 256) {
                    StringBuilder sb = new StringBuilder(s);
                    sb.insert(indexList.get(0).intValue(), '.');
                    sb.insert(indexList.get(1) + 1, '.');
                    sb.insert(indexList.get(2) + 2, '.');
                    ans.add(sb.toString());
                }
            }
            return;
        }
        if (index == n) {return;}
        String cur = s.substring(indexList.isEmpty() ? 0 : indexList.get(indexList.size() - 1), index + 1);
        if (cur.length() > 1 && cur.startsWith("0") || Integer.parseInt(cur) >= 256) {return;}
        backtrack(ans, s, index + 1, n, indexList);
        indexList.add(index + 1);
        backtrack(ans, s, index + 1, n, indexList);
        indexList.remove(indexList.size() - 1);
    }

    public static void main(String[] args) {
        // ["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
        RestoreIpAddresses ria = new RestoreIpAddresses();
        System.out.println(ria.restoreIpAddresses("0279245587303"));
    }

    public List<String> restoreIpAddresses2(String s) {
        List<String> res = new ArrayList<>();
        //if (s == null) return res;
        int len = s.length();
        if (len > 12 || len < 4) return res;
        StringBuilder sb;
        for (int i = 1; i <= 3; i++) {
            if (i > 1 && s.charAt(0) == '0' || i == 3 && s.substring(0, i).compareTo("255") > 0) break;
            for (int j = i + 1; j <= i + 3; j++) {
                if (j > len) break;
                if (j > i + 1 && s.charAt(i) == '0' || j == i + 3 && s.substring(i, i + 3).compareTo("255") > 0) break;
                for (int k = j + 1; k <= j + 3; k++) {
                    if (k > len) break;
                    if (k > j + 1 && s.charAt(j) == '0' || k == j + 3 && s.substring(j, j + 3).compareTo("255") > 0)
                        break;
                    for (int l = k + 1; l <= k + 3; l++) {
                        if (l > len) break;
                        if (l < len) continue;
                        if (l > k + 1 && s.charAt(k) == '0' || l == k + 3 && s.substring(k, k + 3).compareTo("255") > 0)
                            break;
                        sb = new StringBuilder(s);
                        sb.insert(i, '.');
                        sb.insert(j + 1, '.');
                        sb.insert(k + 2, '.');
                        res.add(sb.toString());
                    }
                }
            }
        }
        return res;
    }
}
