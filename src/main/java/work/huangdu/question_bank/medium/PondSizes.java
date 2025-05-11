package work.huangdu.question_bank.medium;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

/**
 * 面试题 16.19. 水域大小
 * 你有一个用于表示一片土地的整数矩阵land，该矩阵中每个点的值代表对应地点的海拔高度。若值为0则表示水域。由垂直、水平或对角连接的水域为池塘。池塘的大小是指相连接的水域的个数。编写一个方法来计算矩阵中所有池塘的大小，返回值需要从小到大排序。
 * 示例：
 * 输入：
 * [
 * [0,2,1,0],
 * [0,1,0,1],
 * [1,1,0,1],
 * [0,1,0,1]
 * ]
 * 输出： [1,2,4]
 * 提示：
 * 0 < len(land) <= 1000
 * 0 < len(land[i]) <= 1000
 *
 * @author huangdu
 * @version 2023/6/25
 */
public class PondSizes {
    public int[] pondSizes(int[][] land) {
        int m = land.length, n = land[0].length;
        int[] direction = {-1, 1, 1, 0, 1, -1, -1, 0, -1};
        List<Integer> ansList = new ArrayList<>();
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (land[i][j] == 0) {
                    int size = 0;
                    queue.offer(i * n + j);
                    land[i][j] = -1;
                    while (!queue.isEmpty()) {
                        int x = queue.peek() / n, y = queue.poll() % n;
                        size++;
                        for (int k = 0; k < 8; k++) {
                            int nextX = x + direction[k], nextY = y + direction[k + 1];
                            if (nextX >= 0 && nextX < m && nextY >= 0 && nextY < n && land[nextX][nextY] == 0) {
                                land[nextX][nextY] = -1;
                                queue.offer(nextX * n + nextY);
                            }
                        }
                    }
                    ansList.add(size);
                }
            }
        }
        int size = ansList.size();
        int[] ans = new int[size];
        for (int i = 0; i < size; i++) {
            ans[i] = ansList.get(i);
        }
        Arrays.sort(ans);
        return ans;
    }
}
