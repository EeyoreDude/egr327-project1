package dealerapi.dealerapi;

public class Truck extends Vehicle{

    // Declare Truck unique values
    private boolean hasSideStep;
    private int towCapacity;

    /**
     * Truck class constructor, calls Vehicle constructor and also defines convertible boolean
     * @param make - the make of the vehicle (i.e. Ford, Kia)
     * @param model - the model of the vehicle (i.e. F-150, Optima)
     * @param year - the year of the model (2007, 2015)
     * @param isFourWheel - whether the vehicle is four-wheel drive, as a boolean value
     * @param price - the price of the vehicle as an integer
     * @param mpg - the miles per gallon of the vehicle as an integer
     * @param hasSideStep - if the truck has a side step, as a boolean value
     * @param towCapacity - the towCapacity of the truck, in tons, as an int
     */
    public Truck(String make, String model, int year, boolean isFourWheel, int price, int mpg, boolean hasSideStep, int towCapacity) {
        super(make, model, year, isFourWheel, price, mpg);
        this.hasSideStep = hasSideStep;
        this.towCapacity = towCapacity;
    }

    public Truck(){};

    /**
     * Getter for hasSideStep
     * @return - hasSideStep boolean
     */
    public boolean hasSideStep() {
        return hasSideStep;
    }

    /**
     * Setter for hasSideStep
     * @param hasSideStep - if the truck has a side step, as a boolean value
     */
    public void setHasSideStep(boolean hasSideStep) {
        this.hasSideStep = hasSideStep;
    }

    /**
     * Getter for towCapacity
     * @return towCapacity int
     */
    public int getTowCapacity() {
        return towCapacity;
    }

    /**
     * Setter for towCapacity
     * @param towCapacity - the towCapacity of the truck, in tons, as an int
     */
    public void setTowCapacity(int towCapacity) {
        this.towCapacity = towCapacity;
    }

    /**
     * Print method
     * Outputs text to console
     * Calls Vehicle print method and adds hasSideStep and towCapacity values to the end
     *
     * Output is as follows:
     *
     * Year Make Model
     * Is Four-Wheel Drive
     * Price
     * MPG
     * Has Side Step
     * Tow Capacity
     * @return printed string
     */
    @Override
    public String toString(){
        String s = super.toString();
        if(!hasSideStep) {
            s += "No ";
        }
        return s + "Side Step\nTow up to " + towCapacity + " tons\n";
    }
}
