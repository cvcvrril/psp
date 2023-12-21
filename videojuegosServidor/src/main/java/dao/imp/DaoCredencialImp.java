package dao.imp;

import dao.ConstantsDAO;
import dao.DaoCredencial;
import dao.data.StaticLists;
import dao.modelo.Credencial;
import domain.excepciones.BaseCaidaException;
import io.vavr.control.Either;
import jakarta.excepciones.ApiError;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class DaoCredencialImp implements DaoCredencial {

    //TODO: MONTAR EL LOGIN


    @Override
    public Either<ApiError, Credencial> userLogged(Credencial credencial) {
        Either<ApiError, Credencial> res;
        try {
            Credencial c = StaticLists.listaCredenciales.stream().filter(credencial1 -> credencial1.getUser().equals(credencial.getUser())).findFirst().orElse(null);
            res = Either.right(c);
        }catch (Exception e){
            log.error(e.getMessage(), e);
            throw new BaseCaidaException(ConstantsDAO.BASE_CAIDA_EXCEPTION);
        }
        return res;
    }

}
