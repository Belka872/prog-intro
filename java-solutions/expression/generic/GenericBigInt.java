package expression.generic;


import expression.generic.ExpressionGeneric.GenericOperation;
import java.math.BigInteger;

public class GenericBigInt implements GenericOperation<BigInteger> {
    @Override
    public BigInteger add(BigInteger left, BigInteger right) {
        return left.add(right);
    }

    @Override
    public BigInteger divide(BigInteger left, BigInteger right) {
        return left.divide(right);
    }

    @Override
    public BigInteger multiply(BigInteger left, BigInteger right) {
        return left.multiply(right);
    }

    @Override
    public BigInteger subtract(BigInteger left, BigInteger right) {
        return left.subtract(right);
    }

    @Override
    public BigInteger negate(BigInteger left) {
        return left.negate();
    }

    @Override
    public BigInteger parseNumber(String s) {
        return new BigInteger(s);
    }

    @Override
    public BigInteger count(BigInteger left) {
        return BigInteger.valueOf(left.bitCount());
    }

    @Override
    public BigInteger min(BigInteger left, BigInteger right) {
        return left.min(right);
    }

    @Override
    public BigInteger max(BigInteger left, BigInteger right) {
        return left.max(right);
    }

    @Override
    public BigInteger convertToInt(int x) {
        return new BigInteger(String.valueOf(x));
    }
}
