package com.example.graphql.config;

import com.example.graphql.utils.Constantes;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;

@Log4j2
@Data
@org.springframework.context.annotation.Configuration
public class Configuration {
    @Value(Constantes.KEY_STORE_PASSWORD)
    private String keyStorePassword;
}
