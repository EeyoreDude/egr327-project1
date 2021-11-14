package dealerapi.dealerapi;

import java.io.*;

public class Vehicle implements Serializable {

    @Serial
    private static final long serialVersionUID = 3883381334275202140L;

    // Declare required values
    private String make;
    private String model;
    private int year;
    private boolean isFourWheel;
    private int price;
    private int mpg;

    private int id;

    /**
     * Vehicle class constructor, takes in and initializes all sub values
     * @param make - the make of the vehicle (i.e. Ford, Kia)
     * @param model - the model of the vehicle (i.e. F-150, Optima)
     * @param year - the year of the model (2007, 2015)
     * @param isFourWheel - whether the vehicle is four-wheel drive, as a boolean value
     * @param price - the price of the vehicle as an integer
     * @param mpg - the miles per gallon of the vehicle as an integer
     */

    public Vehicle(String make, String model, int year, boolean isFourWheel, int price, int mpg) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.isFourWheel = isFourWheel;
        this.price = price;
        this.mpg = mpg;
        this.id = -1;
    }

    /**
     * Vehicle constructor that also contains id value
     * @param id - the id of the vehicle in the rest api
     * @param make - the make of the vehicle (i.e. Ford, Kia)
     * @param model - the model of the vehicle (i.e. F-150, Optima)
     * @param year - the year of the model (2007, 2015)
     * @param isFourWheel - whether the vehicle is four-wheel drive, as a boolean value
     * @param price - the price of the vehicle as an integer
     * @param mpg - the miles per gallon of the vehicle as an integer
     */
    public Vehicle(int id, String make, String model, int year, boolean isFourWheel, int price, int mpg) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.year = year;
        this.isFourWheel = isFourWheel;
        this.price = price;
        this.mpg = mpg;
    }

    public Vehicle(Vehicle other){
        this.make = other.make;
        this.model = other.model;
        this.year = other.year;
        this.isFourWheel = other.isFourWheel;
        this.price = other.price;
        this.mpg = other.mpg;
        this.id = other.id;
    }

    public Vehicle(Vehicle other, int id){
        this.make = other.make;
        this.model = other.model;
        this.year = other.year;
        this.isFourWheel = other.isFourWheel;
        this.price = other.price;
        this.mpg = other.mpg;
        this.id = id;
    }

    public Vehicle(){}

    /**
     * Getter for make
     * @return make string
     */
    public String getMake() {
        return make;
    }

    /**
     * Setter for make
     * @param make - the make of the vehicle (i.e. Ford, Kia)
     */
    public void setMake(String make) {
        this.make = make;
    }

    /**
     * Getter for model
     * @return model string
     */
    public String getModel() {
        return model;
    }

    /**
     * Setter for model
     * @param model - the model of the vehicle (i.e. F-150, Optima)
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * Getter for year
     * @return year int
     */
    public int getYear() {
        return year;
    }

    /**
     * Setter for id
     * @return - the id of the vehicle in the rest api
     */
    public int getId(){
        return id;
    }

    /**
     * Setter for year
     * @param year - the year of the model (2007, 2015)
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Getter for fourWheel
     * @return fourWheel boolean
     */
    public boolean getIsFourWheel() {
        return isFourWheel;
    }

    /**
     * Setter for fourWheel
     * @param isFourWheel - whether the vehicle is four-wheel drive, as a boolean value
     */
    public void setIsFourWheel(boolean isFourWheel) {
        this.isFourWheel = isFourWheel;
    }

    /**
     * Getter for price
     * @return price int
     */
    public int getPrice() {
        return price;
    }

    /**
     * Setter for price
     * @param price - the price of the vehicle as an integer
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * Getter for mpg
     * @return mpg int
     */
    public int getMpg() {
        return mpg;
    }

    /**
     * Setter for mpg
     * @param mpg - the miles per gallon of the vehicle as an integer
     */
    public void setMpg(int mpg) {
        this.mpg = mpg;
    }

    /**
     * Setter for id
     * @param id - the id of the vehicle in the rest api
     */
    public void setId(int id){
        this.id = id;
    }

    public void writeToFile() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("report.txt"));
        writer.write(this.toString());
        writer.close();
    }

    /**
     * Print method
     * Outputs text to console
     *
     * Output is as follows:
     *
     * Year Make Model
     * Is Four-Wheel Drive
     * Price
     * MPG
     * @return printed string
     */
    @Override
    public String toString(){
        return "{\n" +
                "    \"id\": \"" + id + "\",\n" +
                "    \"make\": \"" + make + "\",\n" +
                "    \"model\": \"" + model + "\",\n" +
                "    \"year\": \"" + year + "\",\n" +
                "    \"isFourWheel\": \"" + isFourWheel + "\",\n" +
                "    \"price\": \"" + price + "\",\n" +
                "    \"mpg\": \"" + mpg + "\"\n" +
                "}";
    }
}
