package algorithms.slidingwindow;

import org.junit.Test;

public class SlidingWindowMedianV1Test {
    @Test
    public void test(){
        SlidingWindowMedianTreeSet instance =new SlidingWindowMedianTreeSet();
        int[] values ={-2147483648,-2147483648,2147483647,-2147483648,-2147483648,-2147483648,
                2147483647,2147483647,2147483647,2147483647,-2147483648,-2147483648,-2147483648};

        instance.medianSlidingWindow(values, 3);
    }
}
