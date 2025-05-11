package work.huangdu.exploration.primary_algorithms.sort_search;

/**
 * 278. 第一个错误的版本
 * 你是产品经理，目前正在带领一个团队开发新的产品。不幸的是，你的产品的最新版本没有通过质量检测。由于每个版本都是基于之前的版本开发的，所以错误的版本之后的所有版本都是错的。
 * 假设你有 n 个版本 [1, 2, ..., n]，你想找出导致之后所有版本出错的第一个错误的版本。
 * 你可以通过调用 bool isBadVersion(version) 接口来判断版本号 version 是否在单元测试中出错。实现一个函数来查找第一个错误的版本。你应该尽量减少对调用 API 的次数。
 * 示例:
 * 给定 n = 5，并且 version = 4 是第一个错误的版本。
 * 调用 isBadVersion(3) -> false
 * 调用 isBadVersion(5) -> true
 * 调用 isBadVersion(4) -> true
 * 所以，4 是第一个错误的版本。
 * The isBadVersion API is defined in the parent class VersionControl.
 * boolean isBadVersion(int version);
 *
 * @author huangdu
 * @version 2020/6/23 11:22
 */
public class FirstBadVersion extends VersionControl {
    public FirstBadVersion(int count, int firstVersion) {
        super(count, firstVersion);
    }

    public int firstBadVersion(int n) {
        int i = 1, j = n;
        while (i < j) {
            int version = i + ((j - i) >> 1);
            if (isBadVersion(version)) {
                j = version;
            } else {
                i = version + 1;
            }
        }
        return i;
    }

    public int firstBadVersion1(int n) {
        int cur = 0;
        while (++cur < n) { if (isBadVersion(cur)) { return cur; } }
        return n;
    }

    public int firstBadVersion2(int n) {
        int left = 1;
        int right = n;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (isBadVersion(mid)) { right = mid; } else { left = mid + 1; }
        }
        return left;
    }
}

class VersionControl {
    private final int count;
    private final int firstVersion;

    /**
     * 设置版本数量和第一个错误版本
     *
     * @param count        版本数量
     * @param firstVersion 第一个错误版本
     */
    public VersionControl(int count, int firstVersion) {
        this.count = count;
        this.firstVersion = firstVersion;
    }

    /**
     * 检测是否是有问题的版本
     */
    protected boolean isBadVersion(int version) {
        if (version > count || version < 0) { throw new RuntimeException(); }
        return version >= firstVersion;
    }
}
