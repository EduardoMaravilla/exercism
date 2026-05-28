public class Blackjack {

    public int parseCard(String card) {
        card=card.toLowerCase();
        int valorcarta;
        if(card=="ace"){
            valorcarta=11;
        }else if(card=="two"){
            valorcarta=2;
        }else if(card=="three"){
            valorcarta=3;
        }else if(card=="four"){
            valorcarta=4;
        }else if(card=="five"){
            valorcarta=5;
        }else if(card=="six"){
            valorcarta=6;
        }else if(card=="seven"){
            valorcarta=7;
        }else if(card=="eight"){
            valorcarta=8;
        }else if(card=="nine"){
            valorcarta=9;
        }else if(card=="ten"||card=="jack"||card=="queen"||card=="king"){
            valorcarta=10;
        }
        else if(card=="other"){
            valorcarta=0;
        }
        else{
            valorcarta=0;
        }
        return valorcarta;
    }

    public boolean isBlackjack(String card1, String card2) {
        int valor1=parseCard(card1);
        int valor2=parseCard(card2);
        int suma=valor1+valor2;
        if(suma==21){
            return true;
        }else {
            return false;
        }
    }

    public String largeHand(boolean isBlackjack, int dealerScore) {
        if(isBlackjack==true && dealerScore<=9){
            return "W";
        }else if(isBlackjack==true && dealerScore>=10){
           return "S";
        }else{
            return "P";
        }
    }

    public String smallHand(int handScore, int dealerScore) {
        if(handScore>=17){
            return "S"; 
        }else if(handScore<=11){
            return "H"; 
        }else if (12<=handScore && handScore<=16 && dealerScore>=7){
            return "H";
        }else{
            return "S";
        }
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
