import java.util.Arrays;

/**
 * 602. Russian Doll Envelopes
 * https://www.lintcode.com/problem/russian-doll-envelopes
 *
 * Give a number of envelopes with widths and heights given as a pair of integers (w, h). One envelope can fit into another if and only if both the width and height of one envelope is greater than the width and height of the other envelope.
 * Find the maximum number of nested layers of envelopes.
 * Example
 *
 * Example 1:
 *
 * Input：[[5,4],[6,4],[6,7],[2,3]]
 * Output：3
 * Explanation：
 * the maximum number of envelopes you can Russian doll is 3 ([2,3] => [5,4] => [6,7]).
 *
 * Example 2:
 *
 * Input：[[4,5],[4,6],[6,7],[2,3],[1,1]]
 * Output：4
 * Explanation：
 * the maximum number of envelopes you can Russian doll is 4 ([1,1] => [2,3] => [4,5] / [4,6] => [6,7]).
 *
 * dp timeout
 *
 * binarySearch
 * 只有当 enveloppe 的高度超过之前 enveloppe 的最大高度时, 嵌套层数才能 + 1. (因为排序后相同宽度的enveloppe 的高度是降序排列), 用二分查找找到当前高度在dp中的index 并把 index的值更新掉, 来保证高度最优.
 * 比如 [[4,5],[4,6],[6,7],[2,3],[1,1]], 排序后 [4, 5] 在 [4, 6] 之后, 5会覆盖掉6, 高度被优化
 *
 * same as
 * https://www.lintcode.com/problem/longest-increasing-subsequence
 * https://www.lintcode.com/problem/set-of-boxes
 */
public class RussianDollEnvelopes {
    /**
     * @param envelopes: a number of envelopes with widths and heights
     * @return: the maximum number of envelopes
     */
    public int maxEnvelopes(int[][] envelopes) {
        Arrays.sort(envelopes, (a1, a2)->{
            if(a1[0]!=a2[0]) return a1[0]-a2[0];
            return a2[1]- a1[1];
        });
        print(envelopes);

        int[] increaseSeq= new int[envelopes.length];
        int pos;
        int end=0;
        for(int i=0;i<envelopes.length;i++) {

            // (-(insertion point) - 1)
            pos= Arrays.binarySearch(increaseSeq, 0, end, envelopes[i][1]);
            if(pos<0) {
                pos= -pos -1;
            }

            if(pos==end) {
                increaseSeq[end++]= envelopes[i][1];
            }else {
                increaseSeq[pos]= envelopes[i][1];
            }
            print(increaseSeq);
        }
        return end;
    }

    public int maxEnvelopesDPTimeout(int[][] envelopes) {
        Arrays.sort(envelopes, (a1, a2)->{
            if(a1[0]!=a2[0]) return a1[0]-a2[0];
            return a1[1]- a2[1];
        });
        int res=1;
        int[] dp= new int[envelopes.length]; // end at index i, the value is the max nest
        dp[0]=1;
        for(int i=1;i<envelopes.length;i++) {
            dp[i]=1;
            for(int j=0;j<i;j++) {
                if(envelopes[i][0]> envelopes[j][0] && envelopes[i][1]> envelopes[j][1]) {
                    dp[i]= Math.max(dp[i], 1+ dp[j]);
                }
            }
            res= Math.max(res, dp[i]);
        }

        return res;
    }

    private void print(int[][] envelops) {
        StringBuilder sb=new StringBuilder();
        for(int[] env: envelops) {
            sb.append("["+env[0]+","+ env[1]+"],");
        }
        System.out.println(sb.toString());
    }

    private void print(int[] values) {
        StringBuilder sb=new StringBuilder();
        for(int v: values) sb.append(v+" , ");
        System.out.println(sb.toString());
    }

    public static void main(String[] args) {
        //int[][] data = new int[][]{{4,2},{4,6},{6,7},{2,3},{1,1}}; // expect
        //int[][] data = new int[][]{{5,4},{6,4},{6,7},{2,3}};
        //int[][] data = new int[][]{{1,2},{2,3},{3,4},{3,5},{4,5},{5,5},{5,6},{6,7},{7,8}};  // expect 7
        int[][] data= new int[][]{{15,8},{2,20},{2,14},{4,17},{8,19},{8,9},{5,7},{11,19},{8,11},{13,11},
                {2,13},{11,19},{8,11},{13,11},{2,13},{11,19},{16,1},{18,13},{14,17},{18,19}};
        RussianDollEnvelopes russianDollEnvelopes= new RussianDollEnvelopes();
        int res = russianDollEnvelopes.maxEnvelopes(data);
        System.out.println("res="+res);
    }
}
