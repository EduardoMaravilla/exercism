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
        generar();
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
    private void generar() {
        if (this.desiredLiters==this.bucketOneCap && this.startBucket.equals("two")){
            this.totalMoves=2;
            this.finalBucket = "one";
            this.otherBucket = bucketTwoCap;      
        }else if (this.desiredLiters==this.bucketTwoCap && this.startBucket.equals("one")){
            this.totalMoves=2;
            this.finalBucket = "two";
            this.otherBucket = bucketOneCap;      
        } else if (this.desiredLiters == this.bucketOneCap && this.startBucket.equals("one")) {
            this.totalMoves++;
            this.finalBucket = "one";
            this.otherBucket = 0;
        } else if (this.desiredLiters == this.bucketTwoCap && this.startBucket.equals("two")) {
            this.totalMoves++;
            this.finalBucket = "two";
            this.otherBucket = 0;
        } else {
            boolean condition = true;
            int oneBucket = 0;
            int twoBucket = 0;
            int num=0;            
            int mult=this.bucketOneCap*this.bucketTwoCap;      
            while (condition) {                
                switch (this.startBucket) {
                    case "one":
                        for (int i = 0; i < mult ; i=i+this.bucketOneCap) {
                            if (i%this.bucketTwoCap==this.desiredLiters) {
                                num=i;
                                condition=false;
                                break;
                            }
                        }
                        this.totalMoves=(num/this.bucketOneCap)*2 + ((num/this.bucketTwoCap)-1)*2;
                        oneBucket=num%this.bucketTwoCap;                        
                        break;
                    case "two":
                        for (int i = 0; i < mult ; i=i+this.bucketTwoCap) {
                            if (i%this.bucketOneCap==this.desiredLiters) {
                                num=i;
                                condition=false;
                                break;
                            }
                        }
                        this.totalMoves=(num/this.bucketOneCap)*2 + ((num/this.bucketTwoCap)-1)*2;
                        twoBucket=num % this.bucketOneCap;
                        break;    
                    default:
                        throw new AssertionError();
                }
                if (oneBucket==this.desiredLiters) {
                    this.finalBucket="one";
                    this.otherBucket=this.bucketTwoCap;                  
                }else if (twoBucket==this.desiredLiters) {
                    this.finalBucket="two";
                    this.otherBucket=this.bucketOneCap; 
                }  
            }
        }
    }
}
