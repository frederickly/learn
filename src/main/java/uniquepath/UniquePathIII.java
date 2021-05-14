package uniquepath;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import static java.util.Arrays.asList;

public class UniquePathIII {

    public static void main(String[] args){
        new UniquePathIII().uniqueWeightedPaths(new int[][] {{1,1,2},{1,2,3},{3,2,4}});
        new UniquePathIII().uniqueWeightedPaths(new int[][] {{}});
    }

    /*
     * @param : an array of arrays
     * @return: the sum of all unique weighted paths
     */
    public int uniqueWeightedPaths(int[][] grid) {
        // write your codes here
        // write your code here
        int m = grid.length;
        int n = grid[0].length;
        if(m>0 && n>0 ) {
            Set<Integer>[][] values = new HashSet[m][n];

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {

                    if (i > 0 && j > 0) {
                        values[i][j] = getValues(values[i - 1][j], grid[i][j]);
                        values[i][j].addAll( getValues(values[i][j - 1], grid[i][j]) );
                    }else if(i==0 && j> 0) {
                        values[i][j] = getValues(values[i][j - 1], grid[i][j]);
                    }else if(j==0 && i> 0) {
                        values[i][j] = getValues(values[i - 1][j], grid[i][j]);
                    }else{
                        values[i][j] = new HashSet<>();
                        values[i][j].add(grid[i][j]);
                    }
                }
            }

            return values[m - 1][n - 1].stream().parallel().mapToInt(Integer::intValue).sum();
        }else{
            return 0;
        }
    }

    private Set<Integer> getValues(final Set<Integer> values, final int weight){
        Set<Integer> result = new HashSet<>();
        Iterator<Integer> iterator =values.iterator();
        while(iterator.hasNext()){
            result.add(iterator.next() + weight);
        }
        return result;
    }
}
