public class NeedForSpeed {
    private int speed;
    private int batteryDrain;
    private int distance;
    private int battery;

    public NeedForSpeed(int speed, int batteryDrain) {
        this.speed = speed;
        this.batteryDrain = batteryDrain;
        this.distance = 0;
        this.battery = 100;
    }
    public boolean batteryDrained() {
       return !(this.battery > 0);
    }

    public int distanceDriven() {
        return this.distance;
    }
    public void drive() {
        if(battery > 0){
            this.distance += speed;
            this.battery -=batteryDrain;
        }
    }

    public static NeedForSpeed nitro() {
     return new NeedForSpeed(50,4);
    }
}
class RaceTrack{
    private int distance;
    public RaceTrack(int distance) {
        this.distance = distance;
    }
    public boolean tryFinishTrack(NeedForSpeed car) {
        while( car.distanceDriven() < this.distance && !car.batteryDrained()){
            car.drive();
        }
        return car.distanceDriven()>= this.distance;
    }
}
