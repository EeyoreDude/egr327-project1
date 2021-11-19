package dealerapi.dealerapi;

import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CentersHolder {

    public Dealer dealer = new Dealer("report.txt");
    public int globalId = dealer.getLastID() + 1;

    public CentersHolder() throws IOException {
    }
}
