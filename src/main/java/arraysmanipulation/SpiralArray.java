package arraysmanipulation;

/**
 Description
 中文
 English

 Given an integer n, return a spiral array of n * n sizes.
 Have you met this question in a real interview?
 Example

 Example 1:

 Input : n = 3
 Output :
 [
 [1,2,3]
 [8,9,4]
 [7,6,5]
 ]

 Example 2:

 Input : n = 5
 Output :
 [
 [1,2,3,4,5]
 [16,17,18,19,6]
 [15,24,25,20,7]
 [14,23,22,21,8]
 [13,12,11,10,9]
 ]


 1.greedy向一个方向填充，遇到边界或者已填充过就换方向。
 2.recursive画边框，逐渐缩小边框。

 Greedy approach思路
 向一个方向填充直到stop，之后换方向继续。

 初始化一个n * n的矩阵作为答案，一开始都init为0。从（0， 0）作为起点，顺序选择（右，下，左，上）四个方向，遇到边界或者已经填过的空位就换方向继续，直到整个矩阵填满为止。
 选择方向时为了保证一直循环，可以用queue，pop第一个方向后append到结尾，构成一个可持续的selection space。也可以用 DIR[i % 4]在4个选项里来回循环。

 Recursive思路
 从外向内画边框。逐渐缩小边框范围。注意横纵交接处不要重复画。
 */

public class SpiralArray {

    /**
     * @param n: a Integer
     * @return: a spiral array
     * Greedy solution
     */
    public int[][] spiralArray(int n) {
        int[][] chess= new int[n][n];
        for(int i=0;i<n;i++)
            for(int j=0;j<n;j++)
                chess[i][j] =-1;

        int count = 0;
        int total = n * n;
        int dr = 0;
        int dc = 1;
        int r = 0;
        int c = -1;
        while (count < total)
        {
            int nr = r + dr;
            int nc = c + dc;
            if (nr < 0 || nr >= n || nc < 0 || nc >= n || chess[nr][nc] != -1)
            {
                int temp = dc;
                dc = -dr;
                dr = temp;
            }
            r = r + dr;
            c = c + dc;
            chess[r][c] = ++count;
        }
        return chess;
    }

    /*
      recursion solution
     */
    public int[][] spiralArray2(int n) {
        int[][] res= new int[n][n];
        int count = 1;
        for(int k = 0; k <= n / 2; ++ k) {

            for(int i = k; i < n - k; ++ i)
                res[k][i] = count++;

            for(int j = k + 1; j < n - k - 1; ++ j)
                res[j][n - k - 1] = count++;

            for(int i = n - k - 1; i >= k; -- i)
                res[n - k - 1][i] = count++;

            for(int j = n - k - 2; j > k; -- j)
                res[j][k] = count++;
        }

        if(n % 2 == 1) res[n / 2][n / 2] = n * n;

        return res;
    }
}
