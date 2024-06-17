import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class lc322 {
    public static int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;

        // List of elements - {
        HashMap<Integer, ArrayList<Integer>> exp1 = new HashMap();
        ArrayList<Integer> l1;
        exp1.put(0, new ArrayList<>());
        // List of elements - }

        for (int i=1; i<=amount; i++) {
            for (int c : coins) {
                if (i >= c) {
                    if (dp[i] > dp[i-c]+1) {
                        dp[i] = dp[i-c]+1;
                        // List of elements - {
                        l1 = new ArrayList<>();
                        l1.add(c);
                        l1.addAll(exp1.get(i-c));
                        exp1.put(i, l1);
                        // List of elements - }
                    }
                }
            }
        }

        // List of elements - {
        // System.out.println(exp1.toString());
        System.out.println(exp1.get(amount));
        // List of elements - }

        if (dp[amount] > amount) {
            return -1;
        } else {
            return dp[amount];
        }
    }
    public static void main(String[] args) {
        int[] coin1 = {1, 2, 5};
        int amount = 11;

//        int[] coin1 = {2};
//        int amount = 3;
//
//        int[] coin1 = {1};
//        int amount = 0;

        System.out.println(coinChange(coin1, amount));
    }
}