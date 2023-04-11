package work.huangdu.question_bank.difficult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * 1125. 最小的必要团队
 * 作为项目经理，你规划了一份需求的技能清单 req_skills，并打算从备选人员名单 people 中选出些人组成一个「必要团队」（ 编号为 i 的备选人员 people[i] 含有一份该备选人员掌握的技能列表）。
 * 所谓「必要团队」，就是在这个团队中，对于所需求的技能列表 req_skills 中列出的每项技能，团队中至少有一名成员已经掌握。可以用每个人的编号来表示团队中的成员：
 * 例如，团队 team = [0, 1, 3] 表示掌握技能分别为 people[0]，people[1]，和 people[3] 的备选人员。
 * 请你返回 任一 规模最小的必要团队，团队成员用人员编号表示。你可以按 任意顺序 返回答案，题目数据保证答案存在。
 * 示例 1：
 * 输入：req_skills = ["java","nodejs","reactjs"], people = [["java"],["nodejs"],["nodejs","reactjs"]]
 * 输出：[0,2]
 * 示例 2：
 * 输入：req_skills = ["algorithms","math","java","reactjs","csharp","aws"], people = [["algorithms","math","java"],["algorithms","math","reactjs"],["java","csharp","aws"],["reactjs","csharp"],["csharp","math"],["aws","java"]]
 * 输出：[1,2]
 * 提示：
 * 1 <= req_skills.length <= 16
 * 1 <= req_skills[i].length <= 16
 * req_skills[i] 由小写英文字母组成
 * req_skills 中的所有字符串 互不相同
 * 1 <= people.length <= 60
 * 0 <= people[i].length <= 16
 * 1 <= people[i][j].length <= 16
 * people[i][j] 由小写英文字母组成
 * people[i] 中的所有字符串 互不相同
 * people[i] 中的每个技能是 req_skills 中的技能
 * 题目数据保证「必要团队」一定存在
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2023/4/10
 */
public class SmallestSufficientTeam {
    private int n;
    private String[] req_skills;
    private List<List<String>> people;
    private Map<String, List<Integer>> skillPeopleMap;
    private Set<Integer> cache;

    public int[] smallestSufficientTeam(String[] req_skills, List<List<String>> people) {
        this.n = req_skills.length;
        this.req_skills = req_skills;
        this.people = people;
        this.skillPeopleMap = new HashMap<>(n);
        for (String req_skill : req_skills) {
            skillPeopleMap.put(req_skill, new ArrayList<>());
        }
        for (int i = 0, size = people.size(); i < size; i++) {
            List<String> skills = people.get(i);
            for (String skill : skills) {
                skillPeopleMap.get(skill).add(i);
            }
        }
        backtrack(new HashSet<>(), new HashSet<>(), 0);
        return generateAns();
    }

    private void backtrack(Set<Integer> selectedPerson, Set<String> selectedSkill, int i) {
        if (i == n) {
            if (selectedSkill.size() == n) {
                cache = new HashSet<>(selectedPerson);
            }
            return;
        }
        if (Objects.nonNull(cache) && selectedPerson.size() >= cache.size()) {return;}
        String skill = req_skills[i];
        if (selectedSkill.contains(skill)) {
            backtrack(selectedPerson, selectedSkill, i + 1);
            return;
        }
        List<Integer> personList = skillPeopleMap.get(skill);
        for (int person : personList) {
            if (selectedPerson.contains(person)) {continue;}
            selectedPerson.add(person);
            List<String> skillList = people.get(person), addList = new ArrayList<>();
            for (String s : skillList) {
                if (selectedSkill.add(s)) {
                    addList.add(s);
                }
            }
            if (addList.isEmpty()) {continue;}
            backtrack(selectedPerson, selectedSkill, i + 1);
            selectedPerson.remove(person);
            addList.forEach(selectedSkill::remove);
        }
    }

    private int[] generateAns() {
        int[] ans = new int[cache.size()];
        int i = 0;
        for (int index : cache) {ans[i++] = index;}
        return ans;
    }

    public static void main(String[] args) {
        String[] req_skills = {"algorithms", "math", "java", "reactjs", "csharp", "aws"};
        List<List<String>> people = Arrays.asList(Arrays.asList("algorithms", "math", "java"), Arrays.asList("algorithms", "math", "reactjs"), Arrays.asList("java", "csharp", "aws"), Arrays.asList("reactjs", "csharp"),
            Arrays.asList("csharp", "math"), Arrays.asList("aws", "java"));
        SmallestSufficientTeam sst = new SmallestSufficientTeam();
        System.out.println(Arrays.toString(sst.smallestSufficientTeam(req_skills, people)));
    }

    static class Solution {
        private int n;
        private int allSkill;
        private int allPeople;
        private int[] mask;
        private long[][] memo;

        public int[] smallestSufficientTeam(String[] req_skills, List<List<String>> people) {
            int m = req_skills.length;
            this.n = people.size();
            this.allSkill = (1 << m) - 1;
            this.allPeople = (1 << n) - 1;
            Map<String, Integer> map = new HashMap<>(m);
            for (int i = 0; i < m; i++) {
                map.put(req_skills[i], i);
            }
            this.mask = new int[n];
            for (int i = 0; i < n; i++) {
                for (String skill : people.get(i)) {
                    mask[i] |= 1 << map.get(skill);
                }
            }
            this.memo = new long[n][1 << m];
            long selected = dfs(0, 0);
            int len = Long.bitCount(selected);
            int[] ans = new int[len];
            for (int i = 0, idx = 0; i < 64 && idx < len; i++) {
                if ((selected & (1L << i)) != 0) {
                    ans[idx++] = i;
                }
            }
            return ans;
        }

        private long dfs(int i, int j) {
            if (j == allSkill) {return 0;}
            if (i == n) {return allPeople;}
            if (memo[i][j] != 0) {return memo[i][j];}
            long result1 = dfs(i + 1, j);
            long result2 = dfs(i + 1, j | mask[i]) | (1L << i);
            memo[i][j] = Long.bitCount(result1) < Long.bitCount(result2) ? result1 : result2;
            return memo[i][j];
        }

        public static void main(String[] args) {
            String[] req_skills = {"java", "nodejs", "reactjs"};
            List<List<String>> people = Arrays.asList(Arrays.asList("java"), Arrays.asList("nodejs"), Arrays.asList("nodejs", "reactjs"));
            SmallestSufficientTeam.Solution solution = new SmallestSufficientTeam.Solution();
            System.out.println(Arrays.toString(solution.smallestSufficientTeam(req_skills, people)));
        }
    }
}
