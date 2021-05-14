import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class FindNumber
{

    /**
        * @param A: an integer sorted array
     * @param target: an integer to be inserted
     * @return: An integer
     */
    public int searchInsert(int[] A, int target) {
        // write your code here
        if(A.length ==0){
            return 0;
        }

        int begin =0;
        int end = A.length -1;
        int mid =0;
        while(begin<=end) {
            mid = (begin + end) /2;
            if(A[mid] <= target) {
                begin = mid;
            }else {
                end = mid;
            }
        }

        return mid;
    }
    /**
     * @param matrix: matrix, a list of lists of integers
     * @param target: An integer
     * @return: a boolean, indicate whether matrix contains target
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        // write your code here
        if(matrix.length ==0) {
            return false;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int total = m *n;
        int begin =0;
        int end = total -1;
        //System.out.println("m=" +m+  "  n="+n);
        while(begin<= end) {

            int mid = (begin + end )/2;


            int midValue = matrix[mid/n][mid % n];

            //System.out.println("begin=" +begin+ " mid=" + mid + " end="+end + " midValue=" + midValue);
            if( midValue > target){
                end = mid -1;
            }else if(midValue == target)  {
                return true;
            }else{
                begin = mid +1;
            }
        }
        return false;
    }
}
