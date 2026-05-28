import java.util.stream.IntStream;
public class NaturalNumber {
    private final int number;

    public NaturalNumber(int number) {
        if (number <= 0) {
            throw new IllegalArgumentException("You must supply a natural number (positive integer)");
        }
        this.number = number;
    }
    public Classification getClassification() {
        int suma = IntStream.rangeClosed(1, number / 2)
                           .filter(i -> number % i == 0)
                           .sum();
        if (suma == number) {
            return Classification.PERFECT;
        } else if (suma > number) {
            return Classification.ABUNDANT;
        } else {
            return Classification.DEFICIENT;
        }
    }
}