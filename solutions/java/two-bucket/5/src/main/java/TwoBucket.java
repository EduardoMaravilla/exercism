import static java.lang.Math.*;

class TwoBucket {
    private int totalMoves;
    private int otherBucket;
    private String finalBucket;
    private final int bucketOneCap;
    private final int bucketTwoCap;
    private final int desiredLiters;
    private final String startBucket;

    TwoBucket(int bucketOneCap, int bucketTwoCap, int desiredLiters, String startBucket) {
        if ((bucketOneCap < desiredLiters && bucketTwoCap < desiredLiters)
            || desiredLiters % gcd(bucketOneCap, bucketTwoCap) != 0) {
            throw new UnreachableGoalException();
        }
        this.bucketOneCap = bucketOneCap;
        this.bucketTwoCap = bucketTwoCap;
        this.desiredLiters = desiredLiters;
        this.startBucket = startBucket;
    }

    public Result getResult() {
        BucketState result;
    if (startBucket.equals("one") && bucketOneCap == desiredLiters) {
        return new Result(1, "one", 0);
    }
    if (startBucket.equals("two") && bucketTwoCap == desiredLiters) {
        return new Result(1, "two", 0);
    }
    
    if (startBucket.equals("one") && bucketTwoCap == desiredLiters) {
        return new Result(2, "two", bucketOneCap);
    }
    if (startBucket.equals("two") && bucketOneCap == desiredLiters) {
        return new Result(2, "one", bucketTwoCap);
    }  
        

        
        if (startBucket.equals("one")) {
            result = simulate(bucketOneCap, bucketTwoCap, desiredLiters);
            finalBucket = result.bucketOne == desiredLiters ? "one" : "two";
            otherBucket = result.bucketOne == desiredLiters ? result.bucketTwo : result.bucketOne;
        } else {
            result = simulate(bucketTwoCap, bucketOneCap, desiredLiters);
            finalBucket = result.bucketOne == desiredLiters ? "two" : "one";
            otherBucket = result.bucketOne == desiredLiters ? result.bucketTwo : result.bucketOne;
        }
        totalMoves = result.moves;
        return new Result(totalMoves, finalBucket, otherBucket);
    }

    private record BucketState(int bucketOne, int bucketTwo, int moves) {}

    private BucketState simulate(int fromCap, int toCap, int goal) {
        int from = fromCap, to = 0, moves = 1;
        while (from != goal && to != goal) {
            int pour = Math.min(from, toCap - to);
            to += pour;
            from -= pour;
            moves++;

            if (from == goal || to == goal) break;

            if (from == 0) {
                from = fromCap;
                moves++;
            } else if (to == toCap) {
                to = 0;
                moves++;
            }
        }
        return new BucketState(from, to, moves);
    }
    private int gcd(int a, int b) {
    while (b != 0) {
        int temp = b;
        b = a % b;
        a = temp;
    }
    return a;
}
}
