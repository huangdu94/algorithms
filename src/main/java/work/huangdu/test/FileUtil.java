package work.huangdu.test;

/**
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2022/4/8
 */
final class FileUtil {
    private FileUtil() {}

    static String readFile(String filePath) {
        StringBuilder sb = new StringBuilder();
        try {
            java.io.File file = new java.io.File(filePath);
            java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
