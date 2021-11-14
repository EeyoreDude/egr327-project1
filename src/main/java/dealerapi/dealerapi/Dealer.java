package dealerapi.dealerapi;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.LineIterator;
import org.springframework.util.SerializationUtils;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class Dealer implements Serializable {

    /**
     * Declare fields
     */
    @Serial
    private static final long serialVersionUID = 84913887335462394L;

    private static Inventory inventory;

    public Inventory getInventory(){
        return inventory;
    }

    public ArrayList<Vehicle> getList(){ return inventory.getInventoryList(); }

    public int getLastID(){
        return getList().get(getList().size() - 1).getId();
    }

    /**
     * The constructor for the Dealer class
     * @param fileURL - the url of the file to load into the Dealer
     * @throws IOException - thrown if the file does not exist
     */
    public Dealer(String fileURL) throws IOException {

       // "https://raw.githubusercontent.com/kyungsooim/TestData/master/data/SeptemberInventory.txt"

        inventory = readInventoryFromWeb(fileURL);
        generateReport();
    }

    public Dealer(){}

    /**
     * A helper function for the constructor, reads a given file url and inputs the data into an Inventory object
     * @param fileURL - the url of the file to read
     * @return - an Inventory containing the information from the file
     * @throws IOException - thrown if the file does not exist
     */
    private Inventory readInventoryFromWeb(String fileURL) throws IOException {
        ArrayList<Vehicle> inventoryList = new ArrayList<>();
        InputStream inputStream = new URL(fileURL).openStream();
        InputStreamReader inputStreamReader = new InputStreamReader( inputStream );
        int id = 0;
        try{
            LineIterator lineIterator = IOUtils.lineIterator(inputStreamReader);
            while(lineIterator.hasNext()) {
                // split the line into a string array by commas
                String[] lineArray = lineIterator.nextLine().split(",", 4);
                boolean lineArray3 = lineArray[3].contains("TRUE");

                // split the first string in the array by spaces to separate the make and model
                inventoryList.add(new Vehicle(  id++,
                                                lineArray[0].split(" ", 2)[0],
                                                lineArray[0].split(" ", 2)[1],
                                                parseInt(lineArray[1]),
                                                lineArray3,
                                                parseInt(lineArray[2]),
                                            0){});
            }
        }
        finally {
            IOUtils.closeQuietly( inputStream );
        }

        return  new Inventory(inventoryList);
    }

    /**
     * Serializes the Dealer object to a given file name
     * @param fileName - the name of the file to be saved
     */
    public void serialize(String fileName) throws IOException {
        File file = FileUtils.getFile("C:\\Users\\stuff\\IdeaProjects\\CSC312\\egr327-HW1\\ser", fileName);
        byte[] data = SerializationUtils.serialize(this);
        FileUtils.writeByteArrayToFile(file, data);
    }

    /**
     * Deserializes a Dealer from a given file name
     * @param fileName - the file name to be deserialized
     * @return - the deserialized Dealer
     */
    public Dealer deserialize(String fileName) throws IOException {
        File file = FileUtils.getFile("C:\\Users\\stuff\\IdeaProjects\\CSC312\\egr327-HW1\\ser", fileName);
        byte[] dataToDeserialize;
        dataToDeserialize = FileUtils.readFileToByteArray(file);
        return (Dealer) SerializationUtils.deserialize(dataToDeserialize);
    }

    public void generateReport() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("report.txt"));
        writer.write(inventory.toString());
        writer.close();
    }

    /**
     * A toString override to print out the data of a Dealer in a nice, readable fashion
     * @return - a string representing all the data in the object
     */
    public String toString(){
        return inventory.toString();
    }
}
