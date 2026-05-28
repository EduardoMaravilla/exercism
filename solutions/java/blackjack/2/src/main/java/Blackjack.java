public class Blackjack {

    public int parseCard(String card) {
        card = card.toLowerCase();
        int val;
        switch (card) {
            case "ace" -> val = 11;
            case "two" -> val = 2;
            case "three" -> val = 3;
            case "four" -> val = 4;
            case "five" -> val = 5;
            case "six" -> val = 6;
            case "seven" -> val = 7;
            case "eight" -> val = 8;
            case "nine" -> val = 9;
            case "ten", "queen", "jack", "king" -> val = 10;
            default -> val=0;
        }
        return val;
    }

    public boolean isBlackjack(String card1, String card2) {
        return parseCard(card1) + parseCard(card2) == 21;
    }

    public String largeHand(boolean isBlackjack, int dealerScore) {
        return isBlackjack ? dealerScore <=9 ? "W" : "S" : "P";
    }

    public String smallHand(int handScore, int dealerScore) {
        return handScore >= 17 ? "S": handScore <=11 ? "H" : dealerScore >= 7 ? "H": "S";
    }

    // FirstTurn returns the semi-optimal decision for the first turn, given the cards of the player and the dealer.
    // This function is already implemented and does not need to be edited. It pulls the other functions together in a
    // complete decision tree for the first turn.
    public String firstTurn(String card1, String card2, String dealerCard) {
        int handScore = parseCard(card1) + parseCard(card2);
        int dealerScore = parseCard(dealerCard);

        if (20 < handScore) {
            return largeHand(isBlackjack(card1, card2), dealerScore);
        } else {
            return smallHand(handScore, dealerScore);
        }
    }
}
