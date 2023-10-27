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
import java.util.UUID;

@Log4j2
public class DAOOrdersXML {

    /*Read*/

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

    /*Write*/

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


    /*Para ID único*/
    public static class UniqueIDGenerator {
        public static int generateUniqueID() {
            return Integer.parseInt(UUID.randomUUID().toString());
        }

    }

    public Either<ErrorCOrder, List<OrderItem>> getAll(int orderId) {
        Either<ErrorCOrder, List<OrderItem>> res;
        List<OrderItem> list = new ArrayList<>();
        Either<ErrorCOrder, OrdersXML> ordersXMLS = read();
        if (ordersXMLS.isRight()) {
            OrderXML order = ordersXMLS.get().getOrderXMLList().stream().filter(i -> i.getId() == orderId).findFirst().orElse(null);
            if (order != null) {
                for (OrderItemXML orderItemXML : order.getOrderItem()) {
                    int itemMenuItem = Integer.parseInt(orderItemXML.getMenuItem());
                    int itemQuantity = orderItemXML.getQuantity();
                    int uniqueID = UniqueIDGenerator.generateUniqueID();
                    list.add(new OrderItem(uniqueID, orderId, itemMenuItem, itemQuantity));
                }
                res = Either.right(list);
            } else {
                res = Either.left(new ErrorCOrder("Error al recibir los order_items", 0));
            }
        } else {
            res = Either.left(ordersXMLS.getLeft());
        }
        return res;
    }

    /*Add*/

    public Either<ErrorCOrder, Integer> add(List<OrderItem> orderItemList, int orderId) {
        Either<ErrorCOrder, Integer> res;
        Either<ErrorCOrder, OrdersXML> read = read();
        if (read.isRight()) {
            List<OrderItemXML> itemsXML = new ArrayList<>();
            for (OrderItem orderItem : orderItemList) {
                itemsXML.add(new OrderItemXML("mitem", orderItem.getQuantity()));
            }
            OrderXML orderXML = new OrderXML(orderId, itemsXML);
            List<OrderXML> orders = read.get().getOrderXMLList();
            orders.add(orderXML);
            Either<ErrorCOrder, Integer> write = write(new OrdersXML(orders));
            if (write.isRight()) {
                res = Either.right(orders.size());
            } else {
                res = Either.left(write.getLeft());
            }
        } else {
            res = Either.left(read.getLeft());
        }
        return res;
    }

    /*Update*/

    public Either<ErrorCOrder, Integer> update(List<OrderItem> orderItemList, int orderId) {
        Either<ErrorCOrder, Integer> res;
        Either<ErrorCOrder, OrdersXML> read = read();
        if (read.isRight()) {
            Either<ErrorCOrder, List<OrderItem>> oldItems = getAll(orderId);
            List<OrderXML> orders = read.get().getOrderXMLList();
            if (oldItems.isRight()) {
                List<OrderItemXML> oldItemsXML = new ArrayList<>();
                for (OrderItem orderItem : oldItems.get()) {
                    oldItemsXML.add(new OrderItemXML("", orderItem.getQuantity()));
                }
                OrderXML oldOrder = new OrderXML(orderId, oldItemsXML);
                orders.remove(oldOrder);
                List<OrderItemXML> newItemsXML = new ArrayList<>();
                for (OrderItem orderItem : orderItemList) {
                    newItemsXML.add(new OrderItemXML("", orderItem.getQuantity()));
                }
                OrderXML newOrder = new OrderXML(orderId, newItemsXML);
                orders.add(newOrder);
                Either<ErrorCOrder, Integer> write = write(new OrdersXML(orders));
                if (write.isRight()) {
                    res = Either.right(write.get());
                } else {
                    res = Either.left(write.getLeft());
                }
            } else {
                res = Either.left(oldItems.getLeft());
            }
        } else {
            res = Either.left(read.getLeft());
        }
        return res;
    }

    /*Delete*/

    public Either<ErrorCOrder, Integer> delete(int orderId) {
        Either<ErrorCOrder, Integer> res;
        Either<ErrorCOrder, OrdersXML> read = read();
        if (read.isRight()) {
            List<OrderXML> orders = read.get().getOrderXMLList();
            Either<ErrorCOrder, List<OrderItem>> deleteList = getAll(orderId);
            if (deleteList.isRight()) {
                List<OrderItemXML> deleteItemsXML = new ArrayList<>();
                for (OrderItem orderItem : deleteList.get()) {
                    deleteItemsXML.add(new OrderItemXML("", orderItem.getQuantity()));
                }
                OrderXML deleteOrder = new OrderXML(orderId, deleteItemsXML);
                orders.remove(deleteOrder);
                Either<ErrorCOrder, Integer> write = write(new OrdersXML(orders));
                if (write.isRight()) {
                    res = Either.right(orders.size());
                } else {
                    res = Either.left(write.getLeft());
                }
            } else {
                res = Either.left(deleteList.getLeft());
            }
        } else {
            res = Either.left(read.getLeft());
        }
        return res;

    }

}
