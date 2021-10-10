package work.huangdu.question_bank.easy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 1436. 旅行终点站
 * 给你一份旅游线路图，该线路图中的旅行线路用数组 paths 表示，其中 paths[i] = [cityAi, cityBi] 表示该线路将会从 cityAi 直接前往 cityBi 。请你找出这次旅行的终点站，即没有任何可以通往其他城市的线路的城市。
 * 题目数据保证线路图会形成一条不存在循环的线路，因此恰有一个旅行终点站。
 * 示例 1：
 * 输入：paths = [["London","New York"],["New York","Lima"],["Lima","Sao Paulo"]]
 * 输出："Sao Paulo"
 * 解释：从 "London" 出发，最后抵达终点站 "Sao Paulo" 。本次旅行的路线是 "London" -> "New York" -> "Lima" -> "Sao Paulo" 。
 * 示例 2：
 * 输入：paths = [["B","C"],["D","B"],["C","A"]]
 * 输出："A"
 * 解释：所有可能的线路是：
 * "D" -> "B" -> "C" -> "A".
 * "B" -> "C" -> "A".
 * "C" -> "A".
 * "A".
 * 显然，旅行终点站是 "A" 。
 * 示例 3：
 * 输入：paths = [["A","Z"]]
 * 输出："Z"
 * 提示：
 * 1 <= paths.length <= 100
 * paths[i].length == 2
 * 1 <= cityAi.length, cityBi.length <= 10
 * cityAi != cityBi
 * 所有字符串均由大小写英文字母和空格字符组成。
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/10/1
 */
public class DestCity {
    public String destCity(List<List<String>> paths) {
        int n = paths.size();
        Set<String> source = new HashSet<>(n), target = new HashSet<>(n);
        for (List<String> path : paths) {
            source.add(path.get(0));
            target.add(path.get(1));
        }
        target.removeAll(source);
        return new ArrayList<>(target).get(0);
    }
}
