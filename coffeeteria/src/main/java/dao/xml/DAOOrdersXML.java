package dao.xml;

import common.Configuration;
import io.vavr.control.Either;
import jakarta.xml.bind.*;
import lombok.extern.log4j.Log4j2;
import model.OrdersXML;
import model.errors.ErrorC;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class DAOOrdersXML {

    public Either<String, List<OrdersXML>> read() {
        List<OrdersXML> ordersXMLS = new ArrayList<>();
        try {
            JAXBContext context = JAXBContext.newInstance(OrdersXML.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            unmarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            Path xmlFile = Paths.get(Configuration.getInstance().getProperty("xmlOrdersPath"));
            ordersXMLS = (List<OrdersXML>) unmarshaller.unmarshal(Files.newInputStream(xmlFile));
        } catch (JAXBException | IOException e) {
            log.error(e.getMessage(), e);
            return Either.left(e.getMessage());
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

    public Either<ErrorC, Integer> save(OrdersXML order) {
        List<OrdersXML> ordersXMLS = read().getOrElse(new ArrayList<>());
        ordersXMLS.add(order);
        write(ordersXMLS);
        return Either.right(1);

    }

    public Either<ErrorC, Integer> update(OrdersXML order){


        return null;
    }

    public Either<ErrorC, Integer> delete(OrdersXML order) {
        List<OrdersXML> ordersXMLS = read().getOrElse(new ArrayList<>());
        ordersXMLS.removeIf(ordersXML -> ordersXML.equals(order));
        write(ordersXMLS);
        return Either.right(1);
    }

}
