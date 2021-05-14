package backpack;

public class JohnBackyardGarden {
    /**
     * @param x: the wall's height
     * @return: YES or NO
     */
    public String isBuild(int x) {
        // 完全背包
        // 因为是需要看是否能装满某一具体的容量，要用boolean数组来记录哪些是可以围成的；类似：799
        // dp[x]表示高度为x的墙是否能刚好被围成
        int[] heights = {3, 7};
        boolean[] dp = new boolean[x + 1];
        dp[0] = true;

        for (int i = 0; i < 2; i++) { // 遍历每种物品（每款砖）
            for (int j = heights[i]; j <= x; j++) { // 遍历每种高度
                dp[j] = dp[j] || dp[j - heights[i]];
                // 本来就可以被围成 VS 之前可以被围成且加上当前这块砖可以被围成（扣去当前砖之前可以被围成）
            }
        }

        return dp[x] ? "YES" : "NO";
    }

    public String isBuild2(int x) {
        final String YES ="YES";
        if(x ==3 || x==7 ) return YES;
        if(x< 3) return "NO";

        if(YES.equals(isBuild(x-7)) ||YES.equals(isBuild(x-3))) {
            return YES;
        }else{
            return "NO";
        }
    }
}
