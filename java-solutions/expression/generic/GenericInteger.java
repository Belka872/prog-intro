package expression.generic;

import expression.generic.ExpressionGeneric.GenericOperation;
import expression.exceptions.OverflowException;
import expression.exceptions.ZeroException;

public class GenericInteger implements GenericOperation<Integer> {
    @Override
    public Integer add(Integer left, Integer right) {
        if (left > 0 && right > 0 && left > Integer.MAX_VALUE - right) {
            throw new OverflowException("overflow:" + left + "+" + right);
        }
        if (left < 0 && right < 0 && left < Integer.MIN_VALUE - right) {
            throw new OverflowException("overflow: " + left + "+" + right);
        }
        return left + right;
    }

    @Override
    public Integer divide(Integer left, Integer right) {
        if (right == 0) {
            throw new ZeroException("zero");
        }
        if (left == Integer.MIN_VALUE && right == -1) {
            throw new OverflowException("overflow: " + left + "/" + right);
        }
        return left / right;
    }

    @Override
    public Integer multiply(Integer left, Integer right) {
        if (left > 0 && right > 0 && left > Integer.MAX_VALUE / right) {
            throw new OverflowException("overflow: " + left + "/" + right);
        }
        if (left < 0 && right < 0 && left < Integer.MAX_VALUE / right) {
            throw new OverflowException("overflow: " + left + "/" + right);
        }
        if (left > 0 && right < 0 && right < Integer.MIN_VALUE / left) {
            throw new OverflowException("overflow: " + left + "/" + right);
        }
        if (left < 0 && right > 0 && left < Integer.MIN_VALUE / right) {
            throw new OverflowException("overflow: " + left + "/" + right);
        }
        return left * right;
    }

    @Override
    public Integer subtract(Integer left, Integer right) {
        if (left >= 0 && right < 0 && left > Integer.MAX_VALUE + right) {
            throw new OverflowException("overflow: " + left + "-" + right);
        }
        if (left <= 0 && right > 0 && left < Integer.MIN_VALUE + right) {
            throw new OverflowException("overflow: " + left + "-" + right);
        }
        return left - right;
    }

    @Override
    public Integer negate(Integer left) {
        if (left == Integer.MIN_VALUE) {
            throw new OverflowException("overflow: -" + left);
        }
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
