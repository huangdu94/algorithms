package work.huangdu.exploration.advanced_algorithms.other;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 42. 接雨水
 * 给定n个非负整数表示每个宽度为1的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * 上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。感谢 Marcos 贡献此图。
 * 示例:
 * 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出: 6
 *
 * @author huangdu
 * @version 2020/9/8 11:43
 */
public class Trap {
    public int trap(int[] height) {
        if (height.length == 0) { return 0; }
        int n = height.length, max = height[0], ans = 0;
        int[] leftMax = new int[n], rightMax = new int[n];
        for (int i = 1; i < n; i++) {
            leftMax[i] = max;
            max = Math.max(max, height[i]);
        }
        max = height[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = max;
            max = Math.max(max, height[i]);
        }
        for (int i = 1; i < n - 1; i++) {
            ans += Math.max(0, Math.min(leftMax[i], rightMax[i]) - height[i]);
        }
        return ans;
    }

    public int trap1(int[] height) {
        int volume = 0, leftHeight = 0;
        Deque<Integer> monoStack = new ArrayDeque<>();
        for (int i = 0; i < height.length; i++) {
            int minSide = Math.min(leftHeight, height[i]);
            while (!monoStack.isEmpty() && height[monoStack.peek()] <= height[i]) {
                int index = monoStack.pop();
                if (monoStack.isEmpty()) { break; }
                volume += (minSide - height[index]) * (index - monoStack.peek());
            }
            if (monoStack.isEmpty()) { leftHeight = height[i]; }
            monoStack.push(i);
        }
        return volume;
    }

    public int trap2(int[] height) {
        int volume = 0;
        Deque<Integer> monoStack = new ArrayDeque<>();
        for (int i = 0; i < height.length; i++) {
            while (!monoStack.isEmpty() && height[monoStack.peek()] <= height[i]) {
                int index = monoStack.pop();
                if (monoStack.isEmpty()) { break; }
                volume += (Math.min(height[monoStack.peek()], height[i]) - height[index]) * (i - monoStack.peek() - 1);
            }
            monoStack.push(i);
        }
        return volume;
    }

    public int trap3(int[] height) {
        int volume = 0, i = 0, j = height.length - 1, left = 0, right = 0;
        while (i < j) {
            if (height[i] <= height[j]) {
                if (left < height[i]) { left = height[i]; } else if (left > height[i]) { volume += (left - height[i]); }
                i++;
            } else {
                if (right < height[j]) { right = height[j]; } else if (right > height[j]) { volume += (right - height[j]); }
                j--;
            }
        }
        return volume;
    }

    public static void main(String[] args) {
        Trap trap = new Trap();
        int[] heights = {5, 4, 1, 2};
        System.out.println(trap.trap(heights));
    }

    public int trap4(int[] height) {
        int n = height.length, top = 0, result = 0;
        int[] stack = new int[n];
        for (int i = 0; i < n; i++) {
            int h = height[i], bound = Math.min(height[stack[0]], h);
            while (top != 0 && h >= height[stack[top - 1]]) {
                if (top > 1) {
                    result += (bound - height[stack[top - 1]]) * (stack[top - 1] - stack[top - 2]);
                }
                top--;
            }
            stack[top++] = i;
        }
        return result;
    }

    public int trap5(int[] height) {
        int ans = 0;
        Deque<int[]> stack = new ArrayDeque<>();
        for (int curI = 0, n = height.length; curI < n; curI++) {
            int curH = height[curI];
            while (!stack.isEmpty() && stack.peek()[0] <= curH) {
                int floor = stack.pop()[0];
                if (!stack.isEmpty()) {
                    ans += (Math.min(stack.peek()[0], curH) - floor) * (curI - stack.peek()[1] - 1);
                }
            }
            stack.push(new int[] {curH, curI});
        }
        return ans;
    }

    /**
     * 单调栈，栈中记录“柱子”对应的下标，柱子的高度取height数组中查询
     * 栈中存的是单调递减的
     */
    public int trap6(int[] height) {
        int n = height.length, ans = 0, top = 0;
        int[] stack = new int[n];
        for (int i = 0; i < n; i++) {
            int curH = height[i], waterH = Math.min(height[stack[0]], curH), ptr = i - 1;
            while (top > 0 && height[stack[top - 1]] <= curH) {
                if (top > 1) {
                    ans += (waterH - height[stack[top - 1]]) * (ptr - stack[top - 2]);
                    ptr = stack[top - 2];
                }
                top--;
            }
            stack[top++] = i;
        }
        return ans;
    }
}
