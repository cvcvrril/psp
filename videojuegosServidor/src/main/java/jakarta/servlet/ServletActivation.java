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

import java.io.IOException;

@WebServlet(name = ConstantsJakarta.SERVLET_ACTIVATION, value = ConstantsJakarta.ACTIVATION)
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
        String codigoAuth = request.getParameter(ConstantsJakarta.CODIGO);
        Credencial credencialAuth = servicio.getCredencialCode(codigoAuth);
        if (credencialAuth != null){
            credencialAuth.setAutentificado(true);
            response.getWriter().println(ConstantsJakarta.CUENTA_ACTIVADA);
        } else {
            throw new WrongObjectException(ConstantsJakarta.LA_CREDENCIAL_QUE_SE_HA_TRATADO_DE_ACTIVAR_NO_EXISTE);
        }
    }




}
