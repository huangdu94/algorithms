package work.huangdu.interview.didi;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author huangdu
 * @version 2025/5/27
 */
public class Solution2 {
    public static void main(String[] args) {
        List<Integer> personA = new ArrayList<>(), personB = new ArrayList<>(), personC = new ArrayList<>();
        int[] cards = new int[54];
        for (int i = 1; i <= 54; i++) {
            cards[i - 1] = i;
        }
        Random random = new Random();
        int remain = 54;
        while (remain > 0) {
            remain = sendCard(personA, cards, random, remain);
            remain = sendCard(personB, cards, random, remain);
            remain = sendCard(personC, cards, random, remain);
        }
        System.out.println(personA);
        System.out.println(personA.size());
        System.out.println(personB);
        System.out.println(personB.size());
        System.out.println(personC);
        System.out.println(personC.size());
    }

    private static int sendCard(List<Integer> person, int[] cards, Random random, int remain) {
        int idx = random.nextInt(remain);
        int card = cards[idx];
        person.add(card);
        swap(cards, idx, remain - 1);
        return remain - 1;
    }

    private static void swap(int[] cards, int x, int y) {
        int temp = cards[x];
        cards[x] = cards[y];
        cards[y] = temp;
    }

}