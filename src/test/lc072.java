import static com.udf.BASE.isnull;
import static com.udf.BASE.log;
import static java.lang.Math.min;

public class lc072 {
    public static int minDistance(String word1, String word2) {
        if (word1 == null || word1.equals("")) return word2.length();
        if (word2 == null || word2.equals("")) return word1.length();
        if (word1.equals(word2)) return 0;

        // less space used - <<
        if (word1.length() > word2.length()) return minDistance(word2, word1);
        // less space used - >>

        int m, n, d, i, j;
        m = word1.length();
        n = word2.length();
        int[][] dp = new int[m+1][2];
        for (i = 0; i<=m; i++) dp[i][0] = i;
        dp[0][1] = 1;

        for (j = 1; j<=n; j++) {
            for (i = 1; i<=m; i++) {
                d = (word1.charAt(i-1) == word2.charAt(j-1)) ? 0 : 1;
                dp[i][1] = Math.min(dp[i-1][0]+d, Math.min(dp[i][0]+1, dp[i-1][1]+1));
                //System.out.println(dp[i][1]);
            }
            for (i = 0; i<=m; i++) {
                dp[i][0] = dp[i][1];
            }
            dp[0][1] = dp[0][0] + 1;
        }

        return dp[m][1];
    }

    public static void main(String[] args) {
        log(minDistance ("xyzcde", "xaaced"));
        log(minDistance ("xyzcde", "xaace"));
        log(minDistance ("xyzcd", "xaaced"));
        log(minDistance ("xyzcde", "xyzcde"));
        log(minDistance ("xyzcde", ""));
        log(minDistance (null, "xaaced"));
        log(minDistance ("sea", "ate"));
    }

}
