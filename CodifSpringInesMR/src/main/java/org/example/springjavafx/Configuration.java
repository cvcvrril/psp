package org.example.springjavafx;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import org.example.springjavafx.data.modelo.Cache;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//INFO: Meter aquí objetos para que no salte la mierda del Beans

@org.springframework.context.annotation.Configuration
public class Configuration {

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
