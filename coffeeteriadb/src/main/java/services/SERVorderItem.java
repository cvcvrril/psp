package services;

import dao.db.DAOorderItemDB;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import model.OrderItem;
import model.errors.ErrorCOrderItem;

import java.util.List;

public class SERVorderItem {
    private final DAOorderItemDB daOorderItemDB;

    @Inject
    public SERVorderItem(DAOorderItemDB daOorderItemDB) {
        this.daOorderItemDB = daOorderItemDB;
    }

    public Either<ErrorCOrderItem, List<OrderItem>> getOrders(int i) {
        return daOorderItemDB.getByOrderId(i);
    }

    public Either<ErrorCOrderItem, List<OrderItem>> getAll(){
        return daOorderItemDB.getAll();
    }


}
