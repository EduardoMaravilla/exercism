class ArmstrongNumbers {

    boolean isArmstrongNumber(int numberToCheck) {
        double check=numberToCheck;
        double suma=0;
        String number=Integer.toString(numberToCheck);
        String[] num=number.split("");
        int tam=num.length;
        for(int i=0;i<tam;i++){
            int valor=Integer.parseInt(num[i]);
            suma=suma+ Math.pow(valor,tam);
        }
        if(suma==check){
            return true;
        }else{
            return false;
        }
    }
}
