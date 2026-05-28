public class EliudsEggs {
    public int eggCount(int number) {
        int egg = 0;
        while (number != 0) {
            egg += number % 2;
            number /= 2;
        }
        return egg;
    }
}