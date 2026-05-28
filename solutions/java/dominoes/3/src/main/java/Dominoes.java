import java.util.List;
import java.util.ArrayList;

public class Dominoes {

    public List<Domino> formChain(List<Domino> inputDominoes) throws ChainNotFoundException {
        if (inputDominoes.isEmpty()) return inputDominoes;
        else if (inputDominoes.size() == 1) {
            if (inputDominoes.get(0).getRight() == inputDominoes.get(0).getLeft()) return inputDominoes;
            else throw new ChainNotFoundException("No domino chain found.");
        }
        List<Domino> dominoChain = parcialChain(inputDominoes, true);
        if (dominoChain.size() == inputDominoes.size()) return dominoChain;

        List<Domino> residuo = parcialChain(inputDominoes, false);
        List<List<Domino>> listOtherChains = new ArrayList<>();
        while (residuo.size() > 0) {
            listOtherChains.add(parcialChain(residuo, true));
            residuo = parcialChain(residuo, false);
        }
        for (List<Domino> chain : listOtherChains) {
            dominoChain = connectChain(dominoChain, chain);
        }
        if (dominoChain.size() != inputDominoes.size()) throw new ChainNotFoundException("No domino chain found.");
        return dominoChain;
    }

    private List<Domino> parcialChain(List<Domino> listOfDominoes, boolean dom) {
        List<Domino> dominoChain = new ArrayList<>();
        List<Domino> temp = new ArrayList<>(listOfDominoes);
        dominoChain.add(listOfDominoes.get(0));
        temp.remove(0);
        Domino first = dominoChain.get(0);
        while (temp.size() > 0) {
            int control = 0;
            for (Domino domino : temp) {
                if (first.getRight() == domino.getLeft()) {
                    dominoChain.add(domino);
                    break;
                } else if (first.getRight() == domino.getRight()) {
                    dominoChain.add(new Domino(domino.getRight(), domino.getLeft()));
                    break;
                }
                control++;
            }
            if (control + 1 > temp.size()) break;
            temp.remove(control);
            first = dominoChain.get(dominoChain.size() - 1);
        }
        if (dom) return dominoChain;
        else return temp;
    }

    private List<Domino> connectChain(List<Domino> root, List<Domino> second) {
        List<Domino> connectChain = new ArrayList<>(root);
        if (second.get(0).getLeft() == second.get(second.size() - 1).getRight()) {
            for (int i = 0; i < root.size() - 1; i++) {
                if (connectChain.get(i).getRight() == second.get(0).getLeft()) {
                    for (Domino dom : second) {
                        connectChain.add(i + 1, dom);
                        i++;
                    }
                    break;
                }
            }
        }
        return connectChain;
    }
}