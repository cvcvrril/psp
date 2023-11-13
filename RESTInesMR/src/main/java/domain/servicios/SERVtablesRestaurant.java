    package domain.servicios;

    import dao.ConstantsDAO;
    import dao.db.DAOtablesDB;
    import dao.modelo.TableRestaurant;
    import domain.modelo.excepciones.BadArgumentException;
    import io.vavr.control.Either;
    import jakarta.excepciones.ApiError;
    import jakarta.inject.Inject;
    import lombok.extern.log4j.Log4j2;

    import java.util.List;

    @Log4j2
    public class SERVtablesRestaurant {

        private final DAOtablesDB daOtablesDB;

        @Inject
        public SERVtablesRestaurant(DAOtablesDB daOtablesDB) {
            this.daOtablesDB = daOtablesDB;
        }

        public Either<ApiError, List<TableRestaurant>> getAll(){
            return daOtablesDB.getAll();
        }

        public Either<ApiError, TableRestaurant> get (String idParam){
            try {
                Integer id = Integer.parseInt(idParam);
                return daOtablesDB.get(id);
            }catch (NumberFormatException e){
                log.error(e.getMessage(),e);
                throw new BadArgumentException(ConstantsDAO.BAD_ARGUMENT_EXCEPTION);
            }
        }
    }
