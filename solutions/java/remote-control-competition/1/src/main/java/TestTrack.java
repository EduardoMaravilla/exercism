import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class TestTrack {
    public static void race(RemoteControlCar car) {
        car.drive();
    }
    public static List<ProductionRemoteControlCar> getRankedCars(List<ProductionRemoteControlCar> unsortedList) {    
        Collections.sort(unsortedList);
       return unsortedList;
    }
}