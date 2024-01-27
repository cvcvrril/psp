package org.example.springjavafx;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.example.springjavafx.common.Constantes;
import org.example.springjavafx.data.modelo.Cache;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Properties;

//INFO: Meter aquí objetos para que no salte la mierda del Beans

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
    public FXMLLoader createLoader(ApplicationContext context) {
        FXMLLoader loader = new FXMLLoader();
        loader.setControllerFactory(context::getBean);
        return loader;
    }

    @Bean
    public PasswordEncoder createPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public Cache passwordCache(){
        return new Cache();
    }



}
