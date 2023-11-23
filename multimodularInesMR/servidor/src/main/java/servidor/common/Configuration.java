package servidor.common;


import jakarta.inject.Singleton;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import servidor.dao.ConstantsDAO;
import servidor.domain.modelo.excepciones.BaseCaidaException;

import java.io.IOException;
import java.util.Properties;

@Singleton
@Data
@Log4j2
public class Configuration {

    private Properties ptxt;
    private String pathSql;
    private String userSql;
    private String passSql;
    private String driver;

    public Configuration() {
        try {
            ptxt = new Properties();
            ptxt.load(getClass().getClassLoader().getResourceAsStream(ConstantsCommon.CONFIG_PROPERTIES_TXT));
            this.pathSql = ptxt.getProperty(ConstantsCommon.PATH_DB);
            this.userSql = ptxt.getProperty(ConstantsCommon.USER_DB);
            this.passSql = ptxt.getProperty(ConstantsCommon.PASS_DB);
            this.driver = ptxt.getProperty(ConstantsCommon.DRIVER);
        }catch (IOException e){
            log.error(e.getMessage(),e);
            throw new BaseCaidaException(ConstantsDAO.BASE_CAIDA_EXCEPTION);
        }
    }


}
