package jakarta.servlet;

import domain.servicios.MandarMail;
import jakarta.mail.MessagingException;
import jakarta.servlet.annotation.HttpConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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
            mandarMail.generateAndSendEmail("ines07mar@gmail.com", "Coño pues sí funciona esto de mandar correos", "Prueba");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        request.logout();
        //request.getSession().removeAttribute("USERLOGIN");
        response.setStatus(HttpServletResponse.SC_NO_CONTENT);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
