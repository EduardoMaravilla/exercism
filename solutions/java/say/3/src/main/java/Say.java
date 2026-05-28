public class Say {
    private final String[] basic = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};
    private final String[] decena = {"twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};
    private final String[] miles = {"thousand", "million", "billion"};

    public String say(long number) {
        if (number < 0 || number > 999_999_999_999L) {
            throw new IllegalArgumentException("Número fuera de rango");
        }

        if (number < 20) {
            return basic[(int) number];
        } else if (number < 100) {
            return (number % 10 == 0) ? decena[(int) (number / 10) - 2] : decena[(int) (number / 10) - 2] + "-" + basic[(int) (number % 10)];
        } else if (number < 1000) {
            return (number % 100 == 0) ? basic[(int) (number / 100)] + " hundred" : basic[(int) (number / 100)] + " hundred " + say(number % 100);
        } else {
            for (int i = miles.length - 1; i >= 0; i--) {
                long divisor = (long) Math.pow(10, (i + 1) * 3);
                if (number >= divisor) {
                    return (number % divisor == 0) ? say(number / divisor) + " " + miles[i] : say(number / divisor) + " " + miles[i] + " " + say(number % divisor);
                }
            }
            return ""; // Este caso no debería ocurrir si el número está en el rango adecuado
        }
    }
}