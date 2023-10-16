package services;

import dao.xml.DAOOrdersXML;
import model.OrdersXML;

import java.util.List;

public class SERVorderItem {

    /*Atb*/

    private final DAOOrdersXML daoOrdersXML;

    /*Builder*/

    public SERVorderItem(DAOOrdersXML daoOrdersXML) {
        this.daoOrdersXML = daoOrdersXML;
    }

    /*Methods*/

    public List<OrdersXML> getOrders() {
        return daoOrdersXML.read().getOrNull();
    }



}
