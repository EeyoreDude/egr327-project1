package dealerapi.dealerapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DealerApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(DealerApiApplication.class, args);
    }

}
