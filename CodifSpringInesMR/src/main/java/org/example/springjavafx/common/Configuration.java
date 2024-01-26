package org.example.springjavafx.common;

import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
@Log4j2
@Data
public class Configuration {
    private Properties configProperties;
    private String keyStorePassword;

    public Configuration(){
        try {
            configProperties = new Properties();
            configProperties.load(getClass().getClassLoader().getResourceAsStream("config/config.properties"));
            this.keyStorePassword = configProperties.getProperty("KeyStorePassword");
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
    }


}
