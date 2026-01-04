public class Sum {
    public static void main(String[] args) {
        int sumNumber = 0;
        for (int i = 0; i < args.length; i++) {
            int left = 0;
            int right = 0;
            while (right < args[i].length()) {
                while (right < args[i].length() && !Character.isWhitespace(args[i].charAt(right))) {
                    right++;
                }
                if (left != right) {
                    String currentNumber = args[i].substring(left, right);
                    sumNumber += Integer.parseInt(currentNumber);
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