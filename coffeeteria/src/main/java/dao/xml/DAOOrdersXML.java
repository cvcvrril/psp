package dao.xml;

import common.Configuration;
import io.vavr.control.Either;
import jakarta.xml.bind.*;
import lombok.extern.log4j.Log4j2;
import model.OrderItemXML;
import model.OrderXML;
import model.OrdersXML;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class DAOOrdersXML {

    public Either<String, List<OrdersXML>> write() {
        List<OrdersXML> ordersXMLS;
        try {
            JAXBContext context = JAXBContext.newInstance(OrdersXML.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Path xmlFile = Paths
                    .get(Configuration.getInstance().getProperty("xmlOrdersPath"));

            ordersXMLS = (List<OrdersXML>) unmarshaller.unmarshal(Files.newInputStream(xmlFile));
            marshaller.marshal(ordersXMLS, Files.newOutputStream(xmlFile));
        } catch (JAXBException | IOException e) {
            log.error(e.getMessage(), e);
            return Either.left(e.getMessage());
        }
        return Either.right(ordersXMLS);
    }


    public Either<String, List<OrdersXML>> read() {
        List<OrdersXML> ordersXMLS = new ArrayList<>();
        try {

            //Get the DOM Builder Factory
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

            //Get the DOM Builder
            DocumentBuilder builder = factory.newDocumentBuilder();

            //Load and Parse the XML document
            //document contains the complete XML as a Tree.
            Document document = builder.parse(Files.newInputStream(Paths
                    .get(Configuration.getInstance().getProperty("xmlOrdersPath"))));

            //Writing to the file
            // Generates the DOM document and saves it into an XML file
            Source source = new DOMSource(document);

            Result result = new StreamResult(Files.newOutputStream(Paths
                    .get(Configuration.getInstance().getProperty("xmlOrdersPath")))); // name of the file

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);

        } catch (ParserConfigurationException | IOException | TransformerException | SAXException e) {
            log.error(e.getMessage(), e);
            return Either.left(e.getMessage());
        }
        return Either.right(ordersXMLS);
    }
}
