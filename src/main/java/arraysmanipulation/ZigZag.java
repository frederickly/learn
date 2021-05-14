package arraysmanipulation;

public class ZigZag {
    /**
     * @param matrix: An array of integers
     * @return: An array of integers
     */
    public int[] printZMatrix(int[][] matrix) {
        int[] res;
        if(matrix==null || matrix.length==0|| matrix[0].length==0) return new int[]{};
        int curRow =0, curCol =0;
        int endRow = matrix.length -1;
        int endCol = matrix[0].length -1;


        res = new int[matrix.length * matrix[0].length];
        int index =0;
        res[index++] = matrix[0][0];
        while(curRow < endRow || curCol < endCol) {
            //System.out.println("curRow="+curRow +" curCol="+curCol);
           // System.out.println("start endRow="+endRow +" endCol="+endCol);
           // System.out.println("start curRow="+curRow +" curCol="+curCol + " index=" +index );

            // navigate to right
            if(curRow ==0 && curCol< endCol) {
                curCol++;
            }else{
                curRow++;
            }

            for(;curRow<= endRow && curCol>=0; curRow++, curCol--) {
               // System.out.println("1  curRow="+curRow +" curCol="+curCol + " index=" +index+  " values="+matrix[curRow][curCol]);
                res[index++]= matrix[curRow][curCol];
            }
            curRow--; curCol++; // over the border, step back
           // System.out.println("after 1 curRow="+curRow +" curCol="+curCol ) ;


            if(curCol == 0 && curRow < endRow) {
                curRow++;
            }else {
                curCol++;
            }
           // System.out.println("before 2 curRow="+curRow +" curCol="+curCol ) ;
            for(;curRow>=0 && curCol<= endCol; curRow--, curCol++){
               // System.out.println("2  curRow="+curRow +" curCol="+curCol + " index=" +index+  " values="+matrix[curRow][curCol]);
                res[index++]= matrix[curRow][curCol];
            }

            curCol--; curRow++;  // over the border, step back

        }
        
        return res;
    }
}
