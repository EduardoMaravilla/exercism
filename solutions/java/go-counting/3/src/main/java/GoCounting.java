import java.awt.Point;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Arrays;

public class GoCounting{
    private String board;
    private String[] tab;
    private int alto;
    private int ancho;

    public GoCounting(String board){
        this.board = board;
        tab=board.split("\n");
        alto=tab.length;
        ancho=tab[0].length();
    }

    public Player getTerritoryOwner(int x, int y){
        if(x<0||x>=alto||y<0||y>=ancho){
            throw new IllegalArgumentException("Invalid coordinate");
        }
        String[] tab1=filtro(tab);
        boolean condicion=true;
        String[] tab2=filtroT(tab1);
        String[] tab3=filtroT(tab2);
        while (condicion) {
            if(Arrays.equals(tab3, tab2)){
                condicion=false;
            }else{
                tab2=filtroT(tab3);
                tab3=filtroT(tab2);
            }
        }
        String[] tab4=filtroD(tab3);
        String[] tab5=filtroD(tab4);
        condicion=true;
        while (condicion) {
            if(Arrays.equals(tab5, tab4)){
                condicion=false;
            }else{
                tab4=filtroD(tab5);
                tab5=filtroD(tab4);
            }
        }
        String val=tab5[y].substring(x,x+1);
        if(val.equals("S")){
            return Player.WHITE;
        }else if(val.equals("C")){
            return Player.BLACK;
        }else{
            return Player.NONE;
        }
    }

    public Set<Point> getTerritory(int w, int z){
        if(w<0||w>=alto||z<0||z>=ancho){
            throw new IllegalArgumentException("Invalid coordinate");
        }
        String[] tab1=filtro(tab);
        boolean condicion=true;
        String[] tab2=filtroT(tab1);
        String[] tab3=filtroT(tab2);
        while (condicion) {
            if(Arrays.equals(tab3, tab2)){
                condicion=false;
            }else{
                tab2=filtroT(tab3);
                tab3=filtroT(tab2);
            }
        }
        String[] tab4=filtroD(tab3);
        String[] tab5=filtroD(tab4);
        condicion=true;
        while (condicion) {
            if(Arrays.equals(tab5, tab4)){
                condicion=false;
            }else{
                tab4=filtroD(tab5);
                tab5=filtroD(tab4);
            }
        }
        Set<Point> territorio = new HashSet<>();

        String  val=tab5[z].substring(w,w+1);;
        if(val.equals("B")||val.equals("W")){
            return territorio;
        }
        for(int i=0;i<alto;i++){
            for(int j=0;j<ancho;j++){
                if(tab5[i].substring(j,j+1).equals(val)){
                    territorio.add(new Point(j,i));
                }
            }
        }
        Set<Point> filtroterritorio = new HashSet<>();
        int sumax=0;
        int sumay=0;
        for(Point punto:territorio){
            sumax=punto.x-w;
            sumay=punto.y-z;
            if(Math.abs(sumax)<2 && Math.abs(sumay)<2 ){
                filtroterritorio.add(new Point(punto.x,punto.y));
            }
        }
        return filtroterritorio;
    }
    public HashMap<Player, Set<Point>> getTerritories(){
        HashMap<Player, Set<Point>> territories = new HashMap<>();
        Set<Point> negroTer = new HashSet<>();
        Set<Point> blancTer = new HashSet<>();
        Set<Point> nadaTerr = new HashSet<>();
        if(ancho==1 && alto==1){
            nadaTerr.add(new Point(0,0));
            territories.put(Player.BLACK, negroTer);
            territories.put(Player.WHITE, blancTer);
            territories.put(Player.NONE, nadaTerr);
            return territories;
        }else if(ancho>1 && alto==1){
            for(int i=0;i<ancho;i++){
                String[] var=board.split("");
                if(i==0){
                    if(var[i].equals(" ")){
                        if(var[i+1].equals("B")){
                            negroTer.add(new Point(i,0));
                        }else if(var[i+1].equals("W")){
                            blancTer.add(new Point(i,0));
                        }
                    }
                }else if(i==(ancho-1)){
                    if(var[i].equals(" ")){
                        if(var[i-1].equals("B")){
                            negroTer.add(new Point(i,0));
                        }else if(var[i-1].equals("W")){
                            blancTer.add(new Point(i,0));
                        }
                    }
                }else{
                    if(var[i].equals(" ")){
                        if(var[i-1].equals("B") && var[i+1].equals("B")){
                            negroTer.add(new Point(i,0));
                        }else if(var[i-1].equals("W") && var[i+1].equals("W")){
                            blancTer.add(new Point(i,0));
                        }else{
                            nadaTerr.add(new Point(i,0));
                        }
                    }
                }
            }
            territories.put(Player.BLACK, negroTer);
            territories.put(Player.WHITE, blancTer);
            territories.put(Player.NONE, nadaTerr);
            return territories;
        }else{
            String[] tab1=filtro(tab);
            boolean condicion=true;
            String[] tab2=filtroT(tab1);
            String[] tab3=filtroT(tab2);
            while (condicion) {
                if(Arrays.equals(tab3, tab2)){
                    condicion=false;
                }else{
                    tab2=filtroT(tab3);
                    tab3=filtroT(tab2);
                }
            }
            String[] tab4=filtroD(tab3);
            String[] tab5=filtroD(tab4);
            condicion=true;
            while (condicion) {
                if(Arrays.equals(tab5, tab4)){
                    condicion=false;
                }else{
                    tab4=filtroD(tab5);
                    tab5=filtroD(tab4);
                }
            }
            for(int i=0;i<alto;i++){
                for(int j=0;j<ancho;j++){
                    if(tab5[i].charAt(j) == 'C'){
                        negroTer.add(new Point(j,i));
                    }else if(tab5[i].charAt(j) == 'S'){
                        blancTer.add(new Point(j,i));
                    }
                }
            }
            territories.put(Player.BLACK, negroTer);
            territories.put(Player.WHITE, blancTer);
            territories.put(Player.NONE, nadaTerr);
            return territories;
        }
    }
   
    private String[] filtro(String[] tablero){
        int alto=tablero.length;
        int ancho=tablero[0].length();
        String[] tablero2=new String[alto];
        String territo1="";
        String territo2="";
        String territo3="";
        String cadena="";
        for(int i=0;i<alto;i++){
            if(i==0){
                territo1=tablero[i];
                territo2=tablero[i+1];
            }else if(i==(alto-1)){
                territo1=tablero[i];
                territo2=tablero[i-1];
            }else{
                territo1=tablero[i];
                territo2=tablero[i-1];
                territo3=tablero[i+1];
            }
            String[] val1=territo1.split("");
            String[] val2=territo2.split("");
            String[] val3=territo3.split("");
            cadena="";
            for(int j=0;j<ancho;j++){
                switch (val1[j]) {
                    case " " -> {
                        if (i == 0 || i == (alto - 1)) {
                            if (j == 0) {
                                if (val1[j + 1].equals("B") && val2[j].equals("B")) {
                                    cadena = cadena + "C";
                                } else if (val1[j + 1].equals("W") && val2[j].equals("W")) {
                                    cadena = cadena + "S";
                                } else {
                                    cadena = cadena + "T";
                                }
                            } else if (j == (ancho - 1)) {
                                if (val1[j - 1].equals("B") && val2[j].equals("B")) {
                                    cadena = cadena + "C";
                                } else if (val1[j - 1].equals("W") && val2[j].equals("W")) {
                                    cadena = cadena + "S";
                                } else {
                                    cadena = cadena + "T";
                                }
                            } else {
                                if (val1[j - 1].equals("B") && val2[j].equals("B") && val1[j + 1].equals("B")) {
                                    cadena = cadena + "C";
                                } else if (val1[j - 1].equals("W") && val2[j].equals("W") && val1[j + 1].equals("W")) {
                                    cadena = cadena + "S";
                                } else if (val1[j - 1].equals("B") && val2[j].equals("B") && val1[j + 1].equals(" ") || val1[j - 1].equals(" ") && val2[j].equals("B") && val1[j + 1].equals("B") || val1[j - 1].equals("B") && val2[j].equals(" ") && val1[j + 1].equals("B")) {
                                    cadena = cadena + "D";
                                } else if (val1[j - 1].equals("W") && val2[j].equals("W") && val1[j + 1].equals(" ") || val1[j - 1].equals(" ") && val2[j].equals("W") && val1[j + 1].equals("W") || val1[j - 1].equals("W") && val2[j].equals(" ") && val1[j + 1].equals("W")) {
                                    cadena = cadena + "R";
                                } else {
                                    cadena = cadena + "T";
                                }
                            }
                        } else {
                            if (j == 0) {
                                if (val1[j + 1].equals("B") && val2[j].equals("B") && val3[j].equals("B")) {
                                    cadena = cadena + "C";
                                } else if (val1[j + 1].equals("W") && val2[j].equals("W") && val3[j].equals("W")) {
                                    cadena = cadena + "S";
                                } else if (val1[j + 1].equals("B") && val2[j].equals("B") && val3[j].equals(" ") || val1[j + 1].equals(" ") && val2[j].equals("B") && val3[j].equals("B") || val1[j + 1].equals("B") && val2[j].equals(" ") && val3[j].equals("B")) {
                                    cadena = cadena + "D";
                                } else if (val1[j + 1].equals("W") && val2[j].equals("W") && val3[j].equals(" ") || val1[j + 1].equals(" ") && val2[j].equals("W") && val3[j].equals("W") || val1[j + 1].equals("W") && val2[j].equals(" ") && val3[j].equals("W")) {
                                    cadena = cadena + "R";
                                } else {
                                    cadena = cadena + "T";
                                }
                            } else if (j == (ancho - 1)) {
                                if (val1[j - 1].equals("B") && val2[j].equals("B") && val3[j].equals("B")) {
                                    cadena = cadena + "C";
                                } else if (val1[j - 1].equals("W") && val2[j].equals("W") && val3[j].equals("W")) {
                                    cadena = cadena + "S";
                                } else if (val1[j - 1].equals("B") && val2[j].equals("B") && val3[j].equals(" ") || val1[j - 1].equals(" ") && val2[j].equals("B") && val3[j].equals("B") || val1[j - 1].equals("B") && val2[j].equals(" ") && val3[j].equals("B")) {
                                    cadena = cadena + "D";
                                } else if (val1[j - 1].equals("W") && val2[j].equals("W") && val3[j].equals(" ") || val1[j - 1].equals(" ") && val2[j].equals("W") && val3[j].equals("W") || val1[j - 1].equals("W") && val2[j].equals(" ") && val3[j].equals("W")) {
                                    cadena = cadena + "R";
                                } else {
                                    cadena = cadena + "T";
                                }
                            } else {
                                if (val1[j - 1].equals("B") && val2[j].equals("B") && val1[j + 1].equals("B") && val3[j].equals("B")) {
                                    cadena = cadena + "C";
                                } else if (val1[j - 1].equals("W") && val2[j].equals("W") && val1[j + 1].equals("W") && val3[j].equals("W")) {
                                    cadena = cadena + "S";
                                } else if (val1[j - 1].equals("B") && val2[j].equals("B") && val1[j + 1].equals("B") && val3[j].equals(" ") || val1[j - 1].equals("B") && val2[j].equals("B") && val1[j + 1].equals(" ") && val3[j].equals("B") || val1[j - 1].equals("B") && val2[j].equals(" ") && val1[j + 1].equals("B") && val3[j].equals("B") || val1[j - 1].equals(" ") && val2[j].equals("B") && val1[j + 1].equals("B") && val3[j].equals("B")) {
                                    cadena = cadena + "D";
                                } else if (val1[j - 1].equals("W") && val2[j].equals("W") && val1[j + 1].equals("W") && val3[j].equals(" ") || val1[j - 1].equals("W") && val2[j].equals("W") && val1[j + 1].equals(" ") && val3[j].equals("W") || val1[j - 1].equals("W") && val2[j].equals(" ") && val1[j + 1].equals("W") && val3[j].equals("W") || val1[j - 1].equals(" ") && val2[j].equals("W") && val1[j + 1].equals("W") && val3[j].equals("W")) {
                                    cadena = cadena + "R";
                                } else {
                                    cadena = cadena + "T";
                                }
                            }
                        }
                    }
                    case "B" -> cadena = cadena + "B";
                    case "W" -> cadena = cadena + "W";
                }
            }
            tablero2[i]=cadena;
        }
        return tablero2;

    }
    private String[] filtroT(String[] tablero){
        int alto=tablero.length;
        int ancho=tablero[0].length();
        String[] tablero3=new String[alto];
        if(alto==2){
            if(tablero[0].equals(tablero[1])){
                StringBuilder caden= new StringBuilder();
                String[] cad=tablero[0].split("");
                for (int i = 0; i <ancho; i++) {
                    if(cad[i].equals("T")){
                        if(i==0){
                            if(cad[i+1].equals("B")){
                                caden.append("C");
                            }else if(cad[i+1].equals("W")){
                                caden.append("S");
                            }else{
                                caden.append("T");
                            }
                        }else if(i==(ancho-1)){
                            if(cad[i-1].equals("B")){
                                caden.append("C");
                            }else if(cad[i-1].equals("W")){
                                caden.append("S");
                            }else{
                                caden.append("T");
                            }
                        }else{
                            caden.append(cad[i]);
                        }

                    }else{
                        caden.append(cad[i]);
                    }
                }
                tablero3[0]= caden.toString();
                tablero3[1]= caden.toString();
                return tablero3;
            }
        }
        String territo1="";
        String territo2="";
        String territo3="";
        StringBuilder cadena= new StringBuilder();
        for(int i=0;i<alto;i++){
            if(i==0){
                territo1=tablero[i];
                territo2=tablero[i+1];
            }else if(i==(alto-1)){
                territo1=tablero[i];
                territo2=tablero[i-1];
            }else{
                territo1=tablero[i];
                territo2=tablero[i-1];
                territo3=tablero[i+1];
            }
            String[] val1=territo1.split("");
            String[] val2=territo2.split("");
            String[] val3=territo3.split("");
            cadena = new StringBuilder();
            for(int j=0;j<ancho;j++){
                switch (val1[j]) {
                    case "T" -> {
                        if (i == 0 || i == (alto - 1)) {
                            if (j == 0) {
                                if ("BCD".contains(val1[j + 1]) && "BCD".contains(val2[j])) {
                                    cadena.append("C");
                                } else if ("WSR".contains(val1[j + 1]) && "WSR".contains(val2[j])) {
                                    cadena.append("S");
                                } else {
                                    cadena.append("T");
                                }
                            } else if (j == (ancho - 1)) {
                                if ("BCD".contains(val1[j - 1]) && "BCD".contains(val2[j])) {
                                    cadena.append("C");
                                } else if ("WSR".contains(val1[j - 1]) && "WSR".contains(val2[j])) {
                                    cadena.append("S");
                                } else {
                                    cadena.append("T");
                                }
                            } else {
                                if ("BCD".contains(val1[j - 1]) && "BCD".contains(val2[j]) && "BCD".contains(val1[j + 1])) {
                                    cadena.append("C");
                                } else if ("WSR".contains(val1[j - 1]) && "WSR".contains(val2[j]) && "WSR".contains(val1[j + 1])) {
                                    cadena.append("S");
                                } else if ("BCD".contains(val1[j - 1]) && "BCD".contains(val2[j]) && !("WSR".contains(val1[j + 1])) || "BCD".contains(val1[j - 1]) && !("WSR".contains(val2[j])) && "BCD".contains(val1[j + 1]) || !("WSR".contains(val1[j - 1])) && "BCD".contains(val2[j]) && "BCD".contains(val1[j + 1])) {
                                    cadena.append("D");
                                } else if ("WSR".contains(val1[j - 1]) && "WSR".contains(val2[j]) && !("BCD".contains(val1[j + 1])) || "WSR".contains(val1[j - 1]) && !("BCD".contains(val2[j])) && "WSR".contains(val1[j + 1]) || !("BCD".contains(val1[j - 1])) && "WSR".contains(val2[j]) && "WSR".contains(val1[j + 1])) {
                                    cadena.append("S");
                                } else {
                                    cadena.append("T");
                                }
                            }
                        } else {
                            if (j == 0) {
                                if ("BCD".contains(val1[j + 1]) && "BCD".contains(val2[j]) && "BCD".contains(val3[j])) {
                                    cadena.append("C");
                                } else if ("WSR".contains(val1[j + 1]) && "WSR".contains(val2[j]) && "WSR".contains(val3[j])) {
                                    cadena.append("S");
                                } else if ("BCD".contains(val1[j + 1]) && "BCD".contains(val2[j]) && !("WSR".contains(val3[j])) || "BCD".contains(val1[j + 1]) && !("WSR".contains(val2[j])) && "BCD".contains(val3[j]) || !("WSR".contains(val1[j + 1])) && "BCD".contains(val2[j]) && "BCD".contains(val3[j])) {
                                    cadena.append("D");
                                } else if ("WSR".contains(val1[j + 1]) && "WSR".contains(val2[j]) && !("BCD".contains(val3[j])) || "WSR".contains(val1[j + 1]) && !("BCD".contains(val2[j])) && "WSR".contains(val3[j]) || !("BCD".contains(val1[j + 1])) && "WSR".contains(val2[j]) && "WSR".contains(val3[j])) {
                                    cadena.append("S");
                                } else {
                                    cadena.append("T");
                                }
                            } else if (j == (ancho - 1)) {
                                if ("BCD".contains(val1[j - 1]) && "BCD".contains(val2[j]) && "BCD".contains(val3[j])) {
                                    cadena.append("C");
                                } else if ("WSR".contains(val1[j - 1]) && "WSR".contains(val2[j]) && "WSR".contains(val3[j])) {
                                    cadena.append("S");
                                } else if ("BCD".contains(val1[j - 1]) && "BCD".contains(val2[j]) && !("WSR".contains(val3[j])) || "BCD".contains(val1[j - 1]) && !("WSR".contains(val2[j])) && "BCD".contains(val3[j]) || !("WSR".contains(val1[j - 1])) && "BCD".contains(val2[j]) && "BCD".contains(val3[j])) {
                                    cadena.append("D");
                                } else if ("WSR".contains(val1[j - 1]) && "WSR".contains(val2[j]) && !("BCD".contains(val3[j])) || "WSR".contains(val1[j - 1]) && !("BCD".contains(val2[j])) && "WSR".contains(val3[j]) || !("BCD".contains(val1[j - 1])) && "WSR".contains(val2[j]) && "WSR".contains(val3[j])) {
                                    cadena.append("S");
                                } else {
                                    cadena.append("T");
                                }
                            } else {
                                if ("BCD".contains(val1[j - 1]) && "BCD".contains(val2[j]) && "BCD".contains(val1[j + 1]) && "BCD".contains(val3[j])) {
                                    cadena.append("C");
                                } else if ("WSR".contains(val1[j - 1]) && "WSR".contains(val2[j]) && "WSR".contains(val1[j + 1]) && "WSR".contains(val3[j])) {
                                    cadena.append("S");
                                } else if ("BCD".contains(val1[j - 1]) && "BCD".contains(val2[j]) && "BCD".contains(val1[j + 1]) && !("WSR".contains(val3[j])) || "BCD".contains(val1[j - 1]) && "BCD".contains(val2[j]) && !("WSR".contains(val1[j + 1])) && "BCD".contains(val3[j]) || "BCD".contains(val1[j - 1]) && !("WSR".contains(val2[j])) && "BCD".contains(val1[j + 1]) && "BCD".contains(val3[j]) || !("WSR".contains(val1[j - 1])) && "BCD".contains(val2[j]) && "BCD".contains(val1[j + 1]) && "BCD".contains(val3[j])) {
                                    cadena.append("D");
                                } else if ("WSR".contains(val1[j - 1]) && "WSR".contains(val2[j]) && "WSR".contains(val1[j + 1]) && !("BCD".contains(val3[j])) || "WSR".contains(val1[j - 1]) && "WSR".contains(val2[j]) && !("BCD".contains(val1[j + 1])) && "WSR".contains(val3[j]) || "WSR".contains(val1[j - 1]) && !("BCD".contains(val2[j])) && "WSR".contains(val1[j + 1]) && "WSR".contains(val3[j]) || !("BCD".contains(val1[j - 1])) && "WSR".contains(val2[j]) && "WSR".contains(val1[j + 1]) && "WSR".contains(val3[j])) {
                                    cadena.append("R");
                                } else {
                                    cadena.append("T");
                                }
                            }
                        }
                    }
                    case "B" -> cadena.append("B");
                    case "W" -> cadena.append("W");
                    case "C" -> cadena.append("C");
                    case "S" -> cadena.append("S");
                    case "D" -> cadena.append("D");
                    case "R" -> cadena.append("R");
                }
            }
            tablero3[i]= cadena.toString();
        }
        return tablero3;
    }
    private String[] filtroD(String[] tablero){
        int alto=tablero.length;
        int ancho=tablero[0].length();
        String[] tablero3=new String[alto];
        String territo1="";
        String territo2="";
        String territo3="";
        StringBuilder cadena= new StringBuilder();
        for(int i=0;i<alto;i++){
            if(i==0){
                territo1=tablero[i];
                territo2=tablero[i+1];
            }else if(i==(alto-1)){
                territo1=tablero[i];
                territo2=tablero[i-1];
            }else{
                territo1=tablero[i];
                territo2=tablero[i-1];
                territo3=tablero[i+1];
            }
            String[] val1=territo1.split("");
            String[] val2=territo2.split("");
            String[] val3=territo3.split("");
            cadena = new StringBuilder();
            for(int j=0;j<ancho;j++){
                switch (val1[j]) {
                    case "D", "R" -> {
                        if (i == 0 || i == (alto - 1)) {
                            if (j == 0) {
                                if ("BCD".contains(val1[j + 1]) && "BCD".contains(val2[j])) {
                                    cadena.append("C");
                                } else if ("WSR".contains(val1[j + 1]) && "WSR".contains(val2[j])) {
                                    cadena.append("S");
                                } else {
                                    cadena.append("T");
                                }
                            } else if (j == (ancho - 1)) {
                                if ("BCD".contains(val1[j - 1]) && "BCD".contains(val2[j])) {
                                    cadena.append("C");
                                } else if ("WSR".contains(val1[j - 1]) && "WSR".contains(val2[j])) {
                                    cadena.append("S");
                                } else {
                                    cadena.append("T");
                                }
                            } else {
                                if ("BCD".contains(val1[j - 1]) && "BCD".contains(val2[j]) && "BCD".contains(val1[j + 1])) {
                                    cadena.append("C");
                                } else if ("WSR".contains(val1[j - 1]) && "WSR".contains(val2[j]) && "WSR".contains(val1[j + 1])) {
                                    cadena.append("S");
                                } else {
                                    cadena.append("T");
                                }
                            }
                        } else {
                            if (j == 0) {
                                if ("BCD".contains(val1[j + 1]) && "BCD".contains(val2[j]) && "BCD".contains(val3[j])) {
                                    cadena.append("C");
                                } else if ("WSR".contains(val1[j + 1]) && "WSR".contains(val2[j]) && "WSR".contains(val3[j])) {
                                    cadena.append("S");
                                } else {
                                    cadena.append("T");
                                }
                            } else if (j == (ancho - 1)) {
                                if ("BCD".contains(val1[j - 1]) && "BCD".contains(val2[j]) && "BCD".contains(val3[j])) {
                                    cadena.append("C");
                                } else if ("WSR".contains(val1[j - 1]) && "WSR".contains(val2[j]) && "WSR".contains(val3[j])) {
                                    cadena.append("S");
                                } else {
                                    cadena.append("T");
                                }
                            } else {
                                if ("BCD".contains(val1[j - 1]) && "BCD".contains(val2[j]) && "BCD".contains(val1[j + 1]) && "BCD".contains(val3[j])) {
                                    cadena.append("C");
                                } else if ("WSR".contains(val1[j - 1]) && "WSR".contains(val2[j]) && "WSR".contains(val1[j + 1]) && "WSR".contains(val3[j])) {
                                    cadena.append("S");
                                } else {
                                    cadena.append("T");
                                }
                            }
                        }
                    }
                    case "B" -> cadena.append("B");
                    case "W" -> cadena.append("W");
                    case "C" -> cadena.append("C");
                    case "S" -> cadena.append("S");
                    case "T" -> cadena.append("T");
                }
            }
            tablero3[i]= cadena.toString();
        }
        return tablero3;
    }
}