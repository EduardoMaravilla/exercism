import java.util.ArrayList;
import java.util.List;
public class BowlingGame {
    private final List<Integer> rolls = new ArrayList<>();
    private boolean hasBonusRoll = true;
    private int frames = 0;
    private int points = 0;
    private int square = 0;
    private int bonus2 = 0;
    private boolean strike = false;

    void roll(int pins) {
        rolls.add(pins);
        if (frames == 10 && !hasBonusRoll) throw new IllegalStateException("Cannot roll after game is over");
        if (frames == 10 && !strike) {
            bonus2++;
            if (bonus2 == 2) throw new IllegalStateException("Cannot roll after game is over");
        } else if (frames == 10) {
            bonus2++;
            if (pins < 10) {
                points = points + pins;
            } else if (points > 0) {
                points = points + pins;
            }
            if (points > 10) throw new IllegalStateException("Pin count exceeds pins on the lane");
            if (bonus2 == 3) throw new IllegalStateException("Cannot roll after game is over");
        }
        if (pins < 0) {
            throw new IllegalStateException("Negative roll is invalid");
        }
        if (pins > 10) throw new IllegalStateException("Pin count exceeds pins on the lane");
        if (pins == 10 && square == 0 && frames < 10) {
            frames++;
            strike = true;
        }
        if (pins < 10 && frames < 10) {
            points = points + pins;
            square++;
            if (points > 10) throw new IllegalStateException("Pin count exceeds pins on the lane");
            if (square == 2) {
                frames++;
                if (frames == 10 && points == 10) {
                    hasBonusRoll = true;
                    strike = false;
                } else if (frames == 10) {
                    hasBonusRoll = false;
                }
                points = 0;
                square = 0;
            }
        }
    }

    int score() {
        if (frames < 10 || (hasBonusRoll && bonus2 == 0 && !strike) || (hasBonusRoll && bonus2 < 2 && strike)) {
            throw new IllegalStateException("Score cannot be taken until the end of the game");
        }
        int sum = 0;
        for (Integer roll : rolls) {
            sum += roll;
        }
        if (sum == 0) {
            return 0;
        }
        int num = rolls.size();
        int value1 = 0;
        int value2 = 0;
        int value3 = 0;
        int squares = frames;
        sum = 0;
        for (int k = 0; k < num; k++) {
            if (squares == 1) {
                if ((num - k) == 2) {
                    sum = sum + rolls.get(k) + rolls.get(k + 1);
                    k = rolls.size();
                } else if ((num - k) == 3) {
                    sum = sum + rolls.get(k) + rolls.get(k + 1) + rolls.get(k + 2);
                    k = rolls.size();
                }
            }
            if (squares > 1) {
                value1 = rolls.get(k);
                value2 = rolls.get(k + 1);
                value3 = rolls.get(k + 2);
            }
            if (value1 == 10 && squares > 1) {
                sum = sum + value1 + value2 + value3;
                squares--;
            } else if ((value1 + value2) == 10 && squares > 1) {
                sum = sum + value1 + value2 + value3;
                k += 1;
                squares--;
            } else if ((value1 + value2) < 10 && squares > 1) {
                sum = sum + value1 + value2;
                k += 1;
                squares--;
            }
        }
        return sum;
    }
}