class ArmstrongNumbers {

    boolean isArmstrongNumber(int numberToCheck) {
        double suma=0;
        String number=Integer.toString(numberToCheck);
        String[] num=number.split("");
        int tam=num.length;
        for (String s : num) {
            int valor = Integer.parseInt(s);
            suma = suma + Math.pow(valor, tam);
        }
        return suma == (double) numberToCheck;
    }
}