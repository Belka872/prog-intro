package expression.generic;

import expression.generic.ExpressionGeneric.GenericOperation;

public class GenericShort implements GenericOperation<Short> {
    @Override
    public Short add(Short left, Short right) {
        return (short) (left + right);
    }

    @Override
    public Short divide(Short left, Short right) {
        return (short) (left / right);
    }

    @Override
    public Short multiply(Short left, Short right) {
        return (short) (left * right);
    }

    @Override
    public Short subtract(Short left, Short right) {
        return (short) (left - right);
    }

    @Override
    public Short negate(Short left) {
        return (short) (-left);
    }

    @Override
    public Short parseNumber(String s) {
        return Short.parseShort(s);
    }

    @Override
    public Short count(Short left) {
        return (short) Integer.bitCount(left & 0xFFFF);
    }

    @Override
    public Short min(Short left, Short right) {
        if(left > right) {
            return right;
        }
        return left;
    }

    @Override
    public Short max(Short left, Short right) {
        if(left > right) {
            return left;
        }
        return right;
    }

    @Override
    public Short convertToInt(int left) {
        return (short) left;
    }
}
