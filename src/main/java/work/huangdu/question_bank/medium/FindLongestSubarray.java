package work.huangdu.question_bank.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * 面试题 17.05.  字母与数字
 * 给定一个放有字母和数字的数组，找到最长的子数组，且包含的字母和数字的个数相同。
 * 返回该子数组，若存在多个最长子数组，返回左端点下标值最小的子数组。若不存在这样的数组，返回一个空数组。
 * 示例 1:
 * 输入: ["A","1","B","C","D","2","3","4","E","5","F","G","6","7","H","I","J","K","L","M"]
 * 输出: ["A","1","B","C","D","2","3","4","E","5","F","G","6","7"]
 * 示例 2:
 * 输入: ["A","A"]
 * 输出: []
 * 提示：
 * array.length <= 100000
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2023/3/11
 */
public class FindLongestSubarray {
    public String[] findLongestSubarray(String[] array) {
        int n = array.length;
        // key：字母的个数 - 数字的个数 value：index
        int maxLen = 0, maxKey = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        for (int i = 0, key = 0; i < n; i++) {
            key += Character.isDigit(array[i].charAt(0)) ? -1 : 1;
            if (!map.containsKey(key)) {
                map.put(key, i);
            } else {
                int len = i - map.get(key);
                if (len > maxLen) {
                    maxLen = len;
                    maxKey = key;
                }
            }
        }
        String[] ans = new String[maxLen];
        System.arraycopy(array, map.get(maxKey) + 1, ans, 0, maxLen);
        return ans;
    }
}
