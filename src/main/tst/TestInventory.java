import dealerapi.dealerapi.Car;
import dealerapi.dealerapi.Inventory;
import dealerapi.dealerapi.Truck;
import dealerapi.dealerapi.Vehicle;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;

import java.util.ArrayList;

public class TestInventory {

    /**
     * Initialize data structures that will be used for testing
     */
    public static Car c;
    public static Truck t;
    public static Vehicle v;
    public static ArrayList<Vehicle> l;
    public static Inventory i;

    /**
     * Define data structures
     */
    @BeforeClass
    public static void declareInventory(){
        c = new Car("Kia","Optima", 2007, false, 14599, 23, false){};
        t = new Truck("Ford", "F-150", 2015, true, 35000, 17, false, 2){};
        v = new Vehicle("Chevrolet","Corvette", 2021, true, 59000, 15);
        l = new ArrayList<>();
        l.add(c);
        l.add(t);

        i = new Inventory(l){};
    }

    /**
     * Test the add method
     * Add a new vehicle to an inventory
     * Assert that the new vehicle is equal to the last vehicle in the inventory
     */
    @Test
    public void testAdd(){
        i.add(v);
        Assert.assertEquals(v, i.getInventoryList().get(2));
    }

    /**
     * Test the findCheapest method
     * Assert that the cheapest vehicles price is equal to the returned value of this method
     */
    @Test
    public void testFindCheapest(){
        Assert.assertEquals(c, i.findCheapestVehicle());
    }

    /**
     * Test findMostExpensive method
     * Assert that the most expensive vehicles price is equal to the returned value of this method
     */
    @Test
    public void testFindMostExpensive(){
        Assert.assertEquals(v, i.findMostExpensiveVehicle());
    }

    /**
     * Test printAveragePrice method
     * Assert that the custom message and average price is equal to the returned string of this method
     */
    @Test
    public void testPriceAverage() {
        Assert.assertEquals("The average price of the inventory is $24799.", i.printAveragePriceOfAllVehicles());
    }

    /**
     * Test remove method
     * Remove a vehicle from the inventory
     * Assert that an IndexOutOfBoundsException is thrown when attempting to get the previous last index
     */
    @Test
    public void testRemove() {
        i.remove(v);
        Assert.assertThrows( IndexOutOfBoundsException.class, () -> i.getInventoryList().get(2)); // yes, I googled this formatting
    }







}
