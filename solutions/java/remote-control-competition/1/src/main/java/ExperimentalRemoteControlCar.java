public class ExperimentalRemoteControlCar implements RemoteControlCar{
       private int distancia;
    public void drive() {
        distancia +=20;
    }

    public int getDistanceTravelled() {
        return distancia;
    }
}