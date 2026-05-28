import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Poker {
    private final List<String> hands;

    public Poker(List<String> hands) {
        this.hands = hands;
    }

    public List<String> getBestHands() {
        if (hands.size() <= 1) {
            return hands;
        }

        List<playOfCard> cardList = new ArrayList<>();
        for (String hand : hands) {
            cardList.add(new playOfCard(hand, handValue(hand)));
        }
        Comparator<playOfCard> byValue = Comparator.comparingInt(playOfCard::value);
        cardList.sort(byValue);
        int control = 0;
        for (int i = 0; i < (cardList.size() - 1); i++) {
            if (cardList.get(i).value() == cardList.get(i + 1).value()) {
                control++;
            } else {
                break;
            }
            if (cardList.size() == 2) {
                break;
            }
        }
        if (control == 0) {
            playOfCard Mano = cardList.get(0);
            String ganador = Mano.play();
            return Collections.singletonList(ganador);
        } else {

            List<playOfCard> ncardList = new ArrayList<>();
            for (int i = 0; i <= control; i++) {
                ncardList.add(cardList.get(i));
            }

            return analizar(ncardList);
        }
    }

    private static List<String> analizar(List<playOfCard> lista) {
        List<String> respuesta = new ArrayList<>();
        List<String> temporal = new ArrayList<>();
        int control = lista.get(0).value();        
        for (playOfCard playofcard : lista) {
            temporal.add(recode(playofcard.play()));
            respuesta.add(playofcard.play());
        }        
        if (control == 9 || control == 4) {
            for (int i = 0; i < (temporal.size() - 1); i++) {
                for (int j = 12; j >= 0; j = j - 3) {
                    String val1 = temporal.get(i).substring(j, j + 1);
                    String val2 = temporal.get(i + 1).substring(j, j + 1);
                    if (val1.compareTo(val2) < 0) {
                        respuesta.remove(0);
                        break;
                    } else if (val2.compareTo(val1) < 0) {
                        respuesta.remove(1);
                        break;
                    }
                }
            }
        } else if (control == 2 || control == 3) {
            int cont0 = 0;
            int cont1 = 0;
            for (int i = 0; i < (temporal.size() - 1); i++) {
                for (int j = 12; j >= 0; j = j - 3) {
                    String val1 = temporal.get(i).substring(j, j + 1);
                    String val2 = temporal.get(i + 1).substring(j, j + 1);
                    if (val1.compareTo(val2) < 0) {
                        cont0++;
                    } else if (val2.compareTo(val1) < 0) {
                        cont1++;
                    }
                }
                if (cont0 < cont1) {
                    respuesta.remove(1);
                } else if (cont1 < cont0) {
                    respuesta.remove(0);
                }
                cont0 = 0;
                cont1 = 0;

            }
        } else if (control == 1 || control == 5) {

            for (int i = 0; i < temporal.size(); i++) {
                String hayA = temporal.get(i).substring(12, 13);
                String hay2 = temporal.get(i).substring(0, 1);
                if (hayA.equals("O") && hay2.equals("C")) {
                    String valor = temporal.get(i);
                    valor = valor.replaceAll("O", "B");
                    temporal.set(i, valor);
                }
            }
            for (int i = 0; i < (temporal.size() - 1); i++) {
                for (int j = 12; j >= 0; j = j - 3) {
                    String val1 = temporal.get(i).substring(j, j + 1);
                    String val2 = temporal.get(i + 1).substring(j, j + 1);
                    if (val1.compareTo(val2) < 0) {
                        respuesta.remove(0);
                        break;
                    } else if (val2.compareTo(val1) < 0) {
                        respuesta.remove(1);
                        break;
                    }
                }
            }
        } else if (control == 8 || control == 7 || control == 6) {
            List<String> Ntemporal = new ArrayList<>();
            String char6 = "";
            String char1 = "";
            String char2 = "";
            String char3 = "";
            String char4 = "";
            int num0 = 0;
            int num1 = 0;
            int num2 = 0;
            int num3 = 0;
            int num4 = 0;


            for (String cad : temporal) {
                for (int j = 0; j < cad.length(); j = j + 3) {
                    char6 = cad.substring(j, j + 1);
                    if (j == 0) {
                        char1 = char6;
                        num1++;
                    } else if (!char1.equals(char6) && num0 == 0) {
                        char2 = char6;
                        num2++;
                        num0++;
                    } else if (!char1.equals(char6) && !char2.equals(char6) && num0 == 1) {
                        char3 = char6;
                        num3++;
                        num0++;
                    } else if (!char1.equals(char6) && !char2.equals(char6) && !char3.equals(char6) && num0 == 2) {
                        char4 = char6;
                        num4++;
                        num0++;
                    } else if (char1.equals(char6)) {
                        num1++;
                    } else if (char2.equals(char6)) {
                        num2++;
                    } else if (char3.equals(char6)) {
                        num3++;
                    } else if (char4.equals(char6)) {
                        num4++;
                    }
                }
                if (num1 == 3 || num2 == 3 || num3 == 3) {                    
                    if (num1 == 3) {
                        if (char2.compareTo(char3) < 0) {
                            Ntemporal.add(char1 + char3 + char2);
                        } else {
                            Ntemporal.add(char1 + char2 + char3);
                        }
                    } else if (num2 == 3) {
                        if (char1.compareTo(char3) < 0) {
                            Ntemporal.add(char2 + char3 + char1);
                        } else {
                            Ntemporal.add(char2 + char1 + char3);
                        }
                    } else if (num3 == 3) {
                        if (char1.compareTo(char2) < 0) {
                            Ntemporal.add(char3 + char2 + char1);
                        } else {
                            Ntemporal.add(char3 + char1 + char2);
                        }
                    }
                } else if (num1 == 2 && num2 == 2 || num1 == 2 && num3 == 2 || num2 == 2 && num3 == 2) {
                    if (num1 == 2 && num2 == 2) {
                        if (char1.compareTo(char2) < 0) {
                            Ntemporal.add(char2 + char1 + char3);
                        } else {
                            Ntemporal.add(char1 + char2 + char3);
                        }
                    } else if (num1 == 2 && num3 == 2) {
                        if (char1.compareTo(char3) < 0) {
                            Ntemporal.add(char3 + char1 + char2);
                        } else {
                            Ntemporal.add(char1 + char3 + char2);
                        }
                    } else if (num2 == 2 && num3 == 2) {
                        if (char2.compareTo(char3) < 0) {
                            Ntemporal.add(char3 + char2 + char1);
                        } else {
                            Ntemporal.add(char2 + char3 + char1);
                        }
                    }
                } else {
                    String[] vec1 = {char2, char3, char4};
                    Arrays.sort(vec1, 0, 3, null);
                    String[] vec2 = {char1, char3, char4};
                    Arrays.sort(vec2, 0, 3, null);
                    String[] vec3 = {char1, char2, char4};
                    Arrays.sort(vec3, 0, 3, null);
                    String[] vec4 = {char1, char2, char3};
                    Arrays.sort(vec4, 0, 3, null);
                    if (num1 == 2) {
                        Ntemporal.add(char1 + String.join("", vec1));
                    } else if (num2 == 2) {
                        Ntemporal.add(char2 + String.join("", vec2));
                    } else if (num3 == 2) {
                        Ntemporal.add(char3 + String.join("", vec3));
                    } else if (num4 == 2) {
                        Ntemporal.add(char4 + String.join("", vec4));
                    }

                }
                char1 = "";
                char2 = "";
                char3 = "";
                char4 = "";
                num0 = 0;
                num1 = 0;
                num2 = 0;
                num3 = 0;
                num4 = 0;
            }         

            for (int i = 0; i < (Ntemporal.size() - 1); i++) {
                for (int j = 0; j < Ntemporal.get(0).length(); j++) {
                    String val1 = Ntemporal.get(i).substring(j, j + 1);
                    String val2 = Ntemporal.get(i + 1).substring(j, j + 1);
                    if (val1.compareTo(val2) < 0) {
                        respuesta.remove(0);
                        break;
                    } else if (val2.compareTo(val1) < 0) {
                        respuesta.remove(1);
                        break;
                    }

                }
            }
        }
        return respuesta;
    }

    private record playOfCard(String play, int value) {
    }

    private static int handValue(String hand) {
        return typeHand(hand).ordinal();
    }

    private static boolean sameAll(String hand) {
        String[] val1 = hand.split(" ");
        String val2 = val1[0].substring(1, 2);
        int cont = 0;
        for (String s : val1) {
            if (s.substring(1, 2).equals(val2)) {
                cont++;
            }
        }
        return cont == 5;
    }

    private static String recode(String hand) {
        String[] searchArray = {"S", "H", "D", "C", "J", "Q", "K", "A", "10", "2", "3", "4", "5", "6", "7", "8", "9"};
        String[] replaceArray = {"W", "X", "Y", "Z", "L", "M", "N", "O", "K", "C", "D", "E", "F", "G", "H", "I", "J"};

        String card = hand;
        for (int i = 0; i < searchArray.length; i++) {
            card = card.replaceAll(searchArray[i], replaceArray[i]);
        }
        String[] cards = card.split(" ");
        Arrays.sort(cards, 0, 5);
        return String.join(" ", cards);
    }

    private static handCategory typeHand(String hand) {
        String card = recode(hand);
        String[] cards = card.split(" ");
        Arrays.sort(cards, 0, 5, null);
        handCategory type;

        StringBuilder cadena = new StringBuilder();
        for (String value : cards) {
            cadena.append(value.charAt(0));
        }
        int suma = 0;
        for (int j = 0; j < (cards.length - 1); j++) {
            char c = cards[j].charAt(0);
            char d = cards[j + 1].charAt(0);
            c = (char) (c + 1);
            if (c == d) {
                suma++;
            }
        }
        if (sameAll(card)) {
            //Flush
            type = handCategory.flush;
            //Royal_Flush
            if (cadena.toString().equals("KLMNO")) {
                type = handCategory.royal_flush;
                return type;
            }
            //Straight Flush
            if (cadena.toString().equals("CDEFO")) {
                type = handCategory.straight_flush;
                return type;
            }
            if (suma == 4) {
                type = handCategory.straight_flush;
            }
            return type;
        } else {
            String[] cartas1 = new String[cards.length];
            for (int i = 0; i < cartas1.length; i++) {
                cartas1[i] = cards[i].substring(0, 1);
            }
            int control = 0;
            String cad1 = "";
            String cad2 = "";
            String cad3 = "";
            String cad4 = "";
            String cad5 = "";
            for (int i = 0; i < cartas1.length; i++) {
                if (i == 0) {
                    cad1 = cartas1[i];
                } else {
                    boolean b = !cartas1[i - 1].equals(cartas1[i]);
                    if (b && control == 0) {
                        cad2 = cartas1[i];
                        control++;
                    } else if (b && !cad1.equals(cartas1[i]) && control == 1) {
                        cad3 = cartas1[i];
                        control++;
                    } else if (b && !cad1.equals(cartas1[i]) && !cad2.equals(cartas1[i]) && control == 2) {
                        cad4 = cartas1[i];
                        control++;
                    } else if (b && !cad1.equals(cartas1[i]) && !cad2.equals(cartas1[i]) && !cad3.equals(cartas1[i]) && control == 3) {
                        cad5 = cartas1[i];
                        control++;
                    }
                }
            }
            int con1 = 0;
            int con2 = 0;
            int con3 = 0;
            int con4 = 0;
            int con5 = 0;
            for (String s : cartas1) {
                if (cad1.equals(s)) {
                    con1++;
                } else if (cad2.equals(s)) {
                    con2++;
                } else if (cad3.equals(s)) {
                    con3++;
                } else if (cad4.equals(s)) {
                    con4++;
                } else if (cad5.equals(s)) {
                    con5++;
                }
            }
            // four_of_a_kind cards
            if (con1 == 4 || con2 == 4) {
                type = handCategory.four_of_a_kind;
                return type;
            }
            // FULL HOUSE
            if (con1 == 3 && con2 == 2 || con1 == 2 && con2 == 3) {
                type = handCategory.full_house;
                return type;
            }
            //Straight
            if (cadena.toString().equals("CDEFO")) {
                type = handCategory.straight;
                return type;
            } else if (suma == 4) {
                type = handCategory.straight;
                return type;
            }
            //three of a kind
            if (con1 == 3 || con2 == 3 || con3 == 3) {
                type = handCategory.three_of_a_kind;
                return type;
            }
            //Two pair
            if ((con1 == 2 && con2 == 2) || (con1 == 2 && con3 == 2) || (con2 == 2 && con3 == 2)) {
                type = handCategory.two_pairs;
                return type;
            }
            //One pair
            if (con1 == 2 || con2 == 2 || con3 == 2 || con4 == 2) {
                type = handCategory.one_pair;
                return type;
            }
            //High card
            if (con1 == 1 && con2 == 1 && con3 == 1 && con4 == 1 && con5 == 1) {
                type = handCategory.high_card;
                return type;
            }
            return handCategory.none;
        }
    }

    private static enum handCategory {
        royal_flush(10),
        straight_flush(9),
        four_of_a_kind(8),
        full_house(7),
        flush(6),
        straight(5),
        three_of_a_kind(4),
        two_pairs(3),
        one_pair(2),
        high_card(1),
        none(0);
        private final int value;
        handCategory(int value){
            this.value = value;
        }
        public int getValue() {
            return value;
        }
    }
}