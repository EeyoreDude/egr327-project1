package dealerapi.dealerapi;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@RestController
public class MyTasks {

    private final CentersHolder holder;
    @Autowired
    public MyTasks(CentersHolder holder){
        this.holder = holder;
    }

    RestTemplate restTemplate = new RestTemplate();

    @Scheduled(fixedRate = 1000)
    public void addVehicle(){
        String make = RandomStringUtils.randomAlphabetic(10);
        String model = RandomStringUtils.randomAlphabetic(10);
        int year = (int) (Math.random() * (2016 - 1986 + 1) + 1985);
        int price = (int) (Math.random() * (45000 - 15000 + 1) + 15000);
        boolean isFourWheel = Math.round(Math.random()) == 1;
        int mpg = (int) (Math.random() * (25 - 12 + 1) + 12);

        restTemplate.postForObject("http://localhost:8080/addVehicle", new Vehicle(make, model, year, isFourWheel, price, mpg), Vehicle.class);
    }

    @Scheduled(fixedRate = 1500)
    public void deleteVehicle(){
        int randomID = (int) (Math.random() * (holder.globalId - 1) + 1);
        restTemplate.delete("http://localhost:8080/deleteVehicle/" + randomID);
    }

    @Scheduled(fixedRate  = 3000)
    public void updateVehicle(){
        int randomID = (int) (Math.random() * (holder.globalId - 1) + 1);
        Vehicle updatedVehicle = new Vehicle(randomID, "Cool", "Car", 2001, true, 20, 100);

        restTemplate.put("http://localhost:8080/updateVehicle", updatedVehicle);

        restTemplate.getForObject("http://localhost:8080/getVehicle/" + randomID, Vehicle.class);
    }

    @Scheduled (cron = "0 0 * * * *")
    public void latestVehicleReport(){
        System.out.println("Report:");
        System.out.println(restTemplate.getForObject("http://localhost:8080/getLatestVehicles", ArrayList.class));
    }


}
