package Rental;

import Member.Member;
import Vehicle.Vehicle;

public class Rental{

    Member member;
    Vehicle vehicle;
    private int rentalDays;
    private double cost;
    public Rental(){}
    public Rental(Member member, Vehicle vehicle, int rentalDays, double cost){
        this.member = member;
        this.vehicle = vehicle;
        this.rentalDays = rentalDays;
        this.cost = cost;
    }
    public int getRentalDays(){
        return rentalDays;
    }
    public void setRentalDays(int rentalDays){
        this.rentalDays = rentalDays;
    }
    public double getCost(){
        return cost;
    }
    public void setCost(double cost){
        this.cost = cost;
    }
    public Member getMember(){
        return member;
    }
    public void setMember(Member member){
        this.member = member;
    }
    public Vehicle getVehicle(){
        return vehicle;
    }
    public void setVehicle(Vehicle vehicle){
        this.vehicle = vehicle;
    }
}
