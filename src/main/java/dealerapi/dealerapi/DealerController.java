package dealerapi.dealerapi;

import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class DealerController {
    public DealerController() throws IOException {}

    public Dealer dealer = new Dealer("https://raw.githubusercontent.com/kyungsooim/TestData/master/data/SeptemberInventory.txt");
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
        return null;
    }

    @RequestMapping(value = "/updateVehicle", method = RequestMethod.GET)
    public Vehicle updateVehicle(@RequestBody Vehicle newVehicle) throws IOException{
        for(int i = 0; i < dealer.getList().size(); i++){
            if(dealer.getList().get(i).getId()==newVehicle.getId()){
                dealer.getList().set(i, newVehicle);
                dealer.generateReport();
                return newVehicle;
            } else{
                System.out.println("No Vehicle With Matching ID Found");
            }
        }
        return null;
    }


}
