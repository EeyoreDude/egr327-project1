package dealerapi.dealerapi;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@RestController
public class MyTasks {
    public MyTasks() throws IOException {}

    public Dealer dealer = new Dealer("report.txt");
    RestTemplate restTemplate = new RestTemplate();

    @Scheduled(fixedRate = 500)
    public void addVehicle(){
        String make = RandomStringUtils.randomAlphabetic(10);
        String model = RandomStringUtils.randomAlphabetic(10);
        int year = (int) (Math.random() * (2016 - 1986 + 1) + 1985);
        int price = (int) (Math.random() * (45000 - 15000 + 1) + 15000);
        boolean isFourWheel = Math.round(Math.random()) == 1;
        int mpg = (int) (Math.random() * (25 - 12 + 1) + 12);

        restTemplate.postForObject("http://localhost:8080/addVehicle", new Vehicle(dealer.getLastID() + 1, make, model, year, isFourWheel, price, mpg), Vehicle.class);
    }

    @Scheduled(fixedRate = 1500)
    public void deleteVehicle(){
        int randomID = (int) (Math.random() * (dealer.getLastID()));
        restTemplate.delete("http://localhost:8080/deleteVehicle/" + randomID);
        System.out.println("Deleted:\t" + randomID);
    }

    @Scheduled(fixedRate  = 1000)
    public void updateVehicle(){
        int randomIndex = (int) (Math.random() * (dealer.getList().size() - 1));
        System.out.println("Wrote:\t" + dealer.getList().get(randomIndex).getId());
        Vehicle randomVehicle = dealer.getList().get(randomIndex);
        randomVehicle.setModel("REFURBISHED");

        restTemplate.put("http://localhost:8080/updateVehicle", randomVehicle);
    }



}
