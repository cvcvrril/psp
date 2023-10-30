package servelts;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "prueba", value = "prueba")
public class PruebaServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        int cuntador;
        if (req.getSession().getAttribute("cuntador") == null){
            req.getSession().setAttribute("cuntador", 0);
        }

        cuntador = (int) req.getSession().getAttribute("cuntador");
        res.getWriter().println(cuntador);
        cuntador++;
        req.getSession().setAttribute("cuntador", cuntador);
    }


}
