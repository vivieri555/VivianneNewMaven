package Vehicle;

    public class Vehicle extends Item{
        private String brand;
        private String model;
        private boolean loanable;
        public Vehicle(){}
        public Vehicle(String brand, String model, boolean loanable){
            this.brand = brand;
            this.model = model;
            this.loanable = loanable;
        }
        public Vehicle(String brand, String model, boolean loanable, String vehicleType, String hasRearCamera, String gearbox){}
        public String getBrand(){
            return brand;
        }
        public void setBrand(String brand){
            this.brand = brand;
        }
        public String getModel(){
            return model;
        }
        public void setModel(String model){
            this.model = model;
        }
        public boolean isLoanable(){
            return loanable;
        }
        public void setLoanable(boolean loanable){
            this.loanable = loanable;
        }
        public void start(){
        }
        public String toString(){
            return brand;
        }
    }

