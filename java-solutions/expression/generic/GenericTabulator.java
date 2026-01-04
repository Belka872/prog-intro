package expression.generic;

import expression.generic.ExpressionGeneric.ExpressionUnion;
import expression.generic.ExpressionGeneric.GenericOperation;

import java.util.Objects;

public class GenericTabulator implements Tabulator {
    @Override
    public Object[][][] tabulate(String mode, String expression, int x1, int x2, int y1, int y2, int z1, int z2) {
        GenericOperation<?> tableMode = switch (mode) {
            case "i" -> new GenericInteger();
            case "d" -> new GenericDouble();
            case "bi" -> new GenericBigInt();
            case "u" -> new GenericInt();
            case "s" -> new GenericShort();
            case "f" -> new GenericFloat();
            default -> null;
        };
        return makeTable(tableMode, expression, x1,x2,y1,y2,z1,z2);
    }
    public <T> Object[][][] makeTable(GenericOperation<T> mode, String expression, int x1, int x2, int y1, int y2, int z1, int z2) {
        ExpressionUnion<T> Expression = new ExpressionParser<T>(mode).parse(expression);
        Object[][][] table = new Object[x2-x1+1][y2-y1+1][z2-z1+1];
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[0].length; j++) {
                for (int k = 0; k < Objects.requireNonNull(table[0][0]).length; k++) {
                    T x = mode.convertToInt(x1 + i);
                    T y = mode.convertToInt(y1 + j);
                    T z = mode.convertToInt(z1 + k);
                    try {
                        table[i][j][k] = Expression.evaluate(x, y, z);
                    } catch (Exception e) {
                        table[i][j][k] = null;
                    }
                }
            }
        }
        return table;
    }
}
