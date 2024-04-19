package work.huangdu.question_bank.difficult;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 715. Range 模块
 * Range模块是跟踪数字范围的模块。设计一个数据结构来跟踪表示为 半开区间 的范围并查询它们。
 * 半开区间 [left, right) 表示所有 left <= x < right 的实数 x 。
 * 实现 RangeModule 类:
 * RangeModule() 初始化数据结构的对象。
 * void addRange(int left, int right) 添加 半开区间 [left, right)，跟踪该区间中的每个实数。添加与当前跟踪的数字部分重叠的区间时，应当添加在区间 [left, right) 中尚未跟踪的任何数字到该区间中。
 * boolean queryRange(int left, int right) 只有在当前正在跟踪区间 [left, right) 中的每一个实数时，才返回 true ，否则返回 false 。
 * void removeRange(int left, int right) 停止跟踪 半开区间 [left, right) 中当前正在跟踪的每个实数。
 * 示例 1：
 * 输入
 * ["RangeModule", "addRange", "removeRange", "queryRange", "queryRange", "queryRange"]
 * [[], [10, 20], [14, 16], [10, 14], [13, 15], [16, 17]]
 * 输出
 * [null, null, null, true, false, true]
 * 解释
 * RangeModule rangeModule = new RangeModule();
 * rangeModule.addRange(10, 20);
 * rangeModule.removeRange(14, 16);
 * rangeModule.queryRange(10, 14); 返回 true （区间 [10, 14) 中的每个数都正在被跟踪）
 * rangeModule.queryRange(13, 15); 返回 false（未跟踪区间 [13, 15) 中像 14, 14.03, 14.17 这样的数字）
 * rangeModule.queryRange(16, 17); 返回 true （尽管执行了删除操作，区间 [16, 17) 中的数字 16 仍然会被跟踪）
 * 提示：
 * 1 <= left < right <= 10^9
 * 在单个测试用例中，对 addRange 、  queryRange 和 removeRange 的调用总数不超过 10^4 次
 *
 * @author huangdu
 * @version 2022/6/24
 */
public class RangeModule {
    private final Map<Integer, Boolean> tree;
    private final Map<Integer, Boolean> lazy;

    public RangeModule() {
        this.tree = new HashMap<>();
        this.lazy = new HashMap<>();
    }

    public void addRange(int left, int right) {
        update(0, 1000000000, left, right - 1, 1, true);
    }

    public boolean queryRange(int left, int right) {
        return query(0, 1000000000, left, right - 1, 1);
    }

    public void removeRange(int left, int right) {
        update(0, 1000000000, left, right - 1, 1, false);
    }

    private void update(int l, int r, int start, int end, int idx, boolean val) {
        if (start <= l && r <= end) {
            tree.put(idx, val);
            lazy.put(idx, val);
            return;
        }
        int mid = l + ((r - l) >> 1), leftIdx = idx << 1, rightIdx = (idx << 1) + 1;
        Boolean lazyVal = lazy.put(idx, null);
        if (Objects.nonNull(lazyVal)) {
            tree.put(leftIdx, lazyVal);
            lazy.put(leftIdx, lazyVal);
            tree.put(rightIdx, lazyVal);
            lazy.put(rightIdx, lazyVal);
        }
        if (start <= mid) {
            update(l, mid, start, end, leftIdx, val);
        }
        if (mid < end) {
            update(mid + 1, r, start, end, rightIdx, val);
        }
        tree.put(idx, tree.getOrDefault(leftIdx, false) && tree.getOrDefault(rightIdx, false));
    }

    private boolean query(int l, int r, int start, int end, int idx) {
        if (start <= l && r <= end) {return tree.getOrDefault(idx, false);}
        int mid = l + ((r - l) >> 1), leftIdx = idx << 1, rightIdx = (idx << 1) + 1;
        Boolean lazyVal = lazy.put(idx, null);
        if (Objects.nonNull(lazyVal)) {
            tree.put(leftIdx, lazyVal);
            lazy.put(leftIdx, lazyVal);
            tree.put(rightIdx, lazyVal);
            lazy.put(rightIdx, lazyVal);
        }
        boolean result = true;
        if (start <= mid) {
            result = query(l, mid, start, end, leftIdx);
        }
        if (mid < end) {
            result &= query(mid + 1, r, start, end, rightIdx);
        }
        return result;
    }

    static class RangeModule2 {
        private final Node root;

        public RangeModule2() {
            this.root = new Node();
        }

        public void addRange(int left, int right) {
            update(this.root, 0, 1000000000, left, right - 1, true);
        }

        public boolean queryRange(int left, int right) {
            return query(this.root, 0, 1000000000, left, right - 1);
        }

        public void removeRange(int left, int right) {
            update(this.root, 0, 1000000000, left, right - 1, false);
        }

        private void update(Node node, int left, int right, int start, int end, boolean val) {
            if (start <= left && right <= end) {
                node.val = val;
                node.lazy = val ? 1 : -1;
                return;
            }
            pushDown(node);
            int mid = left + (right - left >> 1);
            if (start <= mid) {
                update(node.left, left, mid, start, end, val);
            }
            if (mid < end) {
                update(node.right, mid + 1, right, start, end, val);
            }
            pushUp(node);
        }

        private boolean query(Node node, int left, int right, int start, int end) {
            if (start <= left && right <= end) {
                return node.val;
            }
            pushDown(node);
            int mid = left + (right - left >> 1);
            boolean result = true;
            if (start <= mid) {
                result = query(node.left, left, mid, start, end);
            }
            if (mid < end) {
                result &= query(node.right, mid + 1, right, start, end);
            }
            return result;
        }

        private void pushDown(Node node) {
            if (node.left == null) {
                node.left = new Node();
            }
            if (node.right == null) {
                node.right = new Node();
            }
            if (node.lazy != 0) {
                node.left.val = node.lazy == 1;
                node.left.lazy = node.lazy;
                node.right.val = node.lazy == 1;
                node.right.lazy = node.lazy;
                node.lazy = 0;
            }
        }

        private void pushUp(Node node) {
            node.val = node.left.val && node.right.val;
        }

        static class Node {
            Node left;
            Node right;
            boolean val;
            int lazy;
        }

        public static void main(String[] args) {
            RangeModule2 rm = new RangeModule2();
            rm.addRange(5, 7);
            System.out.println(rm.queryRange(2, 7));
            rm.addRange(6, 9);
            System.out.println(rm.queryRange(2, 9));
            rm.addRange(2, 7);
            rm.removeRange(3, 10);
            rm.removeRange(1, 8);
            rm.removeRange(1, 10);
            System.out.println(rm.queryRange(4, 7));
        }
    }
}
