package work.huangdu.question_bank.medium;

/**
 * 面试题 01.05. 一次编辑
 * 字符串有三种编辑操作:插入一个字符、删除一个字符或者替换一个字符。 给定两个字符串，编写一个函数判定它们是否只需要一次(或者零次)编辑。
 * 示例 1:
 * 输入:
 * first = "pale"
 * second = "ple"
 * 输出: True
 * 示例 2:
 * 输入:
 * first = "pales"
 * second = "pal"
 * 输出: False
 *
 * @author huangdu
 * @version 2022/5/13
 */
public class OneEditAway {
    private final int REPLACE = 0;
    private final int DELETE = 1;
    private final int INSERT = -1;

    public boolean oneEditAway(String first, String second) {
        int n1 = first.length(), n2 = second.length(), mark = n1 - n2;
        if (mark != REPLACE && mark != DELETE && mark != INSERT) {return false;}
        boolean diff = false;
        for (int i = 0, j = 0; i < n1 && j < n2; i++, j++) {
            if (first.charAt(i) != second.charAt(j)) {
                if (!diff) {
                    diff = true;
                    if (mark == DELETE) {
                        j--;
                    } else if (mark == INSERT) {
                        i--;
                    }
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        OneEditAway oea = new OneEditAway();
        System.out.println(oea.oneEditAway("teacher", "teachy"));
    }
}
