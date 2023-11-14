package dao.imp;

import io.vavr.control.Either;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import lombok.extern.log4j.Log4j2;
import model.MenuItem;
import model.Order;
import model.OrderItem;
import model.errors.ErrorCOrder;
import model.xml.MenuItemXML;
import model.xml.OrderItemXML;
import model.xml.OrderXML;


import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class DAOorderBackupIMP {

    private Path basePath = Path.of("data");

    private Path getFilePath(int customerId) {
        String fileName = "customer_" + customerId + ".xml";
        return basePath.resolve(fileName);
    }

    public Either<ErrorCOrder, Void> saveOrderToXML(Order order) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(OrderXML.class);
            Marshaller marshaller = jaxbContext.createMarshaller();

            OrderXML orderXML = new OrderXML(order.getIdOrd(), convertOrderItemsToXML(order.getOrderItems()));
            StringWriter writer = new StringWriter();
            marshaller.marshal(orderXML, writer);

            Path filePath = getFilePath(order.getIdCo());
            Files.write(filePath, writer.toString().getBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

            return Either.right(null); // Operación exitosa
        } catch (JAXBException | IOException e) {
            log.error(e.getMessage(), e);
            return Either.left(new ErrorCOrder("Error al guardar la orden en XML", 0));
        }
    }

    private List<OrderItemXML> convertOrderItemsToXML(List<OrderItem> orderItems) {
        List<OrderItemXML> orderItemXMLList = new ArrayList<>();
        for (OrderItem orderItem : orderItems) {
            MenuItemXML menuItemXML = new MenuItemXML(orderItem.getMenuItemObject().getNameMItem());
            OrderItemXML orderItemXML = new OrderItemXML(menuItemXML.getMenuItemName(), orderItem.getQuantity());
            orderItemXMLList.add(orderItemXML);
        }
        return orderItemXMLList;
    }

    private List<OrderItem> convertOrderItemsFromXML(List<OrderItemXML> orderItemXMLList) {
        List<OrderItem> orderItems = new ArrayList<>();
        for (OrderItemXML orderItemXML : orderItemXMLList) {
            MenuItem menuItem = new MenuItem(0, orderItemXML.getMenuItem(), "", 0);
            OrderItem orderItem = new OrderItem(0, 0, 0, orderItemXML.getQuantity(), menuItem);
            orderItems.add(orderItem);
        }
        return orderItems;
    }
}


