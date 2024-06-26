package work.huangdu.question_bank.medium;

/**
 * 1105. 填充书架
 * 给定一个数组 books ，其中 books[i] = [thickness_i, height_i] 表示第 i 本书的厚度和高度。你也会得到一个整数 shelfWidth 。
 * 按顺序 将这些书摆放到总宽度为 shelfWidth 的书架上。
 * 先选几本书放在书架上（它们的厚度之和小于等于书架的宽度 shelfWidth ），然后再建一层书架。重复这个过程，直到把所有的书都放在书架上。
 * 需要注意的是，在上述过程的每个步骤中，摆放书的顺序与你整理好的顺序相同。
 * 例如，如果这里有 5 本书，那么可能的一种摆放情况是：第一和第二本书放在第一层书架上，第三本书放在第二层书架上，第四和第五本书放在最后一层书架上。
 * 每一层所摆放的书的最大高度就是这一层书架的层高，书架整体的高度为各层高之和。
 * 以这种方式布置书架，返回书架整体可能的最小高度。
 * 示例 1：
 * 输入：books = [[1,1],[2,3],[2,3],[1,1],[1,1],[1,1],[1,2]], shelfWidth = 4
 * 输出：6
 * 解释：
 * 3 层书架的高度和为 1 + 3 + 2 = 6 。
 * 第 2 本书不必放在第一层书架上。
 * 示例 2:
 * 输入: books = [[1,3],[2,4],[3,2]], shelfWidth = 6
 * 输出: 4
 * 提示：
 * 1 <= books.length <= 1000
 * 1 <= thickness_i <= shelfWidth <= 1000
 * 1 <= height_i <= 1000
 *
 * @author huangdu
 * @version 2023/4/23
 */
public class MinHeightShelves {
    static class Solution {
        private int[][] books;
        private int shelfWidth;
        private int n;
        private int[] memo;

        public int minHeightShelves(int[][] books, int shelfWidth) {
            this.books = books;
            this.shelfWidth = shelfWidth;
            this.n = books.length;
            this.memo = new int[n];
            return dfs(0);
        }

        private int dfs(int idx) {
            if (idx == n) {return 0;}
            if (memo[idx] != 0) {return memo[idx];}
            int width = 0, height = 0, ans = Integer.MAX_VALUE, i = idx;
            while (i < n) {
                width += books[i][0];
                height = Math.max(height, books[i][1]);
                if (width > shelfWidth) {break;}
                ans = Math.min(ans, height + dfs(++i));
            }
            return memo[idx] = ans;
        }

        public static void main(String[] args) {
            MinHeightShelves.Solution mhs = new MinHeightShelves.Solution();
            int[][] books = {{1, 1}, {2, 3}, {2, 3}, {1, 1}, {1, 1}, {1, 1}, {1, 2}};
            int shelfWidth = 4;
            System.out.println(mhs.minHeightShelves(books, shelfWidth));
        }
    }

    public int minHeightShelves(int[][] books, int shelfWidth) {
        int n = books.length;
        int[] f = new int[n + 1];
        f[n] = 0;
        for (int i = n - 1; i >= 0; i--) {
            int width = 0, height = 0, ans = Integer.MAX_VALUE, j = i;
            while (j < n) {
                width += books[j][0];
                height = Math.max(height, books[j][1]);
                if (width > shelfWidth) {break;}
                ans = Math.min(ans, height + f[++j]);
            }
            f[i] = ans;
        }
        return f[0];
    }
}
