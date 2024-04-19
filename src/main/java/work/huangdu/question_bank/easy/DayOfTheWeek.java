package work.huangdu.question_bank.easy;

import java.util.Calendar;

/**
 * 1185. 一周中的第几天
 * 给你一个日期，请你设计一个算法来判断它是对应一周中的哪一天。
 * 输入为三个整数：day、month 和 year，分别表示日、月、年。
 * 您返回的结果必须是这几个值中的一个 {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"}。
 * 示例 1：
 * 输入：day = 31, month = 8, year = 2019
 * 输出："Saturday"
 * 示例 2：
 * 输入：day = 18, month = 7, year = 1999
 * 输出："Sunday"
 * 示例 3：
 * 输入：day = 15, month = 8, year = 1993
 * 输出："Sunday"
 * 提示：
 * 给出的日期一定是在 1971 到 2100 年之间的有效日期。
 *
 * @author huangdu
 * @version 2022/1/4
 */
public class DayOfTheWeek {
    private static final String[] CONSTANT_ARRAY = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

    public String dayOfTheWeek(int day, int month, int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day);
        return CONSTANT_ARRAY[calendar.get(Calendar.DAY_OF_WEEK) - 1];
    }

    public static void main(String[] args) {
        DayOfTheWeek dw = new DayOfTheWeek();
        System.out.println(dw.dayOfTheWeek(4, 1, 2022));
    }
}
