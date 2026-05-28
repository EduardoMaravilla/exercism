import java.util.ArrayList;
import java.util.List;

public class PiecingItTogether {

    public static JigsawInfo getCompleteInformation(JigsawInfo input) {
        Integer pieces = input.getPieces().orElse(0);
        Integer border = input.getBorder().orElse(0);
        Integer inside = input.getInside().orElse(0);
        Integer rows = input.getRows().orElse(0);
        Integer columns = input.getColumns().orElse(0);
        Double aspectRatio = input.getAspectRatio().orElse(0.0);
        String format = input.getFormat().orElse(null);

        // Contradictory: square but rows != columns
        if ("square".equalsIgnoreCase(format) && rows != 0 && columns != 0 && !rows.equals(columns)) {
            throw new IllegalArgumentException("Contradictory data");
        }

        // Case: pieces + aspectRatio
        if (pieces != 0 && aspectRatio != 0 && (rows == 0 || columns == 0)) {
            int[] ratio = decimalToFraction(aspectRatio);
            for (int i = 1; i <= pieces; i++) {
                int r = ratio[1] * i;
                int c = ratio[0] * i;
                if (r * c == pieces) {
                    rows = r;
                    columns = c;
                    break;
                }
            }
        }

        // Case: rows + format = square
        if (rows != 0 && "square".equalsIgnoreCase(format) && columns == 0) {
            columns = rows;
            aspectRatio = 1.0;
        }

        // Case: rows + aspectRatio
        if (rows != 0 && aspectRatio != 0 && columns == 0) {
            int[] ratio = decimalToFraction(aspectRatio);
            for (int i = 1; i <= 1000; i++) {
                if (ratio[1] * i == rows) {
                    columns = ratio[0] * i;
                    break;
                }
                if (ratio[1] * i > rows) {
                    throw new IllegalArgumentException("Aspect ratio incompatible with rows");
                }
            }
        }

        // Case: inside + aspectRatio (square only)
        if (inside != 0 && aspectRatio != 0 && aspectRatio == 1.0 && (rows == 0 || columns == 0)) {
            rows = sqrtExactly(inside) + 2;
            columns = rows;
        }

        // Case: pieces + border + format (portrait only)
        if (pieces != 0 && border != 0 && "portrait".equalsIgnoreCase(format)) {
            inside = pieces - border;
            List<int[]> possibilities = getMultiplicationsOfTwo(pieces);
            for (int[] pair : possibilities) {
                int r = pair[0], c = pair[1];
                int borderCandidate = r * 2 + (c - 2) * 2;
                if (borderCandidate == border && (double) c / r < 1.0) {
                    rows = r;
                    columns = c;
                    aspectRatio = (double) c / r;
                    break;
                }
            }
        }

        // Derive missing values
        if (rows != 0 && columns != 0) {
            if (pieces == 0) pieces = rows * columns;
            if (border == 0) border = rows * 2 + (columns - 2) * 2;
            if (inside == 0) inside = pieces - border;
            if (aspectRatio == 0) aspectRatio = (double) columns / rows;
            if (format == null) format = getFormat(aspectRatio);
        }

        // Final validation
        if (pieces == 0 || rows == 0 || columns == 0) {
            throw new IllegalArgumentException("Insufficient data");
        }

        return new JigsawInfo.Builder()
                .pieces(pieces)
                .border(border)
                .inside(inside)
                .rows(rows)
                .columns(columns)
                .aspectRatio(aspectRatio)
                .format(format)
                .build();
    }

    public static int sqrtExactly(int numero) {
        int sqrt = (int) Math.sqrt(numero);
        if (sqrt * sqrt != numero) {
            throw new ArithmeticException("No exact square root");
        }
        return sqrt;
    }

    public static int[] decimalToFraction(double decimal) {
        int numerator = 1, denominator = 0, prevNumerator = 0, prevDenominator = 1;
        double fraction = decimal;

        while (true) {
            int a = (int) Math.floor(fraction);
            int tempNumerator = numerator;
            numerator = a * numerator + prevNumerator;
            prevNumerator = tempNumerator;

            int tempDenominator = denominator;
            denominator = a * denominator + prevDenominator;
            prevDenominator = tempDenominator;

            double approx = (double) numerator / denominator;
            if (Math.abs(decimal - approx) < 1e-10) {
                return new int[]{numerator, denominator};
            }
            fraction = 1.0 / (fraction - a);
        }
    }

    public static String getFormat(double aspectRatio) {
        return aspectRatio < 1.0 ? "portrait" :
                aspectRatio == 1.0 ? "square" : "landscape";
    }

    public static List<int[]> getMultiplicationsOfTwo(int n) {
        List<int[]> result = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (n % i == 0) result.add(new int[]{i, n / i});
        }
        return result;
    }
}
