package jakarta.servlet;


import jakarta.servlet.annotation.HttpConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

@WebServlet(name = "ServletActivation", value = "/Activation")
@ServletSecurity(
        @HttpConstraint(transportGuarantee = ServletSecurity.TransportGuarantee.CONFIDENTIAL)
)
public class ServletRefreshToken extends HttpServlet {



}
