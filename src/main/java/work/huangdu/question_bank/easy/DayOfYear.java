package work.huangdu.question_bank.easy;

/**
 * 1154. 一年中的第几天
 * 给你一个字符串 date ，按 YYYY-MM-DD 格式表示一个 现行公元纪年法 日期。请你计算并返回该日期是当年的第几天。
 * 通常情况下，我们认为 1 月 1 日是每年的第 1 天，1 月 2 日是每年的第 2 天，依此类推。每个月的天数与现行公元纪年法（格里高利历）一致。
 * 示例 1：
 * 输入：date = "2019-01-09"
 * 输出：9
 * 示例 2：
 * 输入：date = "2019-02-10"
 * 输出：41
 * 示例 3：
 * 输入：date = "2003-03-01"
 * 输出：60
 * 示例 4：
 * 输入：date = "2004-03-01"
 * 输出：61
 * 提示：
 * date.length == 10
 * date[4] == date[7] == '-'，其他的 date[i] 都是数字
 * date 表示的范围从 1900 年 1 月 1 日至 2019 年 12 月 31 日
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/12/21
 */
public class DayOfYear {
    private static final int[] MONTH_DAY = {0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334};

    public int dayOfYear(String date) {
        String[] temp = date.split("-");
        int month = Integer.parseInt(temp[1]) - 1, day = Integer.parseInt(temp[2]);
        if (month < 2) {return MONTH_DAY[month] + day;}
        int year = Integer.parseInt(temp[0]);
        if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
            return MONTH_DAY[month] + day + 1;
        }
        return MONTH_DAY[month] + day;
    }
}
