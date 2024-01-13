package jakarta.servlet;

import dao.modelo.Credencial;
import domain.excepciones.WrongObjectException;
import domain.servicios.CredencialServicio;
import jakarta.ConstantsJakarta;
import jakarta.inject.Inject;
import jakarta.servlet.annotation.HttpConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.utils.RandomBytesGenerator;

import java.io.IOException;

@WebServlet(name = ConstantsJakarta.SERVLET_CHANGE_PASSWORD, value = ConstantsJakarta.CHANGE_PASSWORD)
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
        String codigoChangePassword = request.getParameter(ConstantsJakarta.CODIGO);
        Credencial credencialChangePassword = servicio.getCredencialCode(codigoChangePassword);
        if (credencialChangePassword != null){
            String newPassword = generator.randomNewPassword();
            credencialChangePassword.setPassword(newPassword);
            servicio.actualizarPassword(credencialChangePassword);
            response.getWriter().println(ConstantsJakarta.CONTRASENA_CAMBIADA);
            response.getWriter().println(ConstantsJakarta.NUEVA_CONTRASENA + newPassword);
        } else {
            throw new WrongObjectException(ConstantsJakarta.HA_HABIDO_UN_ERROR_AL_TRATAR_DE_CAMBIAR_LA_CONTRASENA);
        }
    }

}
