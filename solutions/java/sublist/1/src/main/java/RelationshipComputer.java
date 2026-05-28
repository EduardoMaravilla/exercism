import java.util.List;

class RelationshipComputer<T> {
    Relationship computeRelationship(List<T> firstList, List<T> secondList) {
        if (firstList.equals(secondList)) {
            return Relationship.EQUAL;
        } else if (isSublist(firstList, secondList)) {
            return Relationship.SUBLIST;
        } else if (isSublist(secondList, firstList)) {
            return Relationship.SUPERLIST;
        }
        return Relationship.UNEQUAL;
    }
    
    private boolean isSublist(List<T> firstList, List<T> secondList) {
        int firstSize = firstList.size();
        int secondSize = secondList.size();
        for (int i = 0; i <= secondSize - firstSize; i++) {
            if (secondList.subList(i, i + firstSize).equals(firstList)) {
                return true;
            }
        }
        return false;
    }
}
