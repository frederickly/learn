package arraysmanipulation;

import org.junit.Test;

public class ZigZagTest {
    @Test
    public void test() {
        ZigZag instance = new ZigZag();

        int[][] testData= {{1, 2,  3,  4},
                           {5, 6,  7,  8},
                           {9,10, 11, 12}};

        int[] res = instance.printZMatrix(testData);
        for(int value: res)
            System.out.println(value+ ",");
    }
}
