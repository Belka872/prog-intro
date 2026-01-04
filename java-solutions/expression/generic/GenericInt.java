package expression.generic;

import expression.exceptions.OverflowException;
import expression.exceptions.ZeroException;
import expression.generic.ExpressionGeneric.GenericOperation;

public class GenericInt implements GenericOperation<Integer> {
    @Override
    public Integer add(Integer left, Integer right) {
        return left + right;
    }

    @Override
    public Integer divide(Integer left, Integer right) {
        return left / right;
    }

    @Override
    public Integer multiply(Integer left, Integer right) {
        return left * right;
    }

    @Override
    public Integer subtract(Integer left, Integer right) {
        return left - right;
    }

    @Override
    public Integer negate(Integer left) {
        return -left;
    }

    @Override
    public Integer parseNumber(String s) {
        return Integer.parseInt(s);
    }

    @Override
    public Integer count(Integer left) {
        return Integer.bitCount(left);
    }

    @Override
    public Integer min(Integer left, Integer right) {
        if(left > right) {
            return right;
        }
        return left;
    }

    @Override
    public Integer max(Integer left, Integer right) {
        if(left > right) {
            return left;
        }
        return right;
    }

    @Override
    public Integer convertToInt(int left) {
        return left;
    }
}
