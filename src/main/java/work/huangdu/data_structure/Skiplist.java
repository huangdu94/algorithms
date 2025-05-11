package work.huangdu.data_structure;

import java.util.Arrays;
import java.util.Random;

/**
 * 跳表
 * <a href="https://15721.courses.cs.cmu.edu/spring2018/papers/08-oltpindexes1/pugh-skiplists-cacm1990.pdf"/>
 * 跳表是一种随机化的数据结构，可以被看做二叉树的一个变种，它在性能上和红黑树、AVL树不相上下，但是跳表的原理非常简单，目前在Redis和LevelDB中都有用到。
 * 跳表的期望空间复杂度为O(n)，跳表的查询，插入和删除操作的期望时间复杂度均为O(log n)。
 * 跳表实际为一种多层的有序链表，跳表的每一层都为一个有序链表，且满足每个位于第i层的节点有p的概率出现在第i+1层，其中p为常数。
 *
 * @author huangdu
 * @version 2022/9/7
 */
public class Skiplist {
    static final int MAX_LEVEL = 32;
    static final double P_FACTOR = 0.25;
    private final SkiplistNode head;
    private int level;
    private final Random random;

    public Skiplist() {
        this.head = new SkiplistNode(-1, MAX_LEVEL);
        this.level = 0;
        this.random = new Random();
    }

    public boolean search(int target) {
        SkiplistNode curr = this.head;
        for (int i = level - 1; i >= 0; i--) {
            /* 找到第 i 层小于且最接近 target 的元素*/
            while (curr.forward[i] != null && curr.forward[i].val < target) {
                curr = curr.forward[i];
            }
        }
        curr = curr.forward[0];
        /* 检测当前元素的值是否等于 target */
        if (curr != null && curr.val == target) {
            return true;
        }
        return false;
    }

    public void add(int num) {
        SkiplistNode[] update = new SkiplistNode[MAX_LEVEL];
        Arrays.fill(update, head);
        SkiplistNode curr = this.head;
        for (int i = level - 1; i >= 0; i--) {
            /* 找到第 i 层小于且最接近 num 的元素*/
            while (curr.forward[i] != null && curr.forward[i].val < num) {
                curr = curr.forward[i];
            }
            update[i] = curr;
        }
        int lv = randomLevel();
        level = Math.max(level, lv);
        SkiplistNode newNode = new SkiplistNode(num, lv);
        for (int i = 0; i < lv; i++) {
            /* 对第 i 层的状态进行更新，将当前元素的 forward 指向新的节点 */
            newNode.forward[i] = update[i].forward[i];
            update[i].forward[i] = newNode;
        }
    }

    public boolean erase(int num) {
        SkiplistNode[] update = new SkiplistNode[MAX_LEVEL];
        SkiplistNode curr = this.head;
        for (int i = level - 1; i >= 0; i--) {
            /* 找到第 i 层小于且最接近 num 的元素*/
            while (curr.forward[i] != null && curr.forward[i].val < num) {
                curr = curr.forward[i];
            }
            update[i] = curr;
        }
        curr = curr.forward[0];
        /* 如果值不存在则返回 false */
        if (curr == null || curr.val != num) {
            return false;
        }
        for (int i = 0; i < level; i++) {
            if (update[i].forward[i] != curr) {
                break;
            }
            /* 对第 i 层的状态进行更新，将 forward 指向被删除节点的下一跳 */
            update[i].forward[i] = curr.forward[i];
        }
        /* 更新当前的 level */
        while (level > 1 && head.forward[level - 1] == null) {
            level--;
        }
        return true;
    }

    private int randomLevel() {
        int lv = 1;
        /* 随机生成 lv */
        while (random.nextDouble() < P_FACTOR && lv < MAX_LEVEL) {
            lv++;
        }
        return lv;
    }

    private static class SkiplistNode {
        int val;
        SkiplistNode[] forward;

        public SkiplistNode(int val, int maxLevel) {
            this.val = val;
            this.forward = new SkiplistNode[maxLevel];
        }
    }
}
