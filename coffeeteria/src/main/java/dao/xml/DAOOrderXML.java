package dao.xml;

import common.Configuration;
import jakarta.xml.bind.*;
import lombok.extern.log4j.Log4j2;
import model.OrderXML;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Log4j2
public class DAOOrderXML {

    public DAOOrderXML() {

        try {
            JAXBContext context = JAXBContext.newInstance(OrderXML.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            Unmarshaller unmarshaller = context.createUnmarshaller();
            Path xmlFile = Paths
                    .get(Configuration.getInstance().getProperty("xmlOrdersPath"));

            OrderXML empList = (OrderXML) unmarshaller.unmarshal(Files.newInputStream(xmlFile));
            marshaller.marshal(empList, System.out);

            marshaller.marshal(empList, Files.newOutputStream(xmlFile));

            marshaller.marshal(empList, System.out);
        } catch (JAXBException | IOException e) {
            log.error(e.getMessage(), e);
        }
    }
}
