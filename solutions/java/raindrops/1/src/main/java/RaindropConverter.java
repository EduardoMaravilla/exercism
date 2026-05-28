class RaindropConverter {

    String convert(int number) {
        String lluvia = "";
        if(number % 3 == 0){
           lluvia = "Pling";
        }
        if(number % 5 == 0){
           lluvia = lluvia + "Plang";
        }
        if(number % 7 == 0){
           lluvia = lluvia + "Plong";
        }
        if(lluvia.equals("")){
            lluvia = Integer.toString(number);
        }
        return lluvia;
    }
}

