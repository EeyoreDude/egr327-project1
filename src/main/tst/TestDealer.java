import dealerapi.dealerapi.Dealer;
import dealerapi.dealerapi.Vehicle;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class TestDealer {

    /**
     * Declare global variables
     */
    public static Dealer myDealer;

    /**
     * Initialize globals and create serialized Dealer file
     * @throws IOException - thrown if the file URL in the Dealer's constructor does not exist
     */
    @BeforeClass
    public static void declareDealer() throws IOException {
        myDealer = new Dealer("https://raw.githubusercontent.com/kyungsooim/TestData/master/data/SeptemberInventory.txt");
        myDealer.serialize("testSerialization.txt");
    }

    /**
     * Test Dealer's constructor
     * Assert that the first and last vehicle in the dealer match expected values
     * Assert that the constructor throws an exception when the file url does not exist
     */
    @Test
    public void testConstructor(){
        Vehicle expectedFirstVehicle = new Vehicle("Ford", "F150", 2015, true, 35000, 0);
        Vehicle expectedLastVehicle = new Vehicle("Chevy", "Silverado", 2016, true, 35000, 0);
        Vehicle actualFirstVehicle = new Vehicle(myDealer.getInventory().getInventoryList().get(0));
        Vehicle actualLastVehicle = new Vehicle(myDealer.getInventory().getInventoryList().get(8));


        Assert.assertEquals(expectedFirstVehicle.toString(), actualFirstVehicle.toString());
        Assert.assertEquals(expectedLastVehicle.toString(), actualLastVehicle.toString());

        Assert.assertThrows(IOException.class, () -> new Dealer(""));
    }

    /**
     * Test the serialization of Dealer
     * A serialized file was created in BeforeClass, assert that this file exists
     * Assert that this method throws an IllegalArgumentException when given an empty file name
     */
    @Test
    public void testSerialization(){
        File file = FileUtils.getFile("C:\\Users\\stuff\\IdeaProjects\\CSC312\\egr327-HW1\\ser", "testSerialization.txt");
        Assert.assertTrue(file.exists());

        Assert.assertThrows(IllegalArgumentException.class, () -> myDealer.serialize(""));
    }

    /**
     * Test the deserialization of Dealer
     * Create a new dealer by deserializing the file created in BeforeClass
     * Assert that the first and last vehicles in the new Dealer match expected values
     * Assert that the method throws an IOException if the given file does not exist
     * @throws IOException - thrown if the given file does not exist
     */
    @Test
    public void testDeserialization() throws IOException {
        Dealer deserializedDealer = myDealer.deserialize("testSerialization.txt");
        Vehicle expectedFirstVehicle = new Vehicle("Ford", "F150", 2015, true, 35000, 0);
        Vehicle expectedLastVehicle = new Vehicle("Chevy", "Silverado", 2016, true, 35000, 0);
        Vehicle actualFirstVehicle = new Vehicle(deserializedDealer.getInventory().getInventoryList().get(0));
        Vehicle actualLastVehicle = new Vehicle(deserializedDealer.getInventory().getInventoryList().get(8));


        Assert.assertEquals(expectedFirstVehicle.toString(), actualFirstVehicle.toString());
        Assert.assertEquals(expectedLastVehicle.toString(), actualLastVehicle.toString());
        Assert.assertThrows(IOException.class, () -> deserializedDealer.deserialize(""));

    }

    /**
     * Assert that the string returned by toString matches the expected value
     */
    @Test
    public void testToString(){
        Assert.assertEquals("""
                [ 2015 Ford F150, $35000, true, 0MPG ]\s
                [ 2010 Ford Focus, $15000, false, 0MPG ]\s
                [ 2012 Ford Fiesta, $18000, false, 0MPG ]\s
                [ 2015 Ford Fusion, $20000, false, 0MPG ]\s
                [ 1999 Ford Mustang, $8000, false, 0MPG ]\s
                [ 2017 Ford GT, $30000, false, 0MPG ]\s
                [ 2015 Ford Taurus, $25000, false, 0MPG ]\s
                [ 2016 GM Sierra, $40000, true, 0MPG ]\s
                [ 2016 Chevy Silverado, $35000, true, 0MPG ]\s
                """, myDealer.toString());
    }

}
