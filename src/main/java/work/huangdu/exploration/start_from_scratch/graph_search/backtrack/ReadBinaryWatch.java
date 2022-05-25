package work.huangdu.exploration.start_from_scratch.graph_search.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * 401. 二进制手表
 * 二进制手表顶部有 4 个 LED 代表 小时（0-11），底部的 6 个 LED 代表 分钟（0-59）。
 * 每个 LED 代表一个 0 或 1，最低位在右侧。
 * 例如，上面的二进制手表读取 “3:25”。
 * 给定一个非负整数 n 代表当前 LED 亮着的数量，返回所有可能的时间。
 * 示例：
 * 输入: n = 1
 * 返回: ["1:00", "2:00", "4:00", "8:00", "0:01", "0:02", "0:04", "0:08", "0:16", "0:32"]
 * 提示：
 * 输出的顺序没有要求。
 * 小时不会以零开头，比如 “01:00” 是不允许的，应为 “1:00”。
 * 分钟必须由两位数组成，可能会以零开头，比如 “10:2” 是无效的，应为 “10:02”。
 * 超过表示范围（小时 0-11，分钟 0-59）的数据将会被舍弃，也就是说不会出现 "13:00", "0:61" 等时间。
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/3/31
 */
public class ReadBinaryWatch {
    private static final int[] HOUR = {8, 4, 2, 1};
    private static final int[] MINUTE = {32, 16, 8, 4, 2, 1};
    private static List<String> result;
    private static final List<String> hours = new ArrayList<>();
    private static final List<String> minutes = new ArrayList<>();

    public List<String> readBinaryWatch(int num) {
        result = new ArrayList<>();
        for (int i = 0, boundary = Math.min(num, 4); i <= boundary; i++) {
            getHours(0, i, 0);
            getMinutes(0, num - i, 0);
            addTimeString();
            hours.clear();
            minutes.clear();
        }
        return result;
    }

    private void getHours(int start, int count, int sum) {
        if (count == 0) {
            if (sum < 12) {
                hours.add(Integer.toString(sum));
            }
            return;
        }
        if (4 - start < count) {return;}
        for (int i = start; i < 4; i++) {
            getHours(i + 1, count - 1, sum + HOUR[i]);
        }
    }

    private void getMinutes(int start, int count, int sum) {
        if (count == 0) {
            if (sum < 60) {
                String sumStr = Integer.toString(sum);
                if (sum < 10) {
                    sumStr = "0".concat(sumStr);
                }
                minutes.add(sumStr);
            }
            return;
        }
        if (6 - start < count) {return;}
        for (int i = start; i < 6; i++) {
            getMinutes(i + 1, count - 1, sum + MINUTE[i]);
        }
    }

    private void addTimeString() {
        for (String hour : hours) {
            for (String minute : minutes) {
                result.add(hour.concat(":").concat(minute));
            }
        }
    }

    public static void main(String[] args) {
        ReadBinaryWatch rbw = new ReadBinaryWatch();
        System.out.println(rbw.readBinaryWatch(1));
    }
}
