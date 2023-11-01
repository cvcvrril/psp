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

    /*Methods*/

    public Either<ErrorCOrderItem, List<OrderItem>> getOrders(int i) {
        return daOorderItemDB.getByOrderId(i);
    }


//    public Either<ErrorCOrder,Integer> addOrders(List<OrderItem> orderItemList, int i){
//        return daoOrdersXML.add(orderItemList,i);
//    }

    public Either<ErrorCOrderItem, Integer> updateOrders(OrderItem updatedOrderItm) {
        return daOorderItemDB.update(updatedOrderItm);
    }

//    public Either<ErrorCOrder, Integer> delOrders(int i) {
//        return daoOrdersXML.delete(i);
//    }


}
