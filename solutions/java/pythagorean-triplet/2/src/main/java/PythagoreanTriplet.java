import java.util.*;


public class PythagoreanTriplet {

    private int a;
    private int b;
    private int c;

    public PythagoreanTriplet(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }

    public int getC() {
        return c;
    }

    public static TripletBuilder makeTripletsList() {
        return new TripletBuilder();
    }

    public static class TripletBuilder {

        private int sum;
        private int factorLimit = 0;

        public TripletBuilder thatSumTo(int sum) {
            this.sum = sum;
            return this;
        }

        public TripletBuilder withFactorsLessThanOrEqualTo(int factorLimit) {
            this.factorLimit = factorLimit;
            return this;
        }

        public List<PythagoreanTriplet> build() {
            List<PythagoreanTriplet> triplets = new ArrayList<>();
            List<PythagoreanTriplet> triplets2 = new ArrayList<>();
            for (int a = 1; a < this.sum; a++) {
                int b = (this.sum * this.sum - 2 * this.sum * a) / (2 * (this.sum - a));
                int c = this.sum - a - b;
                if (factorLimit <= 0) {
                    if (a * a + b * b == c * c) {
                        if (a > 0 && b > 0 && c > 0) {
                            triplets.add(new PythagoreanTriplet(a, b, c));
                        }
                    }
                } else if (a * a + b * b == c * c && (a <= this.factorLimit && b <= this.factorLimit && c <= this.factorLimit) && a > 0 && b > 0 && c > 0)  {
                    triplets.add(new PythagoreanTriplet(a, b, c));
                }
            }            
            for (int i = 0; i < triplets.size()/2; i++) {                
                triplets2.add(triplets.get(i));
            }
            return triplets2;
        }
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.a;
        hash = 53 * hash + this.b;
        hash = 53 * hash + this.c;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PythagoreanTriplet other = (PythagoreanTriplet) obj;
        if (this.a != other.a) {
            return false;
        }
        if (this.b != other.b) {
            return false;
        }
        return this.c == other.c;
    }
 
    
}
