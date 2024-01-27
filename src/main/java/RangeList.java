import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * RangeList
 * A pair of integers define a range, for example: [1, 5).This range includes integers: 1, 2, 3, and 4.
 * A range list is an aggregate of these ranges: [1, 5), [10, 11), [100, 201)
 *
 * Instances of {@code RangeList} are not safe for use by multiple threads.
 *
 * @author huangdu
 */
public class RangeList {
    private List<int[]> ranges;

    public RangeList() {
        this.ranges = new ArrayList<>();
    }

    /**
     * Adds a range to the list
     *
     * @param range - Array of two integers that specify beginning and end of range.
     */
    public void add(int[] range) {
        if (Objects.isNull(range) || range.length != 2 || range[0] >= range[1]) {return;}
        int start = range[0], end = range[1];
        List<int[]> newRanges = new ArrayList<>();
        // A flag indicating whether a new interval has been added
        boolean hasAdded = false;
        for (int[] current : ranges) {
            if (start > current[1]) {
                newRanges.add(current);
            } else if (end < current[0]) {
                if (!hasAdded) {
                    newRanges.add(new int[] {start, end});
                    hasAdded = true;
                }
                newRanges.add(current);
            } else {
                start = Math.min(start, current[0]);
                end = Math.max(end, current[1]);
            }
        }
        if (!hasAdded) {
            newRanges.add(new int[] {start, end});
        }
        this.ranges = newRanges;
    }

    /**
     * Removes a range from the list
     *
     * @param range - Array of two integers that specify beginning and end of range.
     */
    public void remove(int[] range) {
        if (Objects.isNull(range) || range.length != 2 || range[0] >= range[1]) {return;}
        int start = range[0], end = range[1];
        List<int[]> newRanges = new ArrayList<>();
        for (int[] current : ranges) {
            if (end <= current[0] || start >= current[1]) {
                newRanges.add(current);
            } else {
                if (start > current[0]) {
                    newRanges.add(new int[] {current[0], start});
                }
                if (end < current[1]) {
                    newRanges.add(new int[] {end, current[1]});
                }
            }
        }
        this.ranges = newRanges;
    }

    /**
     * Convert the list of ranges in the range list to a string
     *
     * @return A string representation of the range list
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int[] range : ranges) {
            sb.append('[').append(range[0]).append(", ").append(range[1]).append(") ");
        }
        return sb.toString().trim();
    }
}

/**
 * Test case
 */
class RangeListTest {
    public static void main(String[] args) {
        // Example run
        RangeList rl = new RangeList();
        rl.add(null);
        rl.add(new int[0]);
        rl.add(new int[] {0});
        rl.add(new int[] {0, 1, 2});
        check("".equals(rl.toString()));
        rl.add(new int[] {1, 5});
        check("[1, 5)".equals(rl.toString()));
        rl.add(new int[] {10, 20});
        check("[1, 5) [10, 20)".equals(rl.toString()));
        rl.add(new int[] {20, 20});
        check("[1, 5) [10, 20)".equals(rl.toString()));
        rl.add(new int[] {20, 21});
        check("[1, 5) [10, 21)".equals(rl.toString()));
        rl.add(new int[] {2, 4});
        check("[1, 5) [10, 21)".equals(rl.toString()));
        rl.add(new int[] {3, 8});
        check("[1, 8) [10, 21)".equals(rl.toString()));
        rl.remove(new int[] {10, 10});
        check("[1, 8) [10, 21)".equals(rl.toString()));
        rl.remove(new int[] {10, 11});
        check("[1, 8) [11, 21)".equals(rl.toString()));
        rl.remove(new int[] {15, 17});
        check("[1, 8) [11, 15) [17, 21)".equals(rl.toString()));
        rl.remove(new int[] {3, 19});
        check("[1, 3) [19, 21)".equals(rl.toString()));
    }

    private static void check(boolean var) {
        if (!var) {throw new RuntimeException("Test failure.");}
    }
}