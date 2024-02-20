package com.example.graphql.config;

import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;

@Log4j2
@Data
@org.springframework.context.annotation.Configuration
public class Configuration {
    @Value("${KeyStorePassword}")
    private String keyStorePassword;
}
