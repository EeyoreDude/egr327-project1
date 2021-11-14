package dealerapi.dealerapi;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public class Inventory implements Serializable {

    @Serial
    private static final long serialVersionUID = -1897725568505991669L;
    // Declare required values
    private ArrayList<Vehicle> inventoryList;

    /**
     * Constructor for Inventory class
     * @param inventoryList - a list of vehicles
     */
    public Inventory(ArrayList<Vehicle> inventoryList) {
        this.inventoryList = inventoryList;
    }

    public Inventory(){}

    /**
     * Getter for inventorylist
     * @return - the ArrayList of Vehicles stored in the Inventory
     */
    public ArrayList<Vehicle> getInventoryList() {
        return inventoryList;
    }

    /**
     * Adds a vehicle to the Inventory
     * @param newVehicle - the new vehicle to be added to the Inventory's array list
     */
    public void add(Vehicle newVehicle){
        inventoryList.add(newVehicle);
    }

    /**
     * Removes a vehicle from the Inventory
     * @param vehicle - a vehicle in the Inventory's array list that will be removed
     */
    public void remove(Vehicle vehicle){
        inventoryList.remove(vehicle);
    }

    /**
     * Searches the Inventory's array list and returns the cheapest vehicle
     * @return - the cheapest vehicle in the Inventory
     */
    public Vehicle findCheapestVehicle(){
        int cheapestIndex = 0;
        int currentPrice = Integer.MAX_VALUE;
        for(Vehicle v : inventoryList){
            if(v.getPrice() < currentPrice){
                currentPrice = v.getPrice();
                cheapestIndex = inventoryList.indexOf(v);
            }
        }
        return inventoryList.get(cheapestIndex);
    }

    /**
     * Searches the Inventory's array list and returns the most expensive vehicle
     * @return - the most expensive vehicle in the Inventory
     */
    public Vehicle findMostExpensiveVehicle(){
        int priciestIndex = 0;
        int currentPrice = Integer.MIN_VALUE;
        for(Vehicle v : inventoryList){
            if(v.getPrice() > currentPrice){
                currentPrice = v.getPrice();
                priciestIndex = inventoryList.indexOf(v);
            }
        }
        return inventoryList.get(priciestIndex);
    }

    /**
     * Averages the price of all the vehicles in the inventory
     * @return - a string message stating the average price of the inventory
     */
    public String printAveragePriceOfAllVehicles(){
        int inventoryAveragePrice = 0;
        for(Vehicle vehicle : inventoryList){
            inventoryAveragePrice += vehicle.getPrice();
        }
        inventoryAveragePrice /= inventoryList.size();

        return "The average price of the inventory is $" + inventoryAveragePrice + ".";
    }

    public String toString(){
        String fullString = "";
        for(Vehicle currentVehicle : inventoryList){
            fullString += currentVehicle.toString() + "\n";
        }
        return fullString;
    }
}
