class TwoBucket {
    private int totalMoves;
    private int otherBucket;
    private String finalBucket;
    private final int bucketOneCap;
    private final int bucketTwoCap;
    private final int desiredLiters;
    private final String startBucket;

    TwoBucket(int bucketOneCap, int bucketTwoCap, int desiredLiters, String startBucket) {
        this.bucketOneCap = bucketOneCap;
        this.bucketTwoCap = bucketTwoCap;
        this.desiredLiters = desiredLiters;
        this.startBucket = startBucket;
        generate();
    }

    int getTotalMoves() {
        return this.totalMoves;
    }

    String getFinalBucket() {
        return finalBucket;
    }

    int getOtherBucket() {
        return this.otherBucket;
    }

    private void generate() {
        if (this.desiredLiters == this.bucketOneCap) {
            this.finalBucket = "one";
            if (this.startBucket.equals("one")){
                this.totalMoves++;
                this.otherBucket = 0;
            }else {
                this.totalMoves = 2;
                this.otherBucket = bucketTwoCap;
            }            
        } else if (this.desiredLiters == this.bucketTwoCap) {
            this.finalBucket = "two";
            if(this.startBucket.equals("one")){
                this.totalMoves = 2;
                this.otherBucket = bucketOneCap;
            }else {
                this.totalMoves++;
                this.otherBucket = 0;
            }            
        }else {
            int num = 0;
            switch (this.startBucket) {
                case "one" -> {
                    num = mCM(this.bucketOneCap, this.bucketTwoCap);
                }
                case "two" -> {
                    num = mCM(this.bucketTwoCap, this.bucketOneCap);
                }
                default -> throw new AssertionError();
            }
            this.totalMoves = (num / this.bucketOneCap) * 2 + ((num / this.bucketTwoCap) - 1) * 2;
            if ((num % this.bucketTwoCap) == this.desiredLiters) {
                this.finalBucket = "one";
                this.otherBucket = this.bucketTwoCap;
            } else if ((num % this.bucketOneCap) == this.desiredLiters) {
                this.finalBucket = "two";
                this.otherBucket = this.bucketOneCap;
            }
        }
    }
    private int mCM(int bucketOneCap, int bucketTwoCap) {
        for (int i = 0; i < (bucketOneCap * bucketTwoCap); i = i + bucketOneCap) {
            if (i % bucketTwoCap == this.desiredLiters) {
                return i;
            }
        }
        return 0;
    }
}