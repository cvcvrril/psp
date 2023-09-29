package common;

import jakarta.inject.Singleton;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.util.Properties;

@Singleton
@Log4j2
public class Configuration {

    private Properties p;
    private static Configuration instance;
    private String pathDataCustomers;
    private String pathDataOrders;

    private Configuration(){
        try{
            Properties p = new Properties();
            p.load(getClass().getClassLoader().getResourceAsStream("config/config.properties"));
            this.pathDataCustomers = p.getProperty("pathDataCustomers");
            this.pathDataOrders = p.getProperty("pathDataOrders");

        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

    public static Configuration getInstance(){
        if (instance == null){
            instance = new Configuration();
        }
        return instance;
    }

    public String getProperty (String key){
        return p.getProperty(key);
    }


}
