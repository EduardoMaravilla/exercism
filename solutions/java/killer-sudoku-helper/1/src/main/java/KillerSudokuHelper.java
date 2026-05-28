import java.util.ArrayList;
import java.util.List;
public class KillerSudokuHelper {
    List<List<Integer>> combinationsInCage(Integer cageSum, Integer cageSize, List<Integer> exclude) {
        List<List<Integer>> combinations=new ArrayList<>();
        generateListCombination(cageSum,0,cageSize,1,exclude,new ArrayList<>(),combinations);
        return combinations;
    }
    List<List<Integer>> combinationsInCage(Integer cageSum, Integer cageSize) {        
        return combinationsInCage(cageSum,cageSize,new ArrayList<>());
    }
    private void generateListCombination(Integer cageSum, Integer stepSum, Integer cageSize, int nextNumber,List<Integer> exclude,List<Integer> combination, List<List<Integer>> combinations) {
        if (cageSize == 0) {
            if (cageSum.equals(stepSum)) {
                combinations.add(combination);
            }
            return;
        }
        for (int i = nextNumber; i <= 9; i++) {
            if( !exclude.contains(i)) {
                List<Integer> number = new ArrayList<>(combination);
                number.add(i);
                int newStepSum = stepSum + i;
                generateListCombination(cageSum, newStepSum, cageSize - 1, i + 1, exclude,number, combinations);
            }
        }
    }
}