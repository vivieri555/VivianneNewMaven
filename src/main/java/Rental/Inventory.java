package Rental;

import Vehicle.Vehicle;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
@XmlRootElement
public class Inventory {

    private ObservableList<Vehicle> vehicleList = FXCollections.observableArrayList();
    public Inventory(){}
    public Inventory(ObservableList<Vehicle> vehicleList) {
        this.vehicleList = vehicleList;
    }
    @XmlElement
    public ObservableList<Vehicle> getVehicleList(){
        return vehicleList;
    }
    public void addVehicle(Vehicle v){
        vehicleList.add(v);
    }
}