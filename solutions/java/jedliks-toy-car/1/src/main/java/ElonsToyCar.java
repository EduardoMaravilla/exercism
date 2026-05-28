public class ElonsToyCar {
    private int bateria=100;
    private int distancia=0;
    public static ElonsToyCar buy() {
     return new ElonsToyCar();   
    }

    public String distanceDisplay() {
        
        String dis=Integer.toString(distancia);
        String texto1="Driven "+dis+" meters";
        return texto1;
    }

    public String batteryDisplay() {
        if(bateria>0){
            String dis2=Integer.toString(bateria);
            String texto2="Battery at "+ dis2 +"%";
            return texto2;
        }else{
            return "Battery empty";
        }
        
        
    }

    public void drive() {
        if(bateria>0){
           bateria=bateria-1;
           distancia=distancia+20; 
        }
        
    }
}
