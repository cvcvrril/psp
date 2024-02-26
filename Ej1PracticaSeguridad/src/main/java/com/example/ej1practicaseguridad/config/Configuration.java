package com.example.ej1practicaseguridad.config;

import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

@Log4j2
@Data
@org.springframework.context.annotation.Configuration
public class Configuration {

    private static Configuration instance = null;
    private Properties properties;
    private Configuration() {
        try {

            properties = new Properties();
            properties.load(getClass().getClassLoader().getResourceAsStream("config/properties"));

        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

    public static Configuration getInstance() {
        if (instance == null) {
            instance = new Configuration();
        }
        return instance;
    }
    public String getProperty(String key){return properties.getProperty(key);}
    public void setProperties(String key, String value){
        try {
            properties.setProperty(key, value);
            properties.store(new FileWriter("src/main/resources/config/properties"),null);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

    @Bean
    public PasswordEncoder createPasswordEncoder() {

        return new BCryptPasswordEncoder();
    }

}
