package Rental;

import java.util.ArrayList;
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

    public void available(){
        for(Vehicle vehicle: inventory.getVehicleList()){
            if(vehicle.isLoanable()){
                System.out.println(vehicle + " är tillgänglig att låna.");
            }
        }
    }
}
