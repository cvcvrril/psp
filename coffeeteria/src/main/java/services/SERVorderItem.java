package services;

import dao.xml.DAOOrdersXML;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import model.OrderItem;
import model.xml.OrderItemXML;
import model.xml.OrdersXML;
import model.errors.ErrorCOrder;

import java.util.ArrayList;
import java.util.List;

public class SERVorderItem {

    /*Atb*/

    private final DAOOrdersXML daoOrdersXML;

    /*Builder*/

    @Inject
    public SERVorderItem(DAOOrdersXML daoOrdersXML) {
        this.daoOrdersXML = daoOrdersXML;
    }

    /*Methods*/


    public Either<ErrorCOrder, List<OrderItem>> getOrders(int i) {
        return daoOrdersXML.getAll(i);
    }

    public Either<ErrorCOrder,Integer> addOrders(List<OrderItem> orderItemList, int i){
        return daoOrdersXML.add(orderItemList,i);
    }

    public Either<ErrorCOrder, Integer> updateOrders(List<OrderItem> orderItemList, int i) {
        return daoOrdersXML.update(orderItemList,i);
    }

    public Either<ErrorCOrder, Integer> delOrders(int i) {
        return daoOrdersXML.delete(i);
    }


}
