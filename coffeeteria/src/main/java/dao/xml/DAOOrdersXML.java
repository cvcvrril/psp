package dao.xml;

import common.Configuration;
import io.vavr.control.Either;
import jakarta.xml.bind.*;
import lombok.extern.log4j.Log4j2;
import model.OrdersXML;
import model.errors.ErrorC;
import model.errors.ErrorCOrder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class DAOOrdersXML {

    public Either<ErrorCOrder, List<OrdersXML>> read() {
        List<OrdersXML> ordersXMLS = new ArrayList<>();
        try {
            JAXBContext context = JAXBContext.newInstance(OrdersXML.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            unmarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            Path xmlFile = Paths.get(Configuration.getInstance().getProperty("xmlOrdersPath"));
            ordersXMLS = (List<OrdersXML>) unmarshaller.unmarshal(Files.newInputStream(xmlFile));
        } catch (JAXBException | IOException e) {
            log.error(e.getMessage(), e);
            return Either.left(new ErrorCOrder(e.getMessage(),0));
        }
        return Either.right(ordersXMLS);
    }

    public Either<String, List<OrdersXML>> write(List<OrdersXML> ordersXMLS) {
        try {
            JAXBContext context = JAXBContext.newInstance(OrdersXML.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            Path xmlFile = Paths.get(Configuration.getInstance().getProperty("xmlOrdersPath"));
            marshaller.marshal(ordersXMLS, Files.newOutputStream(xmlFile));
        } catch (JAXBException | IOException e) {
            log.error(e.getMessage(), e);
            return Either.left(e.getMessage());
        }
        return Either.right(ordersXMLS);
    }

    //Read Unmarshall
    //Write Marshall

    public Either<ErrorCOrder, List<OrdersXML>> getAll() {
        return read();
    }

    public Either<ErrorCOrder, OrdersXML> get(int id) {
        List<OrdersXML> ordersXMLS = read().getOrElse(new ArrayList<>());
        for (OrdersXML ordersXML : ordersXMLS) {
            if (ordersXML.getOrderXML().get(0).getIdOrd() == id) {
                return Either.right(ordersXML);
            }
        }
        return Either.left(new ErrorCOrder("La orden no fue encontrada", 404));
    }

    public Either<ErrorCOrder, Integer> save(OrdersXML order) {
        List<OrdersXML> ordersXMLS = read().getOrElse(new ArrayList<>());
        ordersXMLS.add(order);
        write(ordersXMLS);
        return Either.right(1);

    }

    public Either<ErrorCOrder, Integer> update(OrdersXML order) {
        List<OrdersXML> ordersXMLS = read().getOrElse(new ArrayList<>());
        boolean found = false;
        for (int i = 0; i < ordersXMLS.size(); i++) {
            if (ordersXMLS.get(i).equals(order)) {
                ordersXMLS.set(i, order);
                found = true;
                break;
            }
        }
        if (found) {
            write(ordersXMLS);
            return Either.right(1);
        } else {
            return Either.left(new ErrorCOrder("La orden no existe", 0));
        }
    }

    public Either<ErrorCOrder, Integer> delete(OrdersXML order) {
        List<OrdersXML> ordersXMLS = read().getOrElse(new ArrayList<>());
        if (ordersXMLS.remove(order)) {
            write(ordersXMLS);
            return Either.right(1);
        }else {
            return Either.left(new ErrorCOrder("El order item no se pudo eliminar", 0));
        }

    }

}
