package work.huangdu.test;

import work.huangdu.question_bank.difficult.RangeModule;

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
            if (pair[i].isEmpty()) {continue;}
            String[] num = pair[i].split(",");
            data[i][0] = Integer.parseInt(num[0]);
            data[i][1] = Integer.parseInt(num[1]);
        }
        return data;
    }

    public static void execute(String data, String operate, String answer) {
        RangeModule rm = new RangeModule();
        int[][] dataArray = twoDimensionalArray("data.txt");
        String operateStr = FileUtil.readFile(DataUtil.class.getClassLoader().getResource(operate).getPath());
        String[] ops = operateStr.split(",");
        String answerStr = FileUtil.readFile(DataUtil.class.getClassLoader().getResource(answer).getPath());
        String[] answers = answerStr.split(",");
        System.out.println("all: " + dataArray.length);
        for (int i = 0; i < dataArray.length; i++) {
            switch (ops[i]) {
                case "addRange":
                    rm.addRange(dataArray[i][0], dataArray[i][1]);
                    break;
                case "removeRange":
                    rm.removeRange(dataArray[i][0], dataArray[i][1]);
                    break;
                case "queryRange":
                    boolean result = rm.queryRange(dataArray[i][0], dataArray[i][1]);
                    if (result != Boolean.parseBoolean(answers[i])) {
                        System.out.println("error: " + i);
                    }
            }
        }
    }

    public static void main(String[] args) {
        DataUtil.execute("data.txt", "operate.txt", "answer.txt");
    }
}
