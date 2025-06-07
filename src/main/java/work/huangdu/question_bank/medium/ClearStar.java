package work.huangdu.question_bank.medium;

import java.util.*;

/**
 * 3170. 删除星号以后字典序最小的字符串
 * ClearStar
 *
 * @author huangdu
 * @version 2025/6/7
 */
public class ClearStar {
    /**
     * 从前往后遍历s：
     * 1. 维护一个堆（排序规则，先按字母升序排序，字母相同按序列号倒序排序）
     * 2. 维护一个列表里面记录所有要删除的字符index（包括*号的位置）
     * 3. 生成答案
     * 优化：
     * 1. 第一遍遍历记录最后一个*号的位置（如果没有*直接返回）
     * 2. 第二遍遍历只需要处理到最后一个*号的位置，剩余的部分直接append到答案
     */
    public String clearStars(String s) {
        int n = s.length(), lastStarIndex = -1;
        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) == '*') {
                lastStarIndex = i;
                break;
            }
        }
        if (lastStarIndex == -1) {
            return s;
        }
        PriorityQueue<Integer> heap = new PriorityQueue<>((o1, o2) -> {
            int compareLetter = Character.compare(s.charAt(o1), s.charAt(o2));
            if (compareLetter != 0) {
                return compareLetter;
            }
            return Integer.compare(o2, o1);
        });
        List<Integer> deleteIndexList = new ArrayList<>();
        for (int i = 0; i <= lastStarIndex; i++) {
            char ch = s.charAt(i);
            if (ch != '*') {
                heap.offer(i);
            } else {
                deleteIndexList.add(heap.poll());
                deleteIndexList.add(i);
            }
        }
        deleteIndexList.sort(Integer::compareTo);
        int size = deleteIndexList.size();
        StringBuilder ansBuilder = new StringBuilder(n - size);
        ansBuilder.append(s, 0, deleteIndexList.get(0));
        for (int i = 1; i < size; i++) {
            ansBuilder.append(s, deleteIndexList.get(i - 1) + 1, deleteIndexList.get(i));
        }
        ansBuilder.append(s, lastStarIndex + 1, n);
        return ansBuilder.toString();
    }

    /**
     * 从前往后遍历s：
     * 1. 维护一个堆（排序规则，先按字母升序排序，字母相同按序列号倒序排序）
     * 2. 维护一个列表里面记录所有要删除的字符index（包括*号的位置）
     * 3. 生成答案
     * 优化：
     * 1. 第一遍遍历记录最后一个*号的位置（如果没有*直接返回）
     * 2. 第二遍遍历只需要处理到最后一个*号的位置，剩余的部分直接append到答案
     * 优化：
     * 1. 如果需要删除则直接标记那个位置为*，不需要用额外列表来记录
     */
    public String clearStars2(String s) {
        int n = s.length(), lastStarIndex = -1;
        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) == '*') {
                lastStarIndex = i;
                break;
            }
        }
        if (lastStarIndex == -1) {
            return s;
        }
        char[] chars = s.toCharArray();
        PriorityQueue<Integer> heap = new PriorityQueue<>((o1, o2) -> {
            int compareLetter = Character.compare(chars[o1], chars[o2]);
            if (compareLetter != 0) {
                return compareLetter;
            }
            return Integer.compare(o2, o1);
        });
        for (int i = 0; i <= lastStarIndex; i++) {
            if (chars[i] != '*') {
                heap.offer(i);
            } else {
                chars[heap.remove()] = '*';
            }
        }
        int j = 0;
        for (int i = 0; i < n; i++) {
            if (chars[i] != '*') {
                chars[j++] = chars[i];
            }
        }
        return new String(chars, 0, j);
    }

    /**
     * 从前往后遍历s：
     * 1. 维护一个堆（排序规则，先按字母升序排序，字母相同按序列号倒序排序）
     * 2. 维护一个列表里面记录所有要删除的字符index（包括*号的位置）
     * 3. 生成答案
     * 优化：
     * 1. 第一遍遍历记录最后一个*号的位置（如果没有*直接返回）
     * 2. 第二遍遍历只需要处理到最后一个*号的位置，剩余的部分直接append到答案
     * 优化：
     * 1. 如果需要删除则直接标记那个位置为*，不需要用额外列表来记录
     * 优化：
     * 只有26个字母没必要用堆
     */
    public String clearStars3(String s) {
        int n = s.length(), lastStarIndex = -1;
        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) == '*') {
                lastStarIndex = i;
                break;
            }
        }
        if (lastStarIndex == -1) {
            return s;
        }
        char[] chars = s.toCharArray();
        Deque<Integer>[] stacks = new Deque[26];
        for (int i = 0; i < 26; i++) {
            stacks[i] = new ArrayDeque<>();
        }
        for (int i = 0; i <= lastStarIndex; i++) {
            char ch = chars[i];
            if (ch != '*') {
                stacks[ch - 'a'].push(i);
            } else {
                for (Deque<Integer> stack : stacks) {
                    if (!stack.isEmpty()) {
                        chars[stack.pop()] = '*';
                        break;
                    }
                }
            }
        }
        int j = 0;
        for (int i = 0; i < n; i++) {
            if (chars[i] != '*') {
                chars[j++] = chars[i];
            }
        }
        return new String(chars, 0, j);
    }

    /**
     * 从前往后遍历s：
     * 1. 维护一个堆（排序规则，先按字母升序排序，字母相同按序列号倒序排序）
     * 2. 维护一个列表里面记录所有要删除的字符index（包括*号的位置）
     * 3. 生成答案
     * 优化：
     * 1. 第一遍遍历记录最后一个*号的位置（如果没有*直接返回）
     * 2. 第二遍遍历只需要处理到最后一个*号的位置，剩余的部分直接append到答案
     * 优化：
     * 1. 如果需要删除则直接标记那个位置为*，不需要用额外列表来记录
     * 优化：
     * 只有26个字母没必要用堆
     * 优化：
     * 使用位运算得到第一个非空栈的位置
     */
    public String clearStars4(String s) {
        int n = s.length(), lastStarIndex = -1;
        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) == '*') {
                lastStarIndex = i;
                break;
            }
        }
        if (lastStarIndex == -1) {
            return s;
        }
        char[] chars = s.toCharArray();
        Deque<Integer>[] stacks = new Deque[26];
        for (int i = 0; i < 26; i++) {
            stacks[i] = new ArrayDeque<>();
        }
        int mask = 0;
        for (int i = 0; i <= lastStarIndex; i++) {
            char ch = chars[i];
            if (ch != '*') {
                stacks[ch - 'a'].push(i);
                mask |= 1 << ch - 'a';
            } else {
                int pos = Integer.numberOfTrailingZeros(mask);
                Deque<Integer> stack = stacks[pos];
                chars[stack.pop()] = '*';
                if (stack.isEmpty()) {
                    mask ^= 1 << pos;
                }
            }
        }
        int j = 0;
        for (int i = 0; i < n; i++) {
            if (chars[i] != '*') {
                chars[j++] = chars[i];
            }
        }
        return new String(chars, 0, j);
    }
}
