package dealerapi.dealerapi;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


import java.io.IOException;
import java.rmi.NoSuchObjectException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class DealerController {
    public DealerController() throws IOException {}

    public Dealer dealer = new Dealer("report.txt");
    public int globalId = dealer.getLastID() + 1;


    @RequestMapping(value = "/addVehicle", method = RequestMethod.POST)
    public Vehicle addVehicle(@RequestBody Vehicle newVehicle) throws IOException{
        newVehicle.setId(globalId++);
        dealer.getInventory().add(new Vehicle(newVehicle));
        dealer.generateReport();
        return newVehicle;
    }

    @RequestMapping(value = "/getVehicle/{id}", method = RequestMethod.GET)
    public Vehicle getVehicle(@PathVariable("id")  int id) throws IOException{
        for(Vehicle currentVehicle : dealer.getList()){
                if(currentVehicle.getId() == id){
                    return currentVehicle;
                }
        }
        throw new NoSuchObjectException("No vehicle with id " + id);
    }

    @RequestMapping(value = "/updateVehicle", method = RequestMethod.PUT)
    public Vehicle updateVehicle(@RequestBody Vehicle newVehicle) throws IOException{
        if(newVehicle.getId() < 0){
            throw new IllegalArgumentException("Please provide an ID");
        }

        for(Vehicle currentVehicle : dealer.getList()){
            if(currentVehicle.getId() == newVehicle.getId()){
                dealer.getList().set(currentVehicle.getId(), newVehicle);
                dealer.generateReport();
                return newVehicle;
            }
        }

        throw new NoSuchObjectException("No vehicle with id " + newVehicle.getId());
    }

    @RequestMapping(value = "/deleteVehicle/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteVehicle(@PathVariable("id") int id) throws IOException{
        for(Vehicle currentVehicle : dealer.getList()){
            if(currentVehicle.getId() == id){
                dealer.getList().remove(currentVehicle);
                dealer.generateReport();
                return new ResponseEntity<>("Successfully removed vehicle with id " + id, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("No vehicle with id " + id, HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/getLatestVehicles", method = RequestMethod.GET)
    public List<Vehicle> getLatestVehicles() {
        ArrayList<Vehicle> latestVehicles = new ArrayList<>();
        for(int i = Math.max(dealer.getList().size() - 10, 0); i < dealer.getList().size(); i++){
            latestVehicles.add(dealer.getList().get(i));
        }
        return latestVehicles;
    }


    RestTemplate restTemplate = new RestTemplate();

    @Scheduled(fixedRate = 3000)
    public void addVehicle(){
        String make = RandomStringUtils.randomAlphabetic(10);
        String model = RandomStringUtils.randomAlphabetic(10);
        int year = (int) (Math.random() * (2016 - 1986 + 1) + 1985);
        int price = (int) (Math.random() * (45000 - 15000 + 1) + 15000);
        boolean isFourWheel = Math.round(Math.random()) == 1;
        int mpg = (int) (Math.random() * (25 - 12 + 1) + 12);

        restTemplate.postForObject("http://localhost:8080/addVehicle", new Vehicle(make, model, year, isFourWheel, price, mpg), Vehicle.class);

        int thisId = globalId - 1;
        System.out.println("Added:\t" + thisId);
    }

    @Scheduled(fixedRate = 1500)
    public void deleteVehicle(){
        int randomID = (int) (Math.random() * (globalId - 1) + 1);
        restTemplate.delete("http://localhost:8080/deleteVehicle/" + randomID);
        System.out.println("Deleted:\t" + randomID);
    }

    @Scheduled(fixedRate  = 1000)
    public void updateVehicle(){
        int randomID = (int) (Math.random() * (globalId - 1) + 1);
        Vehicle randomVehicle = new Vehicle(randomID, "Cool", "Car", 2001, true, 20, 100);
        randomVehicle.setModel("REFURBISHED");

        restTemplate.put("http://localhost:8080/updateVehicle", randomVehicle);
        System.out.println("Updated:\t" + randomID);
    }

    @Scheduled(fixedRate = 500)
    public void testForDuplicate(){
        dealer.testInventoryIds();
    }


}
