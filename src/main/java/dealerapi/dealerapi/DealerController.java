package dealerapi.dealerapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.rmi.NoSuchObjectException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class DealerController {
    private final CentersHolder holder;

    @Autowired
    public DealerController(CentersHolder holder) throws IOException {
        this.holder = holder;
    }

    @RequestMapping(value = "/addVehicle", method = RequestMethod.POST)
    public Vehicle addVehicle(@RequestBody Vehicle newVehicle) throws IOException{
        newVehicle.setId(holder.globalId++);
        holder.dealer.getInventory().add(new Vehicle(newVehicle));
        holder.dealer.generateReport();
        return newVehicle;
    }

    @RequestMapping(value = "/getVehicle/{id}", method = RequestMethod.GET)
    public Vehicle getVehicle(@PathVariable("id")  int id) throws IOException{
        for(Vehicle currentVehicle : holder.dealer.getList()){
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

        for(int i = 0; i < holder.dealer.getList().size(); i++){
            if(holder.dealer.getList().get(i).getId() == newVehicle.getId()){
                holder.dealer.getList().set(i, newVehicle);
                holder.dealer.generateReport();
                return newVehicle;
            }
        }

        return null;
    }

    @RequestMapping(value = "/deleteVehicle/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteVehicle(@PathVariable("id") int id) throws IOException{
        for(Vehicle currentVehicle : holder.dealer.getList()){
            if(currentVehicle.getId() == id){
                holder.dealer.getList().remove(currentVehicle);
                holder.dealer.generateReport();
                return new ResponseEntity<>("Successfully removed vehicle with id " + id, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("No vehicle with id " + id, HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/getLatestVehicles", method = RequestMethod.GET)
    public List<Vehicle> getLatestVehicles() {
        ArrayList<Vehicle> latestVehicles = new ArrayList<>();
        for(int i = Math.max(holder.dealer.getList().size() - 10, 0); i < holder.dealer.getList().size(); i++){
            latestVehicles.add(holder.dealer.getList().get(i));
        }
        return latestVehicles;
    }







}
