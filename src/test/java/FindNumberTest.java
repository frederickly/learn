import org.junit.Test;

public class FindNumberTest {

    @Test
    public void test(){
        FindNumber fn= new FindNumber();
        int[][] matrix = new int[][]{{1,4,5},{6,7,8}};
        System.out.println("result =" + fn.searchMatrix(matrix, 6));
    }
}
