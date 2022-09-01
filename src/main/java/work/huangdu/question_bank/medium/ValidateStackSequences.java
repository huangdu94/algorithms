package work.huangdu.question_bank.medium;

/**
 * 946. 验证栈序列
 * 给定 pushed 和 popped 两个序列，每个序列中的 值都不重复，只有当它们可能是在最初空栈上进行的推入 push 和弹出 pop 操作序列的结果时，返回 true；否则，返回 false 。
 * 示例 1：
 * 输入：pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
 * 输出：true
 * 解释：我们可以按以下顺序执行：
 * push(1), push(2), push(3), push(4), pop() -> 4,
 * push(5), pop() -> 5, pop() -> 3, pop() -> 2, pop() -> 1
 * 示例 2：
 * 输入：pushed = [1,2,3,4,5], popped = [4,3,5,1,2]
 * 输出：false
 * 解释：1 不能在 2 之前弹出。
 * 提示：
 * 1 <= pushed.length <= 1000
 * 0 <= pushed[i] <= 1000
 * pushed 的所有元素 互不相同
 * popped.length == pushed.length
 * popped 是 pushed 的一个排列
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2022/8/31
 */
public class ValidateStackSequences {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        int n = pushed.length, top = 0, push_idx = 0, pop_idx = 0;
        int[] stack = new int[n];
        while (pop_idx < n) {
            while (push_idx < n && pushed[push_idx] != popped[pop_idx]) {
                stack[top++] = pushed[push_idx++];
            }
            if (push_idx == n) {return false;}
            push_idx++;
            pop_idx++;
            while (pop_idx < n && top > 0 && popped[pop_idx] == stack[top - 1]) {
                pop_idx++;
                top--;
            }
        }
        return push_idx == n;
    }

    public static void main(String[] args) {
        ValidateStackSequences vss = new ValidateStackSequences();
        int[] pushed = {1, 2, 3, 4, 5};
        int[] popped = {4, 5, 3, 2, 1};
        System.out.println(vss.validateStackSequences(pushed, popped));
    }
}