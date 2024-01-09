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

import java.io.IOException;

@WebServlet(name = "ServletActivation", value = "/Activation")
@ServletSecurity(
        @HttpConstraint(transportGuarantee = ServletSecurity.TransportGuarantee.NONE)
)
public class ServletActivation extends HttpServlet {

    private final CredencialServicio servicio;

    @Inject
    public ServletActivation(CredencialServicio servicio) {
        this.servicio = servicio;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String codigoAuth = request.getParameter("codigo");
        Credencial credencialAuth = servicio.getCredencialCode(codigoAuth);
        if (credencialAuth != null){
            credencialAuth.setAutentificado(true);
            response.getWriter().println("Cuenta activada");
        } else {
            throw new WrongObjectException("La credencial que se ha tratado de activar no existe");
        }
    }




}
