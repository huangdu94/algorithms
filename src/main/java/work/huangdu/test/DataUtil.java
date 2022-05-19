package work.huangdu.test;

/**
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2022/4/8
 */
public final class DataUtil {
    private DataUtil() {}

    public static int[][] twoDimensionalArray(String path) {
        String absolutePath = DataUtil.class.getClassLoader().getResource(path).getPath();
        String pairs = FileUtil.readFile(absolutePath).replace("\n", "");
        pairs = pairs.substring(2, pairs.length() - 2);
        String[] pair = pairs.split("],\\[");
        // 初始化二维数组
        int[][] data = new int[pair.length][2];
        // 初始化每行数据
        for (int i = 0; i < pair.length; i++) {
            String[] num = pair[i].split(",");
            data[i][0] = Integer.parseInt(num[0]);
            data[i][1] = Integer.parseInt(num[1]);
        }
        return data;
    }
}
