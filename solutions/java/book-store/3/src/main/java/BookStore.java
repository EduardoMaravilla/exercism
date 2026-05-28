import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class BookStore {
    private static final double BOOK_PRICE = 8.00;
    private static final double[] DISCOUNTS = { 0.00, 0.05, 0.10, 0.20, 0.25 };

    private Map<Integer,Long> values;

    public double calculateBasketCost(List<Integer> books) {
        if (books.size() == 0) return 0.0;
        int numberOfBooks = books.size();
        this.values = books.stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
        if (numberOfBooks == values.size()){
           return calculatePrice(numberOfBooks);
        }
        if (values.size() == 1){
            return values.get(books.get(0))*BOOK_PRICE;
        }
        List<Double> prices = calculatePrice();
        Collections.sort(prices);
        return prices.get(0);
    }
    private double calculatePrice(int differentBooks){
        return (differentBooks*BOOK_PRICE)*(1.00 - DISCOUNTS[differentBooks-1]);
    }

    private List<Double> calculatePrice(){
        List<Double> prices =  new ArrayList<>();
        int[] distinctBook = {1,2,3,4,5};
        double sum =0;
        for (int distinct: distinctBook){
            Map<Integer,Long> books = new HashMap<>(values);
            while (books.size() > 0){
                Set<Integer> typeBook= new HashSet<>(books.keySet());
                if (typeBook.size() > distinct){
                    for (int i = 0; i < (typeBook.size() - distinct); i++) {
                        long minCount = Collections.min(books.values());
                        Optional<Integer> keyToRemove = books.entrySet().stream()
                                .filter(entry -> entry.getValue() == minCount)
                                .map(Map.Entry::getKey)
                                .findFirst();
                        keyToRemove.ifPresent(typeBook::remove);
                    }
                }
                sum += calculatePrice(typeBook.size());
                for (int val: typeBook){
                    books.put(val, books.get(val)-1);
                    if (books.get(val) == 0){
                        books.remove(val);
                    }
                }
            }
            prices.add(sum);
            sum=0;
        }
        return prices;
    }
} 