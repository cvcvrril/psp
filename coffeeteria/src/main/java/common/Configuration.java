package common;

import jakarta.inject.Singleton;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.util.Properties;

@Singleton
@Log4j2
public class Configuration {

    private Properties ptxt;
    private Properties pxml;
    private static Configuration instance;
    private String pathDataCustomers;
    private String pathDataOrders;
    private String pathXMLOrders;

    private Configuration(){
        try{
            ptxt = new Properties();
            ptxt.load(getClass().getClassLoader().getResourceAsStream("config/config.properties"));
            this.pathDataCustomers = ptxt.getProperty("pathDataCustomers");
            this.pathDataOrders = ptxt.getProperty("pathDataOrders");
            pxml = new Properties();
            pxml.loadFromXML(Configuration.class.getClassLoader().getResourceAsStream("config/properties.xml"));
            this.pathXMLOrders = pxml.getProperty("xmlOrdersPath");

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

    public String getPropertyTXT(String key){
        return ptxt.getProperty(key);
    }

    public String getPropertyXML(String key){return pxml.getProperty(key);}


}
