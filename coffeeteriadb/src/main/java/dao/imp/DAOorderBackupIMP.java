package dao.imp;

import io.vavr.control.Either;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import lombok.extern.log4j.Log4j2;

import model.Order;
import model.OrderItem;
import model.errors.ErrorCOrder;
import model.xml.MenuItemXML;
import model.xml.OrderItemXML;
import model.xml.OrderXML;
import model.xml.OrdersXML;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class DAOorderBackupIMP {

    private final Path basePath = Path.of("data");

    private Path getFilePath(int customerId) {
        String fileName = "customer_" + customerId + ".xml";
        return basePath.resolve(fileName);
    }

    public Either<ErrorCOrder, Void> saveOrderToXML(List<Order> orderList, int customerId) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(OrderXML.class);
            Marshaller marshaller = jaxbContext.createMarshaller();

            OrdersXML orderXML = new OrdersXML(parseOrdertoXML(orderList));

            marshaller.marshal(orderXML,Files.newOutputStream(basePath));

            Path filePath = getFilePath(orderList.get(customerId).getIdCo());
            Files.newInputStream(filePath, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            return Either.right(null);
        } catch (JAXBException | IOException e) {
            log.error(e.getMessage(), e);
            return Either.left(new ErrorCOrder("Error al guardar la orden en XML", 0));
        }
    }

    private List<OrderXML> parseOrdertoXML(List<Order> orderList){
        List<OrderXML> orderXMLList = new ArrayList<>();
        for (Order order : orderList){
            OrderXML orderXML = new OrderXML(order.getIdOrd(),parseOrderItemsToXML(order.getOrderItems()));
            orderXMLList.add(orderXML);
        }
        return orderXMLList;
    }

    private List<OrderItemXML> parseOrderItemsToXML(List<OrderItem> orderItems) {
        List<OrderItemXML> orderItemXMLList = new ArrayList<>();
        for (OrderItem orderItem : orderItems) {
            MenuItemXML menuItemXML = new MenuItemXML(orderItem.getMenuItemObject().getNameMItem());
            OrderItemXML orderItemXML = new OrderItemXML(menuItemXML.getMenuItemName(), orderItem.getQuantity());
            orderItemXMLList.add(orderItemXML);
        }
        return orderItemXMLList;
    }
}


