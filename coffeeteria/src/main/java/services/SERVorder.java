package services;

import dao.imp.DAOorderIMP;
import io.vavr.control.Either;
import model.Order;
import model.errors.ErrorC;

import java.util.List;

public class SERVorder {

    /*Atributos*/

    private DAOorderIMP daOorderIMP;

    /*Constructor*/

    public SERVorder(DAOorderIMP daOorderIMP) {
        this.daOorderIMP = daOorderIMP;
    }

    /*Métodos*/

    public List<Order> getOrders(){
        return daOorderIMP.getOrders();
    }

    public Either<ErrorC, List<Order>> getOrders(int i){
        return daOorderIMP.getOrder(i);
    }



}
