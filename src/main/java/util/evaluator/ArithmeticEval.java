package util.evaluator;

import enums.Operation;

/**
 * Evaluates integer operations and conditions
 */
public class ArithmeticEval {
    private static final long MAX_INT = (long) Integer.MAX_VALUE;
    private static final long MIN_INT = (long) Integer.MIN_VALUE;

    /**
     * Performs an operation to check for overflow
     * @param a first integer
     * @param b second integer
     * @param op operation to be performed
     * @return if operation results in an overflow
     */
    public static boolean willOverFlow(int a, int b, Operation op) {
        long result;
        switch (op) {
            case ADD:
                result = (long) a + (long) b;
                break;
            case SUBTRACT:
                result = (long) a - (long) b;
                break;
            case MULTIPLY:
                result = (long) a * (long) b;
                break;
            default:
                result = (long) a / (long) b;
                break;
        }
        return result > MAX_INT || result < MIN_INT;
    }

    /**
     * checks overflow for all basic operations
     * @param a
     * @param b
     * @return
     */
    public static boolean willAnyOverFlow(int a, int b){
        for (Operation op : Operation.getOperations()){
            if(willOverFlow(a,b,op))
                return false;
        }
        return true;
    }
}
