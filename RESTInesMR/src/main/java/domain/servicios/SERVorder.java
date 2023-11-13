package domain.servicios;

import dao.ConstantsDAO;
import dao.db.DAOorderDB;
import dao.modelo.Order;
import domain.modelo.excepciones.BadArgumentException;
import io.vavr.control.Either;
import jakarta.excepciones.ApiError;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;

import java.util.List;

@Log4j2
public class SERVorder {
    private final DAOorderDB daOorderDB;


    @Inject
    public SERVorder(DAOorderDB daOorderDB) {
        this.daOorderDB = daOorderDB;
    }

    public Either <ApiError,List<Order>> getAll() {
        return daOorderDB.getAll();
    }

    public Either<ApiError, Order> getOrder(String idParam) {
        try {
            Integer id = Integer.parseInt(idParam);
            return daOorderDB.get(id);
        }catch (NumberFormatException e){
            log.error(e.getMessage(),e);
            throw new BadArgumentException(ConstantsDAO.BAD_ARGUMENT_EXCEPTION);
        }


    }

    public Either<ApiError, Integer> add(Order order) {
        return daOorderDB.add(order);
    }

    public Either<ApiError, Integer> updateOrder(Order o) {
        return daOorderDB.update(o);
    }

    public Either<ApiError, Integer> delOrder(String idParam) {
        try {
            Integer id = Integer.parseInt(idParam);
            return daOorderDB.delete(id);
        }catch (NumberFormatException e){
            log.error(e.getMessage(),e);
            throw new BadArgumentException(ConstantsDAO.BAD_ARGUMENT_EXCEPTION);
        }

    }

}
