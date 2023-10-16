package services;

import dao.xml.DAOOrdersXML;
import io.vavr.control.Either;
import model.OrdersXML;
import model.errors.ErrorCOrder;

import java.util.List;

public class SERVorderItem {

    /*Atb*/

    private final DAOOrdersXML daoOrdersXML;

    /*Builder*/

    public SERVorderItem(DAOOrdersXML daoOrdersXML) {
        this.daoOrdersXML = daoOrdersXML;
    }

    /*Methods*/


    public Either<ErrorCOrder, List<OrdersXML>> getOrders() {
        return daoOrdersXML.getAll();
    }

    public Either<ErrorCOrder, OrdersXML> getOrders(int i){
        return daoOrdersXML.get(i);
    }

    public Either<ErrorCOrder, Integer> saveOrders(OrdersXML ordersXML){
        return daoOrdersXML.save(ordersXML);
    }

    public Either<ErrorCOrder, Integer> updateOrders(OrdersXML ordersXML){
        return daoOrdersXML.update(ordersXML);
    }

    public Either<ErrorCOrder, Integer> delOrders(OrdersXML ordersXML){
        return daoOrdersXML.delete(ordersXML);
    }

}
