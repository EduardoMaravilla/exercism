public class CarsAssemble {

    public double productionRatePerHour(int speed) {
        double product=221;
        double respuesta=0;
        if(speed>10){
            speed=10;
        }
        if(speed>=1 && speed<=4){
            respuesta=product*speed;
            return respuesta;
        }else if(speed>=5 && speed<=8){
            respuesta=product*speed*0.9;
            return respuesta;
        }else if(speed==9){
            respuesta=product*speed*0.8;
            return respuesta;
        }else if(speed==10){
            respuesta=product*speed*0.77;
            return respuesta;
        }else{
            return respuesta;
        }
    }

    public int workingItemsPerMinute(int speed) {
        double product=3.68;
        double respuesta=0;
        if(speed>10){
            speed=10;
        }
        if(speed>=1 && speed<=4){
            respuesta=product*speed;
        }else if(speed>=5 && speed<=8){
            respuesta=product*speed*0.9;
        }else if(speed==9){
            respuesta=product*speed*0.8;
        }else if(speed==10){
            respuesta=product*speed*0.77;
        }
        return (int)respuesta;
    }
}

