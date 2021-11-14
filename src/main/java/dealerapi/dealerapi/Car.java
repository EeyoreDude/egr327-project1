package dealerapi.dealerapi;

public class Car extends Vehicle{

    // Declare values unique to Car subclass
    private boolean isConvertible;

    /**
     * Car class constructor, calls Vehicle constructor and also defines convertible boolean
     * @param make - the make of the vehicle (i.e. Ford, Kia)
     * @param model - the model of the vehicle (i.e. F-150, Optima)
     * @param year - the year of the model (2007, 2015)
     * @param isFourWheel - whether the vehicle is four-wheel drive, as a boolean value
     * @param price - the price of the vehicle as an integer
     * @param mpg - the miles per gallon of the vehicle as an integer
     * @param isConvertible - if the car is a convertible, as a boolean value
     */
    public Car(String make, String model, int year, boolean isFourWheel, int price, int mpg, boolean isConvertible) {
        super(make, model, year, isFourWheel, price, mpg);
        this.isConvertible = isConvertible;
    }

    public Car(){}

    /**
     * Getter for isConvertible
     * @return isConvertible boolean
     */
    public boolean isConvertible() {
        return isConvertible;
    }

    /**
     * Setter for isConvertible
     * @param convertible - if the car is a convertible, as a boolean value
     */
    public void setConvertible(boolean convertible) {
        isConvertible = convertible;
    }

    /**
     * Print method
     * Outputs text to console
     * Calls Vehicle print method and adds convertible value to the end
     *
     * Output is as follows:
     *
     * Year Make Model
     * Is Four-Wheel Drive
     * Price
     * MPG
     * Is Convertible
     * @return printed string
     */
    @Override
    public String toString(){
        if(isConvertible) {
            return super.toString() + "Convertible\n";
        }
        return super.toString();
    }
}
