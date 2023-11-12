package services;

import dao.db.DAOorderItemDB;
import dao.spring.DAOorderItemSpring;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import model.OrderItem;
import model.errors.ErrorCOrderItem;

import java.util.List;

public class SERVorderItem {
    private final DAOorderItemDB daOorderItemDB;
    private final DAOorderItemSpring daOorderItemSpring;

    @Inject
    public SERVorderItem(DAOorderItemDB daOorderItemDB, DAOorderItemSpring daOorderItemSpring) {
        this.daOorderItemDB = daOorderItemDB;
        this.daOorderItemSpring = daOorderItemSpring;
    }

    public Either<ErrorCOrderItem, List<OrderItem>> getOrders(int i) {
        return daOorderItemDB.getByOrderId(i);
    }

    public Either<ErrorCOrderItem, List<OrderItem>> getAll(){
        //return daOorderItemDB.getAll();
        return daOorderItemSpring.getAll();
    }


}
