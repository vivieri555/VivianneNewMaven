package Vehicle;


import javafx.scene.control.Label;

public class Car extends Vehicle{
    private String vehicleType;
    private String hasRearCamera;
    private String gearbox;
    public Car() {}
    public Car(String brand, String model, Boolean loanable, String vehicleType, String hasRearCamera, String gearbox) {
        super(brand, model, loanable);
        this.vehicleType = vehicleType;
        this.hasRearCamera = hasRearCamera;
        this.gearbox = gearbox;
    }
    public String getVehicleType() {
        return vehicleType;
    }
    public void setVehicleType (String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getHasRearCamera() {
        return hasRearCamera;
    }
    public void setHasRearCamera(String hasRearCamera) {
        this.hasRearCamera = hasRearCamera;
    }

    public String getGearbox() {
        return gearbox;
    }
    public String setGearbox(String gearbox) {
        return gearbox;
    }
    @Override
    public void start(){
        super.start();
        Label label = new Label("Tryck på knappen för att starta");
        label.getText();
    }
}
