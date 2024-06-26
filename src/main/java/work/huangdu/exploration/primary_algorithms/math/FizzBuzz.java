package work.huangdu.exploration.primary_algorithms.math;

import java.util.ArrayList;
import java.util.List;

/**
 * 写一个程序，输出从 1 到 n 数字的字符串表示。
 * 1. 如果 n 是3的倍数，输出“Fizz”；
 * 2. 如果 n 是5的倍数，输出“Buzz”；
 * 3.如果 n 同时是3和5的倍数，输出 “FizzBuzz”。
 * 示例：
 * n = 15,
 * 返回:
 * [
 * "1",
 * "2",
 * "Fizz",
 * "4",
 * "Buzz",
 * "Fizz",
 * "7",
 * "8",
 * "Fizz",
 * "Buzz",
 * "11",
 * "Fizz",
 * "13",
 * "14",
 * "FizzBuzz"
 * ]
 *
 * @author huangdu
 * @version 2020/6/29 16:11
 */
public class FizzBuzz {
    public List<String> fizzBuzz(int n) {
        List<String> result = new ArrayList<>(n);
        for (int i = 1; i <= n; i++) {
            String temp = "";
            if (n % 3 == 0)
                temp += "Fizz";
            if (n % 5 == 0)
                temp += "Buzz";
            if (n % 3 != 0 && n % 5 != 0)
                temp += Integer.toString(i);
            result.add(temp);
        }
        return result;
    }

    public List<String> fizzBuzz2(int n) {
        List<String> res = new ArrayList<>(n);
        for (int i = 1; i <= n; i++) {
            StringBuilder iStr = new StringBuilder();
            if (i % 3 == 0) {
                iStr.append("Fizz");
            }
            if (i % 5 == 0) {
                iStr.append("Buzz");
            }
            if (iStr.length() == 0) {
                iStr.append(i);
            }
            res.add(iStr.toString());
        }
        return res;
    }
}
