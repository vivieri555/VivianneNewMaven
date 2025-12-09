package Rental;

public interface PricePolicy {

    double cost(int time);
    double getDiscountedCost(Rental rental);
}