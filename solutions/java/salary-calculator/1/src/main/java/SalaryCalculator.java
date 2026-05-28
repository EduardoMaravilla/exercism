public class SalaryCalculator {
    public double multiplierPerDaysSkipped(int daysSkipped) {
        boolean expr = daysSkipped>5;
        double multi= expr ? 0.85 : 1.0;
        return multi;
    }

    public int multiplierPerProductsSold(int productsSold) {
        boolean expr1 = productsSold>20;
        int multi1= expr1 ? 13 : 10;
        return multi1;
    }

    public double bonusForProductSold(int productsSold) {
        double bonus=multiplierPerProductsSold(productsSold)*productsSold;
        return bonus;
    }

    public double finalSalary(int daysSkipped, int productsSold) {
        double sala=1000*multiplierPerDaysSkipped(daysSkipped)+bonusForProductSold(productsSold);
        boolean exp3= sala>2000;
        double salary= exp3 ? 2000:sala;
        return salary;
    } 
}
