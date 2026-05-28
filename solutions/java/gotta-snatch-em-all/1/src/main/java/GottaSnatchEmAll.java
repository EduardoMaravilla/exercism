import java.util.*;

class GottaSnatchEmAll {

    static Set<String> newCollection(List<String> cards) {
        return new HashSet<>(cards);
    }

    static boolean addCard(String card, Set<String> collection) {
       if (collection.contains(card)){
           return false;
       }else {
           collection.add(card);
           return true;
       }
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
        Map<String, Integer> map = new HashMap<>();
        for (String key : allCards(collections)){
            map.put(key,0);
        }
        Set<String>  result = new HashSet<>();
        for (Set<String> listCards: collections){
            for (String card : listCards){
                map.put(card,map.get(card) + 1);
            }
        }
        int val = collections.size();
        for (Map.Entry<String, Integer> values : map.entrySet()){
            if (values.getValue() == val){
                result.add(values.getKey());
            }
        }
        return result;
    }

    static Set<String> allCards(List<Set<String>> collections) {
        Set<String> list = new HashSet<>();
        for (Set<String> listCards: collections){
            list.addAll(listCards);
        }
        return list;
    }
}