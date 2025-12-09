package Rental;

import java.util.ArrayList;
import java.util.Scanner;
import Vehicle.*;

public class RentalService implements PricePolicy{
    Inventory inventory;
    public RentalService(){}
    public RentalService(Inventory inventory){
        this.inventory = inventory;
    }
    //lägga in rentals i listan
    public ArrayList<Rental> rentals = new ArrayList<Rental>();
    public void add(Rental rental){
        rentals.add(rental);
    }
    public void sum(){
        double sum = 0;
        for(Rental cost: rentals){
            System.out.println("Intäkter: " + cost.getCost());
            sum = sum + cost.getCost();
        } System.out.println("Summan av intäkterna: " + sum + " kr");
    }
    public Vehicle searchCar(String search) {
        for (Vehicle car : inventory.getVehicleList()) {
            if (car.getBrand().contains(search) || car.getModel().contains(search)) {
                return car;
            }
        }
        return null;
    }
    @Override
    public double cost(int days) {
        return 1000 * days;
    }
    public void listRental(){
        for (Rental rental : rentals){
            System.out.println("Uthyrningens uppgifter:");
            System.out.println(rental.member.getName() + ", bilen: " + rental.getVehicle() + ", " + rental.getRentalDays() + " dagar, Kostnad: " + rental.getCost());
        }
    }
    public void terminateRental(String name){
        for(Rental rental: rentals){
            if(rental.getMember().equals(name)){
                System.out.println("Avslutar bokning på " + rental.getMember().getName());
                delete(rental);
            }
        } System.out.println("Avslutar bokningen");
    }
    public void delete(Rental rental) {
        {
            rentals.remove(rental);
        }
    }
    @Override
    public double getDiscountedCost(Rental rental) {
        double discountedCost = 0;
        if (rental.member.getStatus().equalsIgnoreCase("Premium")) {
            System.out.println("Medlemmen kan få rabatt 100 kr på varje uthyrning");
            discountedCost = cost(rental.getRentalDays()) -100;
        }
        else{
            return cost(rental.getRentalDays());
        }
        return discountedCost;
    }
    public void cars(){
        Scanner input = new Scanner(System.in);
        System.out.println("Välj 1 om du vill söka bil efter varumärke eller brand");
        System.out.println("Välj 2 om du vill lista elektriska bilar");
        System.out.println("Välj 3 om du vill lista familjebilar");
        System.out.println("Välj 4 om du vill lista stadsbilar");
        System.out.println("Välj 5 om du vill se tillgängliga bilar");
        String answer = input.nextLine();
        switch (answer) {
            case "1":
                System.out.println("Vilket bilmärke eller modell vill du söka på");
                String userAnswer = input.nextLine();
                Vehicle search = searchCar(userAnswer);
                if(search == null){
                    System.out.println("Bilen finns inte");
                }
                else{
                    System.out.println("Bilinfo: " + search.getBrand() + ", " + search.getModel() + ", " + search.isLoanable() + ", " + search);
                }
                break;
            case "2":
                System.out.println("De elektriska bilarna som finns:");
                for(Vehicle vehicle: inventory.getVehicleList()) {
                    if(vehicle instanceof ElectricCar){
                        System.out.println(vehicle.getBrand() + ", Modell: " + vehicle.getModel() + ", Batterylevel: "
                                + ((ElectricCar)vehicle).getBatteryLevel() + ", Dörrar: " + ((ElectricCar) vehicle).getDoors());
                        vehicle.start();
                    }
                }
                break;
            case "3":
                System.out.println("Familjebilarna som finns:");
                for(Vehicle vehicle: inventory.getVehicleList()){
                    if(vehicle instanceof FamilyCar){
                        System.out.println(vehicle.getBrand() + ", Modell: " + vehicle.getModel() +", Gearbox: " + ((FamilyCar)vehicle).getGearbox()+
                                ", Backkamera: " + ((FamilyCar) vehicle).isHasRearCamera());
                        vehicle.start();
                    }
                }
                break;
            case "4":
                System.out.println("Stadsbilarna som finns:");
                for(Vehicle vehicle: inventory.getVehicleList()){
                    if(vehicle instanceof CityCar){
                        System.out.println(vehicle.getBrand() + ", Modell: "+ vehicle.getModel()+ ", Färg:"+ ((CityCar)vehicle).getColor()
                                + ", Dörrar:" + ((CityCar) vehicle).getDoors());
                        vehicle.start();
                    }
                }
                break;
            case "5":
                available();
                break;
            default:
                break;
        }
    }
    public void available(){
        for(Vehicle vehicle: inventory.getVehicleList()){
            if(vehicle.isLoanable()){
                System.out.println(vehicle + " är tillgänglig att låna.");
            }
        }
    }
}
