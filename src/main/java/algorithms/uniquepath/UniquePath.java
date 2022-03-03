package algorithms.uniquepath;

public class UniquePath {

    public static void main(String[] args) {
        int[][] data= new int[3][3];
        data[1][1]=1;

        new UniquePath().uniquePathsWithObstacles(data);
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        // write your code here
        int m= obstacleGrid.length;
        int n= obstacleGrid[0].length;
        System.out.println("m="+m + " n="+n);

        int[][] values = new int[m][n];
        if(obstacleGrid[0][0] ==0) {
            values[0][0]=1;
        }
        System.out.println( "values[0][0]="+ values[0][0]);

        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++){
                //System.out.println("i="+i + " j="+j);
                if(i> 0 || j>0) {
                    values[i][j]= obstacleGrid[i][j]==1?0:

                            ((i>0 && obstacleGrid[i-1][j] == 0? values[i-1][j]:0)
                                    + (j>0 && obstacleGrid[i][j-1] == 0 ? values[i][j-1] :0));

                    System.out.println( "values["+ i +"]["+j+"]="+ values[i][j]);
                }

            }

        }

        return values[m-1][n-1];
    }
}
