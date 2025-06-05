package work.huangdu.question_bank.medium;

/**
 * 1061. 按字典序排列最小的等效字符串
 * SmallestEquivalentString
 *
 * @author huangdu
 * @version 2025/6/5
 */
public class SmallestEquivalentString {
    public String smallestEquivalentString(String s1, String s2, String baseStr) {
        UnionFindSet ufs = new UnionFindSet();
        for (int i = 0, n = s1.length(); i < n; i++) {
            char ch1 = s1.charAt(i), ch2 = s2.charAt(i);
            if (ch1 == ch2) {
                continue;
            }
            ufs.union(ch1, ch2);
        }
        int n = baseStr.length();
        char[] chars = new char[n];
        for (int i = 0; i < n; i++) {
            chars[i] = ufs.find(baseStr.charAt(i));
        }
        return new String(chars);
    }

    static class UnionFindSet {
        char[] parent = new char[128];

        UnionFindSet() {
            for (char ch = 'a'; ch <= 'z'; ch++) {
                parent[ch] = ch;
            }
        }

        void union(char x, char y) {
            if (x == y) {
                return;
            }
            char parentX = find(x), parentY = find(y);
            if (parentX == parentY) {
                return;
            }
            if (parentX < parentY) {
                parent[parentY] = parentX;
            } else {
                parent[parentX] = parentY;
            }
        }

        char find(char x) {
            char father = x;
            while (father != parent[father]) {
                father = parent[father];
            }
            parent[x] = father;
            return father;
        }
    }
}
