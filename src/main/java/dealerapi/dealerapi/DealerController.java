package dealerapi.dealerapi;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.NoSuchObjectException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class DealerController {
    public DealerController() throws IOException {}

    public Dealer dealer = new Dealer("https://raw.githubusercontent.com/EeyoreDude/egr327-project1/main/preset-13.txt");
    private static int id = 0;


    @RequestMapping(value = "/addVehicle", method = RequestMethod.POST)
    public Vehicle addVehicle(@RequestBody Vehicle newVehicle) throws IOException{
        id = dealer.getLastID() + 1;
        newVehicle.setId(id++);
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
        if(newVehicle.getId() == -1){
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
    public List<Vehicle> getLatestVehicles() throws IOException{
        int i = Math.min(dealer.getList().size() - 10, 0);
        ArrayList<Vehicle> latestVehicles = new ArrayList<>();
        for(; i < dealer.getList().size(); i++){
            latestVehicles.add(dealer.getList().get(i));
        }
        return latestVehicles;
    }


}
