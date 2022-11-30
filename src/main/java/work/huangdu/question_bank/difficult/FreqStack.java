package work.huangdu.question_bank.difficult;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 895. 最大频率栈
 * 设计一个类似堆栈的数据结构，将元素推入堆栈，并从堆栈中弹出出现频率最高的元素。
 * 实现 FreqStack 类:
 * FreqStack() 构造一个空的堆栈。
 * void push(int val) 将一个整数 val 压入栈顶。
 * int pop() 删除并返回堆栈中出现频率最高的元素。
 * 如果出现频率最高的元素不只一个，则移除并返回最接近栈顶的元素。
 * 示例 1：
 * 输入：
 * ["FreqStack","push","push","push","push","push","push","pop","pop","pop","pop"],
 * [[],[5],[7],[5],[7],[4],[5],[],[],[],[]]
 * 输出：[null,null,null,null,null,null,null,5,7,5,4]
 * 解释：
 * FreqStack = new FreqStack();
 * freqStack.push (5);//堆栈为 [5]
 * freqStack.push (7);//堆栈是 [5,7]
 * freqStack.push (5);//堆栈是 [5,7,5]
 * freqStack.push (7);//堆栈是 [5,7,5,7]
 * freqStack.push (4);//堆栈是 [5,7,5,7,4]
 * freqStack.push (5);//堆栈是 [5,7,5,7,4,5]
 * freqStack.pop ();//返回 5 ，因为 5 出现频率最高。堆栈变成 [5,7,5,7,4]。
 * freqStack.pop ();//返回 7 ，因为 5 和 7 出现频率最高，但7最接近顶部。堆栈变成 [5,7,5,4]。
 * freqStack.pop ();//返回 5 ，因为 5 出现频率最高。堆栈变成 [5,7,4]。
 * freqStack.pop ();//返回 4 ，因为 4, 5 和 7 出现频率最高，但 4 是最接近顶部的。堆栈变成 [5,7]。
 * 提示：
 * 0 <= val <= 10^9
 * push 和 pop 的操作数不大于 2 * 10^4。
 * 输入保证在调用 pop 之前堆栈中至少有一个元素。
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2022/11/30
 */
public class FreqStack {
    private final Map<Integer, Integer> freq;
    private final List<Deque<Integer>> stackList;

    public FreqStack() {
        this.freq = new HashMap<>();
        this.stackList = new ArrayList<>();
    }

    public void push(int val) {
        int count = freq.merge(val, 1, Integer::sum);
        if (count > stackList.size()) {
            stackList.add(new ArrayDeque<>());
        }
        stackList.get(count - 1).push(val);
    }

    public int pop() {
        Deque<Integer> lastStack = stackList.get(stackList.size() - 1);
        int val = lastStack.pop();
        freq.merge(val, -1, Integer::sum);
        if (lastStack.isEmpty()) {
            stackList.remove(stackList.size() - 1);
        }
        return val;
    }
}
