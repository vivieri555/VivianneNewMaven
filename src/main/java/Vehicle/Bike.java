package Vehicle;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Bike extends Vehicle{
    private String gears;
    private String basket;
    public Bike() {}
    public Bike (String brand, String model, Boolean loanable, String gears, String basket) {
        super(brand, model, loanable);
        this.gears = gears;
        this.basket = basket;
    }
    @XmlElement
    public String getGears() {
        return gears;
    }
    public void setGears(String gears) {
        this.gears = gears;
    }
    @XmlElement
    public String getBasket () {
        return basket;
    }
    public void setBasket(String basket) {
        this.basket = basket;
    }
}
