package dao.xml;

import common.Configuration;
import io.vavr.control.Either;
import jakarta.xml.bind.*;
import lombok.extern.log4j.Log4j2;
import model.OrderItem;
import model.xml.OrderItemXML;
import model.xml.OrderXML;
import model.xml.OrdersXML;
import model.errors.ErrorCOrder;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class DAOOrdersXML {

    public Either<ErrorCOrder, OrdersXML> read() {
        Either<ErrorCOrder, OrdersXML> either;
        try {
            JAXBContext context = JAXBContext.newInstance(OrdersXML.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            Path xmlFile = Paths.get(Configuration.getInstance().getPropertyXML("xmlOrdersPath"));

            OrdersXML listXml = (OrdersXML) unmarshaller.unmarshal(Files.newInputStream(xmlFile));

            either = Either.right(listXml);

        } catch (JAXBException | IOException e) {
            either = Either.left(new ErrorCOrder(e.getMessage(), 0));
            log.error(e.getMessage());
        }
        return either;
    }

    public Either<ErrorCOrder, Integer> write(OrdersXML ordersXML) {
        Either<ErrorCOrder, Integer> either = null;
        try {
            JAXBContext context = JAXBContext.newInstance(OrdersXML.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            Path xmlFile = Paths.get(Configuration.getInstance().getPropertyXML("xmlOrdersPath"));

            marshaller.marshal(ordersXML, Files.newOutputStream(xmlFile));

        } catch (JAXBException | IOException e) {
            either = Either.left(new ErrorCOrder(e.getMessage(), 0));
            log.error(e.getMessage());
        }
        return either;
    }



    //Read Unmarshall
    //Write Marshall

    public Either<ErrorCOrder, List<OrderItem>> getAll(int orderId) {
        Either<ErrorCOrder, List<OrderItem>> either;
        List<OrderItem> list = new ArrayList<>();

        Either<ErrorCOrder, OrdersXML> listXml = read();
        if (listXml.isRight()) {
            //OrderXML order = listXml.get().getList().stream().filter(i -> i.getId() == orderId).findFirst().orElse(null);
            OrderXML order = listXml.get().getOrderXMLList().stream().filter(i ->i.getId() == orderId).findFirst().orElse(null);
            if (order != null) {
                for (OrderItemXML orderItemXML : order.getOrderItem()) {
                    list.add(new OrderItem(0, orderId, "", 0));
                }
                either = Either.right(list);
            } else {
                either = Either.left(new ErrorCOrder("", 0));
            }

        } else {
            either = Either.left(listXml.getLeft());
        }
        return either;
    }

    public Either<ErrorCOrder,Integer> add(List<OrderItem> orderItemList, int orderId){
        Either<ErrorCOrder, Integer> add;
        Either<ErrorCOrder, OrdersXML> read = read();
        if (read.isRight()) {
            //add order
            List<OrderItemXML> itemsXML = new ArrayList<>();
            for (OrderItem orderItem : orderItemList) {
                itemsXML.add(new OrderItemXML("", orderItem.getQuantity()));
            }
            OrderXML orderXML = new OrderXML(orderId, itemsXML);
            List<OrderXML> orders = read.get().getOrderXMLList();
            orders.add(orderXML);

            //write order
            Either<ErrorCOrder, Integer> write = write(new OrdersXML(orders));
            if (write.isRight()) {
                add = Either.right(orders.size());
            } else {
                add = Either.left(write.getLeft());
            }
        } else {
            add = Either.left(read.getLeft());
        }
        return add;
    }

    public Either<ErrorCOrder, Integer> update(List<OrderItem> orderItemList, int orderId) {
        Either<ErrorCOrder, Integer> update;
        Either<ErrorCOrder, OrdersXML> read = read();
        if (read.isRight()) {
            Either<ErrorCOrder, List<OrderItem>> oldItems = getAll(orderId);
            List<OrderXML> orders = read.get().getOrderXMLList();
            if (oldItems.isRight()) {
                //delete old order
                List<OrderItemXML> oldItemsXML = new ArrayList<>();
                for (OrderItem orderItem : oldItems.get()) {
                    oldItemsXML.add(new OrderItemXML("", orderItem.getQuantity()));
                }
                OrderXML oldOrder = new OrderXML(orderId, oldItemsXML);
                orders.remove(oldOrder);

                //add updated order
                List<OrderItemXML> newItemsXML = new ArrayList<>();
                for (OrderItem orderItem : orderItemList) {
                    newItemsXML.add(new OrderItemXML("", orderItem.getQuantity()));
                }
                OrderXML newOrder = new OrderXML(orderId, newItemsXML);
                orders.add(newOrder);

                //write
                Either<ErrorCOrder, Integer> write = write(new OrdersXML(orders));
                if (write.isRight()) {
                    update = Either.right(write.get());
                } else {
                    update = Either.left(write.getLeft());
                }
            } else {//order not in list
                update = Either.left(oldItems.getLeft());
            }
        } else {//failure to read orders
            update = Either.left(read.getLeft());
        }
        return update;
    }

    public Either<ErrorCOrder, Integer> delete(int orderId) {
        Either<ErrorCOrder, Integer> delete;
        Either<ErrorCOrder, OrdersXML> read = read();
        if (read.isRight()) {
            List<OrderXML> orders = read.get().getOrderXMLList();
            Either<ErrorCOrder, List<OrderItem>> deleteList = getAll(orderId);

            if(deleteList.isRight()){
                List<OrderItemXML> deleteItemsXML = new ArrayList<>();
                for(OrderItem orderItem : deleteList.get()){
                    deleteItemsXML.add(new OrderItemXML("",orderItem.getQuantity()));
                }
                OrderXML deleteOrder = new OrderXML(orderId, deleteItemsXML);
                orders.remove(deleteOrder);

                //write
                Either<ErrorCOrder, Integer> write = write(new OrdersXML(orders));
                if (write.isRight()) {
                    delete = Either.right(orders.size());
                } else {
                    delete = Either.left(write.getLeft());
                }
            } else {
                delete = Either.left(deleteList.getLeft());
            }
        } else {
            delete = Either.left(read.getLeft());
        }
        return delete;

    }

}
