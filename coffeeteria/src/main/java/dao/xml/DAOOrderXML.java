package dao.xml;

import common.Configuration;
import io.vavr.control.Either;
import jakarta.xml.bind.*;
import lombok.extern.log4j.Log4j2;
import model.OrderXML;
import model.OrdersXML;
import model.errors.ErrorCOrder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

@Log4j2
public class DAOOrderXML {

    public Either<String, List<OrdersXML>> write(){
        try {
            JAXBContext context = JAXBContext.newInstance(OrdersXML.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Path xmlFile = Paths
                    .get(Configuration.getInstance().getProperty("xmlOrdersPath"));

            OrdersXML empList = (OrdersXML) unmarshaller.unmarshal(Files.newInputStream(xmlFile));
            marshaller.marshal(empList, Files.newOutputStream(xmlFile));
        } catch (JAXBException | IOException e) {
            log.error(e.getMessage(), e);
            return Either.left(e.getMessage());
        }
        return Either.right(Collections.emptyList());
    }

    public Either<String, List<OrdersXML>> read(){

    }

}
