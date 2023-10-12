package common;


import jakarta.inject.Singleton;
import lombok.Data;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.util.Properties;

@Data
@Log4j2
@Singleton
public class Configuration {
    private String pathApi;
    private Properties properties;

    public Configuration(){
        try {
            this.properties = new Properties();
            this.properties.load(getClass().getClassLoader()
                    .getResourceAsStream(Constantes.CONFIG_CONFIG_PROPERTIES));
            this.pathApi = properties.getProperty(Constantes.API_URL);
        } catch (IOException e) {
            log.error(e.getMessage(),e);
        }
    }

    public String getProperty(String key){
        return this.properties.getProperty(key);
    }
}
