package work.huangdu.exploration.start_from_scratch.binary.number_bit_operation;

/**
 * 458. 可怜的小猪
 * 有 buckets 桶液体，其中 正好 有一桶含有毒药，其余装的都是水。它们从外观看起来都一样。为了弄清楚哪只水桶含有毒药，你可以喂一些猪喝，通过观察猪是否会死进行判断。不幸的是，你只有 minutesToTest 分钟时间来确定哪桶液体是有毒的。
 * 喂猪的规则如下：
 * 选择若干活猪进行喂养
 * 可以允许小猪同时饮用任意数量的桶中的水，并且该过程不需要时间。
 * 小猪喝完水后，必须有 minutesToDie 分钟的冷却时间。在这段时间里，你只能观察，而不允许继续喂猪。
 * 过了 minutesToDie 分钟后，所有喝到毒药的猪都会死去，其他所有猪都会活下来。
 * 重复这一过程，直到时间用完。
 * 给你桶的数目 buckets ，minutesToDie 和 minutesToTest ，返回在规定时间内判断哪个桶有毒所需的 最小 猪数。
 * 示例 1：
 * 输入：buckets = 1000, minutesToDie = 15, minutesToTest = 60
 * 输出：5
 * 示例 2：
 * 输入：buckets = 4, minutesToDie = 15, minutesToTest = 15
 * 输出：2
 * 示例 3：
 * 输入：buckets = 4, minutesToDie = 15, minutesToTest = 30
 * 输出：2
 * 提示：
 * 1 <= buckets <= 1000
 * 1 <= minutesToDie <= minutesToTest <= 100
 *
 * @author huangdu
 * @version 2020/10/17 11:16
 */
public class PoorPigs {
    public int poorPigs(int buckets, int minutesToDie, int minutesToTest) {
        if (buckets == 1) {return 0;}
        int time = minutesToTest / minutesToDie + 1;
        int max = time, count = 1;
        while (max < buckets) {
            max *= time;
            count++;
        }
        return count;
    }

    public static void main(String[] args) {
        PoorPigs pp = new PoorPigs();
        System.out.println(pp.poorPigs(4, 15, 30));
    }

    public int poorPigs2(int buckets, int minutesToDie, int minutesToTest) {
        if (buckets == 1) { return 0; }
        int states = minutesToTest / minutesToDie + 1, count = states, res = 1;
        while (count < buckets) {
            count *= states;
            res++;
        }
        return res;
    }
}
