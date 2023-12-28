package jakarta.servlet;

import domain.servicios.MandarMail;
import jakarta.mail.MessagingException;
import jakarta.servlet.annotation.HttpConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.utils.RandomBytesGenerator;

import java.io.IOException;

@WebServlet(name = "ServletMail", value = "/Mail")
@ServletSecurity(
        @HttpConstraint(transportGuarantee = ServletSecurity.TransportGuarantee.NONE)
)
public class ServletMail extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MandarMail mandarMail = new MandarMail();
        try {

            //TODO: A PARTIR DE ESTO ARMAR LO DE LA ACTIVACIÓN (CON EL SERVLET CAMBIAR EL BOOLEANO DE LA TABLA DEL USUARIO)

            RandomBytesGenerator randomBytesGenerator = new RandomBytesGenerator();
            String random = randomBytesGenerator.randomBytes();

            mandarMail.generateAndSendEmail("ines07mar@gmail.com", "<html><body><a href=\"http://localhost:8080/videojuegosServidor-1.0-SNAPSHOT/Activation?codigo="+ random+"\">Activación</a></body></html>", "Prueba activacion");
            response.getWriter().println("Correo enviado");
        } catch (MessagingException e) {
            response.getWriter().println(e.getMessage());
        }
        //request.logout();
        //request.getSession().removeAttribute("USERLOGIN");
        //response.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
