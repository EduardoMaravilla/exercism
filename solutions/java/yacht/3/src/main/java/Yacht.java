import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
class Yacht {
    private final YachtCategory yachtCategory;
    private final int[] dice;
    private final Map<Integer, Long> count;
    Yacht(int[] dice, YachtCategory yachtCategory) {
        this.yachtCategory = yachtCategory;
        this.dice = Arrays.copyOf(dice, dice.length);
        Arrays.sort(this.dice);
        this.count = Arrays.stream(this.dice).boxed().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }
    int score() {
        return switch (yachtCategory) {
            case YACHT -> Arrays.stream(dice).allMatch(d -> d == dice[0]) ? 50 : 0;
            case ONES -> sumOf(1);
            case TWOS -> sumOf(2);
            case THREES -> sumOf(3);
            case FOURS -> sumOf(4);
            case FIVES -> sumOf(5);
            case SIXES -> sumOf(6);
            case FULL_HOUSE -> (count.containsValue(2L) && count.containsValue(3L)) ? Arrays.stream(dice).sum() : 0;
            case FOUR_OF_A_KIND ->
                    Arrays.stream(dice).filter(d -> count.get(d) >= 4).map(d -> d * 4).findFirst().orElse(0);
            case LITTLE_STRAIGHT -> isStraight() && dice[0] == 1 ? 30 : 0;
            case BIG_STRAIGHT -> isStraight() && dice[0] == 2 ? 30 : 0;
            case CHOICE -> Arrays.stream(dice).sum();
            default -> 0;
        };
    }
    private int sumOf(int value) {
        return Arrays.stream(dice).filter(d -> d == value).sum();
    }
    private boolean isStraight() {
        return IntStream.range(0, dice.length - 1).allMatch(i -> dice[i] + 1 == dice[i + 1]);
    }
}