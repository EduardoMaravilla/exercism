import java.util.*;

class GottaSnatchEmAll {

    static Set<String> newCollection(List<String> cards) {
        return new HashSet<>(cards);
    }

    static boolean addCard(String card, Set<String> collection) {
       return collection.add(card);
    }

    static boolean canTrade(Set<String> myCollection, Set<String> theirCollection) {
        if (myCollection.isEmpty() || theirCollection.isEmpty()){
            return false;
        }
        int val=0;
        for (String their: theirCollection){
            for (String my:myCollection){
                if (their.equals(my)){
                    val++;
                }
            }
        }
        int myCollectionLong= myCollection.size();
        int theirCollectionLong= theirCollection.size();
        if (myCollectionLong == theirCollectionLong){
            return val < myCollectionLong;
        } else if (theirCollectionLong > myCollectionLong) {
            return val == myCollectionLong;
        }else {
            return false;
        }
    }

    static Set<String> commonCards(List<Set<String>> collections) {
        Set<String> allCards = allCards(collections);
        Set<String> commonCards = new HashSet<>(allCards);
        for (Set<String> collection : collections) {
            commonCards.retainAll(collection);
        }
        return commonCards;
    }

    static Set<String> allCards(List<Set<String>> collections) {
        Set<String> list = new HashSet<>();
        for (Set<String> listCards: collections){
            list.addAll(listCards);
        }
        return list;
    }
}