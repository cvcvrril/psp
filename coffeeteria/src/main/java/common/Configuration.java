package common;

import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.util.Properties;

@Log4j2
public class Configuration {

    private String pathDataCustomers;
    private String pathDataOrders;

    public Configuration(){
        try{
            Properties p = new Properties();
            p.load(getClass().getClassLoader().getResourceAsStream("config/config.properties"));
            this.pathDataCustomers = p.getProperty("pathDataCustomers");
            this.pathDataOrders = p.getProperty("pathDataOrders");

        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }


}
