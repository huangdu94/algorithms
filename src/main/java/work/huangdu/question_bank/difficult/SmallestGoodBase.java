package work.huangdu.question_bank.difficult;

/**
 * 483. 最小好进制
 * 对于给定的整数 n, 如果n的k（k>=2）进制数的所有数位全为1，则称 k（k>=2）是 n 的一个好进制。
 * 以字符串的形式给出 n, 以字符串的形式返回 n 的最小好进制。
 * 示例 1：
 * 输入："13"
 * 输出："3"
 * 解释：13 的 3 进制是 111。
 * 示例 2：
 * 输入："4681"
 * 输出："8"
 * 解释：4681 的 8 进制是 11111。
 * 示例 3：
 * 输入："1000000000000000000"
 * 输出："999999999999999999"
 * 解释：1000000000000000000 的 999999999999999999 进制是 11。
 * 提示：
 * n的取值范围是 [3, 10^18]。
 * 输入总是有效且没有前导 0。
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/6/18
 */
public class SmallestGoodBase {
    // TODO 数学推导 二项式定理
    public String smallestGoodBase(String n) {
        long nVal = Long.parseLong(n);
        int mMax = (int)(Math.log(nVal) / Math.log(2));
        for (int m = mMax; m > 1; m--) {
            int k = (int)Math.pow(nVal, 1.0 / m);
            long mul = 1, sum = 1;
            for (int i = 0; i < m; i++) {
                mul *= k;
                sum += mul;
            }
            if (sum == nVal) {
                return Integer.toString(k);
            }
        }
        return Long.toString(nVal - 1);
    }

    public String smallestGoodBase2(String n) {
        //将字符串转为long类型
        long num = Long.parseLong(n);
        //求得数位1的最大数量，即转为2进制时对应的数量
        int mMax = (int)Math.ceil(Math.log(num) / Math.log(2));
        for (int m = mMax; m > 1; m--) {
            //二分法查找是否存在对应k进制，第一个找到存在的就是答案
            long left = 2;
            long right = num - 1;
            while (left <= right) {
                long mid = left + ((right - left) >> 1);
                if (goodBaseVal(m, mid) == num) {
                    return Long.toString(mid);
                }
                //溢出时也说明要找的k进制比当前mid小
                long val = goodBaseVal(m, mid);
                if (val == num) {
                    return Long.toString(left);
                }
                if (val == -1 || val > num) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }
        return Long.toString(num - 1);
    }

    //求 数位1数量为 n , 进制为 k 时对应的值
    public long goodBaseVal(int n, long k) {
        long sum = 1;
        long cur = 1;
        for (int i = 1; i < n; i++) {
            //溢出时返回-1
            if (cur > Long.MAX_VALUE / k) { return -1; }
            cur *= k;
            if (sum > Long.MAX_VALUE - cur) { return -1; }
            sum += cur;
        }
        return sum;
    }

    public static void main(String[] args) {
        SmallestGoodBase sgb = new SmallestGoodBase();
        System.out.println(sgb.smallestGoodBase2("15"));
    }
}
