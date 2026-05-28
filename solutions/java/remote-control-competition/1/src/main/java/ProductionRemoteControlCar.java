import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

class ProductionRemoteControlCar implements RemoteControlCar, Comparable<ProductionRemoteControlCar>{
      private int distancia=0;
      private int numberOfVictories=0;
    public void drive() {
        distancia +=10;
    }
    public int getDistanceTravelled() {
        return distancia;
    }
    public int getNumberOfVictories() {
        return numberOfVictories;
    }

    public void setNumberOfVictories(int numberOfVictories) {
        this.numberOfVictories=numberOfVictories;
    }
    public int compareTo(ProductionRemoteControlCar other) {
        return Integer.compare(other.numberOfVictories, numberOfVictories);
    }
}