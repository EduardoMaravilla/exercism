class NeedForSpeed {
    // TODO: define the constructor for the 'NeedForSpeed' class
    private int speed;
    private int batteryDrain;
    private int distanceDriven;
    private int batteryPercentage;
    
    
    public NeedForSpeed(int speed, int batteryDrain) {
        this.speed=speed;
        this.batteryDrain=batteryDrain;
        this.distanceDriven = 0;
        this.batteryPercentage = 100;
    }

    public boolean batteryDrained() {
        return this.batteryPercentage <= 0;
    }

    public int distanceDriven() {
        return this.distanceDriven;
    }

    public void drive() {
        if(this.batteryPercentage > 0){
          this.distanceDriven += this.speed;
        this.batteryPercentage -= this.batteryDrain;  
        }
        
        if (this.batteryPercentage < 0) {
            this.batteryPercentage = 0;
        }
    }

    public static NeedForSpeed nitro() {
        return new NeedForSpeed(50, 4);
    }
}

class RaceTrack {
    // TODO: define the constructor for the 'RaceTrack' class
    private int distance;
    public RaceTrack(int distance){
        this.distance=distance;
    }
    
    
    public boolean tryFinishTrack(NeedForSpeed car) {
         while (car.distanceDriven() < this.distance && !car.batteryDrained()) {
            car.drive();
        }
        if(car.distanceDriven()==distance){
            return true;
        }else{
            return false;
        }
        
    }
}