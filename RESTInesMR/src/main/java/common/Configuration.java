package common;

import jakarta.inject.Singleton;
import lombok.Data;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.util.Properties;

@Singleton
@Data
@Log4j2
public class Configuration {

    private Properties ptxt;
    private Properties pxml;
    private Properties psql;

    private String pathDataCustomers;
    private String pathDataOrders;
    private String pathXMLOrders;
    private String pathSQL;
    private String userSQL;
    private String passSQL;
    private String driver;

    public Configuration(){
        try{
            ptxt = new Properties();
            ptxt.load(getClass().getClassLoader().getResourceAsStream(ConstantsCommon.CONFIG_PROPERTIES_TXT));
            this.pathDataCustomers = ptxt.getProperty(ConstantsCommon.PATH_DATA_CUSTOMERS);
            this.pathDataOrders = ptxt.getProperty(ConstantsCommon.PATH_DATA_ORDERS);
            this.pathSQL = ptxt.getProperty(ConstantsCommon.PATH_DB);
            this.userSQL = ptxt.getProperty(ConstantsCommon.USER_DB);
            this.passSQL = ptxt.getProperty(ConstantsCommon.PASS_DB);
            this.driver = ptxt.getProperty(ConstantsCommon.DRIVER);
        } catch (IOException e) {
            log.error(e.getMessage(),e);
            throw new RuntimeException();
        }
    }
}
