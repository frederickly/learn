package algorithms.calculator;

import org.junit.Test;

public class BasicCalculatorTest {

    @Test
    public void test(){
        BasicCalculator cal= new BasicCalculator();
        int res = cal.calculate(" 2-1 + 2 ");
        System.out.println("result="+ res);
    }
}
