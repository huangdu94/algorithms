package work.huangdu.question_bank.difficult;

/**
 * 335. 路径交叉
 * 给你一个整数数组 distance 。
 * 从 X-Y 平面上的点 (0,0) 开始，先向北移动 distance[0] 米，然后向西移动 distance[1] 米，向南移动 distance[2] 米，向东移动 distance[3] 米，持续移动。也就是说，每次移动后你的方位会发生逆时针变化。
 * 判断你所经过的路径是否相交。如果相交，返回 true ；否则，返回 false 。
 * 示例 1：
 * 输入：distance = [2,1,1,2]
 * 输出：true
 * 示例 2：
 * 输入：distance = [1,2,3,4]
 * 输出：false
 * 示例 3：
 * 输入：distance = [1,1,1,1]
 * 输出：true
 * 提示：
 * 1 <= distance.length <= 10^5
 * 1 <= distance[i] <= 10^5
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/10/29
 */
public class IsSelfCrossing {
    // TODO 归纳法
    public boolean isSelfCrossing(int[] distance) {
        int n = distance.length;
        for (int i = 3; i < n; ++i) {
            // 第 1 类路径交叉的情况
            if (distance[i] >= distance[i - 2] && distance[i - 1] <= distance[i - 3]) {
                return true;
            }

            // 第 2 类路径交叉的情况
            if (i == 4 && (distance[3] == distance[1]
                && distance[4] >= distance[2] - distance[0])) {
                return true;
            }

            // 第 3 类路径交叉的情况
            if (i >= 5 && (distance[i - 3] - distance[i - 5] <= distance[i - 1]
                && distance[i - 1] <= distance[i - 3]
                && distance[i] >= distance[i - 2] - distance[i - 4]
                && distance[i - 2] > distance[i - 4])) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        IsSelfCrossing isf = new IsSelfCrossing();
        System.out.println(isf.isSelfCrossing(new int[] {3, 3, 4, 2, 2}));
    }

/*    public boolean isSelfCrossing(int[] distance) {
        int n = distance.length;
        Map<Double, List<double[]>> xs = new HashMap<>(n / 2);
        Map<Double, List<double[]>> ys = new HashMap<>(n / 2 + 1);
        double curX = 0;
        double curY = 0;
        for (int i = 0; i < n; i++) {
            double preX = curX, preY = curY;
            List<double[]> list;
            double[] interval;
            switch (i & 0x3) {
                case 0:
                    curY += distance[i];
                    list = xs.computeIfAbsent(curX, k -> new ArrayList<>());
                    interval = new double[] {preY, curY - 0.1};
                    break;
                case 1:
                    curX -= distance[i];
                    list = ys.computeIfAbsent(curY, k -> new ArrayList<>());
                    interval = new double[] {curX + 0.1, preX};
                    break;
                case 2:
                    curY -= distance[i];
                    list = xs.computeIfAbsent(curX, k -> new ArrayList<>());
                    interval = new double[] {curY + 0.1, preY};
                    break;
                default:
                    curX += distance[i];
                    list = ys.computeIfAbsent(curY, k -> new ArrayList<>());
                    interval = new double[] {preX, curX - 0.1};
                    break;
            }
            Arrays.sort(interval);
            list.add(interval);
        }
        for (List<double[]> xlist : xs.values()) {
            xlist.sort(Comparator.comparingDouble(o -> o[0]));
            for (int i = 1, size = xlist.size(); i < size; i++) {
                if (xlist.get(i - 1)[1] >= xlist.get(i)[0]) {
                    return true;
                }
            }
        }
        for (List<double[]> ylist : ys.values()) {
            ylist.sort(Comparator.comparingDouble(o -> o[0]));
            for (int i = 1, size = ylist.size(); i < size; i++) {
                if (ylist.get(i - 1)[1] >= ylist.get(i)[0]) {
                    return true;
                }
            }
        }
        for (Map.Entry<Double, List<double[]>> xentry : xs.entrySet()) {
            double x = xentry.getKey();
            List<Double> yMeetX = new ArrayList<>();
            for (Map.Entry<Double, List<double[]>> yentry : ys.entrySet()) {
                for (double[] interval : yentry.getValue()) {
                    if (interval[0] <= x && x <= interval[1]) {
                        yMeetX.add(yentry.getKey());
                        break;
                    }
                }
            }
            for (double y : yMeetX) {
                for (double[] interval : xentry.getValue()) {
                    if (interval[0] <= y && y <= interval[1]) {
                        return true;
                    }
                }
            }
        }
        return false;
    }*/
}