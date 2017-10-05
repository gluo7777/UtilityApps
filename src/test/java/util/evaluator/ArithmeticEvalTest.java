package util.evaluator;

import enums.Operation;
import org.junit.Test;
import static org.junit.Assert.*;

public class ArithmeticEvalTest {
    @Test
    public void test_willOverFlowTrue(){
        int a,b;

        // addition
        a = Integer.MAX_VALUE; b= Integer.MAX_VALUE;
        assertTrue(ArithmeticEval.willOverFlow(a,b, Operation.ADD));
        a = Integer.MIN_VALUE; b= Integer.MIN_VALUE;
        assertTrue(ArithmeticEval.willOverFlow(a,b, Operation.ADD));

        // subtraction
        a = Integer.MAX_VALUE; b= Integer.MIN_VALUE;
        assertTrue(ArithmeticEval.willOverFlow(a,b, Operation.SUBTRACT));
        a = Integer.MIN_VALUE; b= Integer.MAX_VALUE;
        assertTrue(ArithmeticEval.willOverFlow(a,b, Operation.SUBTRACT));

        // multiplication
        a = Integer.MAX_VALUE; b= Integer.MAX_VALUE;
        assertTrue(ArithmeticEval.willOverFlow(a,b, Operation.MULTIPLY));
        a = Integer.MIN_VALUE; b= Integer.MIN_VALUE;
        assertTrue(ArithmeticEval.willOverFlow(a,b, Operation.MULTIPLY));
        a = Integer.MAX_VALUE; b= Integer.MIN_VALUE;
        assertTrue(ArithmeticEval.willOverFlow(a,b, Operation.MULTIPLY));
    }

    @Test
    public void test_willOverFlowFalse(){
        int a,b;

        // addition
        a = Integer.MAX_VALUE; b= 0;
        assertFalse(ArithmeticEval.willOverFlow(a,b, Operation.ADD));
        a = Integer.MIN_VALUE; b= 0;
        assertFalse(ArithmeticEval.willOverFlow(a,b, Operation.ADD));

        // subtraction
        a = 0; b= Integer.MAX_VALUE;
        assertFalse(ArithmeticEval.willOverFlow(a,b, Operation.SUBTRACT));
        a = 0; b= Integer.MAX_VALUE;
        assertFalse(ArithmeticEval.willOverFlow(a,b, Operation.SUBTRACT));

        // multiplication
        a = Integer.MAX_VALUE; b= 1;
        assertFalse(ArithmeticEval.willOverFlow(a,b, Operation.MULTIPLY));
        a = Integer.MIN_VALUE; b= 1;
        assertFalse(ArithmeticEval.willOverFlow(a,b, Operation.MULTIPLY));
    }
}
