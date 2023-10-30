package servelts;

import Common.Constantes;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import listeners.ThymeLeafListener;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import java.io.IOException;

public class AdivinaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException{
        TemplateEngine tEngine = (TemplateEngine) getServletContext().getAttribute(ThymeLeafListener.TEMPLATE_ENGINE_ATTR);

        String numRandom;
        String numUser;


    }

    private int contador(HttpServletRequest req){
        Integer contador;
        if (req.getSession().getAttribute(Constantes.CONTADOR_KEY) == null){
            req.getSession().setAttribute(Constantes.CONTADOR_KEY, 0);
        }
        contador = (Integer) req.getSession().getAttribute(Constantes.CONTADOR_KEY);
        contador++;
        req.getSession().setAttribute(Constantes.CONTADOR_KEY, contador);
        return contador;
    }

    private boolean comprobarNumbero(String numUser, String numRandom, WebContext context, HttpServletRequest req) {
        int contadorInt = (Integer) req.getSession().getAttribute(Constantes.CONTADOR_KEY);
        if (numUser.equals(numRandom)){

        } else {
            
        }
        return false;
    }

}
