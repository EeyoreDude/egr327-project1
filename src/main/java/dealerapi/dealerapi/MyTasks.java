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




}
