

class RomanNumerals{
     private int num;
    RomanNumerals(int num){
        this.num=num;
    }

    public String getRomanNumeral(){
        String romano="";
        int control=num;
        while(control>=1000){
            romano += "M";
            control -= 1000;
        }
        while(control>=900){
            romano += "CM";
            control -= 900;
        }
        while(control>=500){
            romano += "D";
            control -= 500;
        }
        while(control>=400){
            romano += "CD";
            control -= 400;
        }
        while(control>=100){
            romano += "C";
            control -= 100;
        }
        while(control>=90){
            romano += "XC";
            control -= 90;
        }
        while(control>=50){
            romano += "L";
            control -= 50;
        }
        while(control>=40){
            romano += "XL";
            control -= 40;
        }
        while(control>=10){
            romano += "X";
            control -= 10;
        }
        while(control>=9){
            romano += "IX";
            control -= 9;
        }
        while(control>=5){
            romano += "V";
            control -= 5;
        }
        while(control>=4){
            romano += "IV";
            control -= 4;
        }
        while(control>=1){
            romano += "I";
            control -= 1;
        }
        return romano;
    }
}