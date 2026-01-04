import java.math.BigInteger;

public class SumBigIntegerOctal {
    public static void main(String[] args) {
        BigInteger sumNumber = BigInteger.ZERO;
        for (int i = 0; i < args.length; i++) {
            int left = 0;
            int right = 0;
            while (right < args[i].length()) {
                while (right < args[i].length() && !Character.isWhitespace(args[i].charAt(right))) {
                    right++;
                }
                if (left != right) {
                    if (args[i].charAt(right - 1) == 'o' || args[i].charAt(right - 1) == 'O') {
                        sumNumber = sumNumber.add(new BigInteger(args[i].substring(left, right - 1), 8));
                    } else {
                        sumNumber = sumNumber.add(new BigInteger(args[i].substring(left, right)));
                    }
                }
                while (right < args[i].length() && Character.isWhitespace(args[i].charAt(right))) {
                    right++;
                }
                left = right;
            }
        }
        System.out.println(sumNumber);
    }
}