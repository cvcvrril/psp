package jakarta.servlet;

import dao.modelo.Credencial;
import domain.excepciones.WrongObjectException;
import domain.servicios.CredencialServicio;
import jakarta.inject.Inject;
import jakarta.servlet.annotation.HttpConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.utils.RandomBytesGenerator;

import java.io.IOException;

@WebServlet(name = "ServletChangePassword", value = "/ChangePassword")
@ServletSecurity(
        @HttpConstraint(transportGuarantee = ServletSecurity.TransportGuarantee.NONE)
)
public class ServletForgotPassword extends HttpServlet {

    private final CredencialServicio servicio;
    private final RandomBytesGenerator generator;

    @Inject
    public ServletForgotPassword(CredencialServicio servicio, RandomBytesGenerator generator) {
        this.servicio = servicio;
        this.generator = generator;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String codigoChangePassword = request.getParameter("codigo");
        Credencial credencialChangePassword = servicio.getCredencialCode(codigoChangePassword);
        if (credencialChangePassword != null){
            String newPassword = generator.randomNewPassword();
            credencialChangePassword.setPassword(newPassword);
            servicio.actualizarPassword(credencialChangePassword);
            response.getWriter().println("Contraseña cambiada");
            response.getWriter().println("Nueva contraseña: " + newPassword);
        } else {
            throw new WrongObjectException("La credencial que se ha tratado de activar no existe");
        }
    }

}
