package work.huangdu.temp;

import java.util.Arrays;

import work.huangdu.data_structure.TreeNode;

/**
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/3/12
 */
public class Solution {
    private int n;
    private int max;
    private int[][] ans;
    private boolean[] used;
    private int mid;
    // 一行的值
    private int standard;

    public int[][] sudoku(int n) {
        this.n = n;
        this.max = n * n;
        this.ans = new int[n][n];
        this.used = new boolean[max + 1];
        // 中间数必固定
        this.mid = (max + 1) / 2;
        ans[n / 2][n / 2] = mid;
        used[mid] = true;
        this.standard = n * (max + 1) / 2;
        backtrack(0);
        return ans;
    }

    public boolean backtrack(int idx) {
        // 跳过中间一个数，它是确定的
        if (idx == mid - 1) {
            idx++;
        }
        if (idx == max) {
            return true;
        }
        int row = idx / n, coj = idx % n;
        for (int num = 1; num <= max; num++) {
            if (!used[num] && check(row, coj, num)) {
                ans[row][coj] = num;
                used[num] = true;
                if (backtrack(idx + 1)) {
                    return true;
                }
                used[num] = false;
            }
        }
        return false;
    }

    public boolean check(int row, int coj, int num) {
        int sum;
        // 检查行
        if (coj == n - 1) {
            sum = num;
            for (int j = 0; j < n - 1; j++) {
                sum += ans[row][j];
            }
            if (sum != standard) {
                return false;
            }
        }
        // 检查列
        if (row == n - 1) {
            sum = num;
            for (int i = 0; i < n - 1; i++) {
                sum += ans[i][coj];
            }
            if (sum != standard) {
                return false;
            }
        }
        // 检查对角线 或 反对角线
        if (row == n - 1) {
            if (coj == n - 1) {
                sum = num;
                for (int i = 0; i < n - 1; i++) {
                    sum += ans[i][i];
                }
                return sum == standard;
            } else if (coj == 0) {
                sum = num;
                for (int i = 0; i < n - 1; i++) {
                    sum += ans[i][n - i - 1];
                }
                return sum == standard;
            }
        }
        return true;
    }

    private int min = Integer.MAX_VALUE;
    private int pre = -1;

    public int minDiffInBST(TreeNode root) {
        inorder(root);
        return min;
    }

    private void inorder(TreeNode root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        if (pre != -1) {
            min = Math.min(min, root.val - pre);
        }
        pre = root.val;
        inorder(root.right);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        //int[] nums = {4, 14, 2};
        long start = System.currentTimeMillis();
        //System.out.println(solution.numSquares(104));
        System.out.println(solution.triangleNumber(new int[] {2, 2, 3, 4}));
        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start) / 1000.0 + "秒.");
        System.out.println("Done!");
    }

    public boolean isPowerOfTwo(int n) {
        if (n <= 0) {return false;}
        return (n & n - 1) == 0;
    }

    public int findMaxLength(int[] nums) {
        return -1;
    }

    public int triangleNumber(int[] nums) {
        int n = nums.length;
        if (n < 3) {return 0;}
        Arrays.sort(nums);
        int count = 0;
        for (int i = 0; i < n; i++) {
            int a = nums[i];
            for (int j = i + 1; j < n; j++) {
                int ab = a + nums[j];
                for (int k = j + 1; k < n && ab > nums[k]; k++) {
                    count++;
                }
            }
        }
        return count;
    }
}

class Trie2 {
    private final TrieNode2 root = new TrieNode2();

    /**
     * Initialize your data structure here.
     */
    public Trie2() {

    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        int n = word.length();
        TrieNode2 cur = root;
        for (int i = 0; i < n; i++) {
            char c = word.charAt(i);
            if (cur.children[c - 'a'] == null) {
                cur.children[c - 'a'] = new TrieNode2();
            }
            cur = cur.children[c - 'a'];
        }
        cur.flag = true;
    }

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        int n = word.length();
        TrieNode2 cur = root;
        for (int i = 0; i < n; i++) {
            char c = word.charAt(i);
            if (cur.children[c - 'a'] == null) {
                return false;
            }
            cur = cur.children[c - 'a'];
        }
        return cur.flag;
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        int n = prefix.length();
        TrieNode2 cur = root;
        for (int i = 0; i < n; i++) {
            char c = prefix.charAt(i);
            if (cur.children[c - 'a'] == null) {
                return false;
            }
            cur = cur.children[c - 'a'];
        }
        return true;
    }

    static class TrieNode2 {
        boolean flag;
        TrieNode2[] children = new TrieNode2[26];
    }
}
