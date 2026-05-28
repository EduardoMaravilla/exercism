import java.util.stream.IntStream;
public class SquareRoot {
    public int squareRoot(int radicand) {
        return IntStream.range(1, radicand).filter(n -> n * n == radicand).findFirst().orElse(1);
    }
}