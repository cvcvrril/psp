package com.example.graphql.config;


import com.example.graphql.utils.Constantes;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Properties;

@Log4j2
@Data
@org.springframework.context.annotation.Configuration
public class Configuration {

    private Properties configProperties;
    private String keyStorePassword;

    public Configuration(){
        try {
            configProperties = new Properties();
            configProperties.load(getClass().getClassLoader().getResourceAsStream(Constantes.NAME));
            this.keyStorePassword = configProperties.getProperty(Constantes.KEY_STORE_PASSWORD);
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
    }

    @Bean
    public PasswordEncoder createPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
