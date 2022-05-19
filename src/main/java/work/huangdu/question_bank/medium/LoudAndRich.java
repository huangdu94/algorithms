package work.huangdu.question_bank.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 851. 喧闹和富有
 * 有一组 n 个人作为实验对象，从 0 到 n - 1 编号，其中每个人都有不同数目的钱，以及不同程度的安静值（quietness）。为了方便起见，我们将编号为 x 的人简称为 "person x "。
 * 给你一个数组 richer ，其中 richer[i] = [ai, bi] 表示 person ai 比 person bi 更有钱。另给你一个整数数组 quiet ，其中 quiet[i] 是 person i 的安静值。richer 中所给出的数据 逻辑自恰（也就是说，在 person x 比 person y 更有钱的同时，不会出现 person y 比 person x 更有钱的情况 ）。
 * 现在，返回一个整数数组 answer 作为答案，其中 answer[x] = y 的前提是，在所有拥有的钱肯定不少于 person x 的人中，person y 是最安静的人（也就是安静值 quiet[y] 最小的人）。
 * 示例 1：
 * 输入：richer = [[1,0],[2,1],[3,1],[3,7],[4,3],[5,3],[6,3]], quiet = [3,2,5,4,6,1,7,0]
 * 输出：[5,5,2,5,4,5,6,7]
 * 解释：
 * answer[0] = 5，
 * person 5 比 person 3 有更多的钱，person 3 比 person 1 有更多的钱，person 1 比 person 0 有更多的钱。
 * 唯一较为安静（有较低的安静值 quiet[x]）的人是 person 7，
 * 但是目前还不清楚他是否比 person 0 更有钱。
 * answer[7] = 7，
 * 在所有拥有的钱肯定不少于 person 7 的人中（这可能包括 person 3，4，5，6 以及 7），
 * 最安静（有较低安静值 quiet[x]）的人是 person 7。
 * 其他的答案也可以用类似的推理来解释。
 * 示例 2：
 * 输入：richer = [], quiet = [0]
 * 输出：[0]
 * 提示：
 * n == quiet.length
 * 1 <= n <= 500
 * 0 <= quiet[i] < n
 * quiet 的所有值 互不相同
 * 0 <= richer.length <= n * (n - 1) / 2
 * 0 <= ai, bi < n
 * ai != bi
 * richer 中的所有数对 互不相同
 * 对 richer 的观察在逻辑上是一致的
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/12/15
 */
public class LoudAndRich {
    public int[] loudAndRich(int[][] richer, int[] quiet) {
        int n = quiet.length;
        Node[] nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            Node node = new Node();
            node.richerIdSet.add(i);
            nodes[i] = node;
        }
        for (int[] compare : richer) {
            int richId = compare[0], poorId = compare[1];
            nodes[poorId].richerNodeList.add(nodes[richId]);
        }
        for (Node node : nodes) { dfs(node); }
        int[][] quietIndexArray = new int[n][];
        for (int i = 0; i < n; i++) {
            quietIndexArray[i] = new int[] {quiet[i], i};
        }
        Arrays.sort(quietIndexArray, Comparator.comparingInt(o -> o[0]));
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            Set<Integer> richerSet = nodes[i].richerIdSet;
            for (int k = 0; k < n; k++) {
                int id = quietIndexArray[k][1];
                if (richerSet.contains(id)) {
                    result[i] = id;
                    break;
                }
            }
        }
        return result;
    }

    private static class Node {
        private final List<Node> richerNodeList;
        private final Set<Integer> richerIdSet;

        public Node() {
            this.richerNodeList = new ArrayList<>();
            this.richerIdSet = new HashSet<>();
        }
    }

    private void dfs(Node node) {
        if (node == null) {return;}
        if (node.richerIdSet.size() > 1) { return;}
        for (Node richerNode : node.richerNodeList) {
            dfs(richerNode);
            node.richerIdSet.addAll(richerNode.richerIdSet);
        }
    }

    public static void main(String[] args) {
        LoudAndRich lar = new LoudAndRich();
        // [0,0,0]
        System.out.println(Arrays.toString(lar.loudAndRich(new int[][] {{0, 1}, {1, 2}}, new int[] {0, 1, 2})));
    }

    // TODO 使用图的知识解这一题
}