public class QueenAttackCalculator {
    private Queen queen1;
    private Queen queen2;

    public QueenAttackCalculator(Queen queen1, Queen queen2) {
        if ( queen1==null) throw new IllegalArgumentException("You must supply valid positions for both Queens.");
        if ( queen2==null) throw new IllegalArgumentException("You must supply valid positions for both Queens.");
        if(queen1.equals(queen2)) throw new IllegalArgumentException("Queens cannot occupy the same position.");
        this.queen1 = queen1;
        this.queen2 = queen2;
    }

    boolean canQueensAttackOneAnother(){
        return  (Math.abs(queen1.getRow() - queen2.getRow()) == Math.abs(queen1.getColumn() - queen2.getColumn())) || queen1.getRow() == queen2.getRow() || queen1.getColumn() == queen2.getColumn();
    }
}