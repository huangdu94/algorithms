package work.huangdu.question_bank.medium;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Queue;

/**
 * 284. 顶端迭代器
 * 请你设计一个迭代器，除了支持 hasNext 和 next 操作外，还支持 peek 操作。
 * 实现 PeekingIterator 类：
 * PeekingIterator(int[] nums) 使用指定整数数组 nums 初始化迭代器。
 * int next() 返回数组中的下一个元素，并将指针移动到下个元素处。
 * bool hasNext() 如果数组中存在下一个元素，返回 true ；否则，返回 false 。
 * int peek() 返回数组中的下一个元素，但 不 移动指针。
 * 示例：
 * 输入：
 * ["PeekingIterator", "next", "peek", "next", "next", "hasNext"]
 * [[[1, 2, 3]], [], [], [], [], []]
 * 输出：
 * [null, 1, 2, 2, 3, false]
 * 解释：
 * PeekingIterator peekingIterator = new PeekingIterator([1, 2, 3]); // [1,2,3]
 * peekingIterator.next();    // 返回 1 ，指针移动到下一个元素 [1,2,3]
 * peekingIterator.peek();    // 返回 2 ，指针未发生移动 [1,2,3]
 * peekingIterator.next();    // 返回 2 ，指针移动到下一个元素 [1,2,3]
 * peekingIterator.next();    // 返回 3 ，指针移动到下一个元素 [1,2,3]
 * peekingIterator.hasNext(); // 返回 False
 * 提示：
 * 1 <= nums.length <= 1000
 * 1 <= nums[i] <= 1000
 * 对 next 和 peek 的调用均有效
 * next、hasNext 和 peek 最多调用  1000 次
 * 进阶：你将如何拓展你的设计？使之变得通用化，从而适应所有的类型，而不只是整数型？
 *
 * Java Iterator interface reference:
 * https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/10/6
 */
public class PeekingIterator implements Iterator<Integer> {
    private final Queue<Integer> peekQueue;
    private final Iterator<Integer> iterator;

    public PeekingIterator(Iterator<Integer> iterator) {
        // initialize any member here.
        this.peekQueue = new ArrayDeque<>();
        this.iterator = iterator;
    }

    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        Integer next;
        if (peekQueue.isEmpty()) {
            next = iterator.next();
            peekQueue.offer(next);
        } else {
            next = peekQueue.peek();
        }
        return next;
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public Integer next() {
        if (!peekQueue.isEmpty()) {return peekQueue.poll();}
        return iterator.next();
    }

    @Override
    public boolean hasNext() {
        return !peekQueue.isEmpty() || iterator.hasNext();
    }
}