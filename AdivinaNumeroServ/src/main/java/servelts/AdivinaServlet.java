package servelts;

import Common.Constantes;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import listeners.ThymeLeafListener;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@WebServlet(name = "AdivinarServlet", value = "/adivina")
public class AdivinaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException{
        TemplateEngine tEngine = (TemplateEngine) getServletContext().getAttribute(ThymeLeafListener.TEMPLATE_ENGINE_ATTR);
        IWebExchange webExchange = JakartaServletWebApplication.buildApplication(getServletContext()).buildExchange(req, res);
        WebContext context = new WebContext(webExchange);
        String numRandom = generarRandom(req);
        String numero = req.getParameter("numero");

        if (numero == null || numero.isEmpty()){
            context.setVariable("mensaje", "Mete un numero, no seas cabezon plis");
            tEngine.process("home", context, res.getWriter());
        } else {
            if (!comprobarRepetido(req, numero)){
                if (contador(req) < 10){
                    String resul = comprobarNumero(numero, numRandom, context, req);
                    tEngine.process(resul, context, res.getWriter());
                } else {
                    context.setVariable("mensaje", "Perdiste, has agotado tus intentos");
                    tEngine.process("lose", context, res.getWriter());
                }
            } else {
                int contador = 10 - (Integer) req.getSession().getAttribute("contador");
                context.setVariable("mensaje", "Venga maquina, has repetido numero");
                context.setVariable("intentos", contador);
                tEngine.process("rep", context, res.getWriter());
            }
        }
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

    private String comprobarNumero(String numero, String numRandom, WebContext context, HttpServletRequest req) {
        int contadorInt = (Integer) req.getSession().getAttribute(Constantes.CONTADOR_KEY);
        if (numero.equals(numRandom)){
            context.setVariable("numero",numero);
            context.setVariable("intentos", contadorInt);
            return "gana";
        } else {
            String pista = Integer.parseInt(numero) < Integer.parseInt(numRandom)? "El numero es mas alto": "El numero es mas bajo";
            context.setVariable("mensaje", pista);
            context.setVariable("intentos", 10-contadorInt);
            return "fallo";
        }
    }

    private boolean comprobarRepetido(HttpServletRequest req, String numero){
        List<String> list = new ArrayList<>();
        boolean rep;
        if (req.getSession().getAttribute("prev") == null){
            req.getSession().setAttribute("prev", list);
        } else {
            list = (List<String>) req.getSession().getAttribute("prev");
        }
        if (list.contains(numero)){
            rep = true;
        } else {
            rep = false;
            list.add(numero);
            req.getSession().setAttribute("prev", list);
        }
        return rep;
    }

    private String generarRandom(HttpServletRequest req){
        String numRandom;
        if (req.getSession().getAttribute("incognita") == null){
            Random r = new Random();
            numRandom = String.valueOf(r.nextInt(11));
            req.getSession().setAttribute("incognita", numRandom);
        } else {
            numRandom = (String) req.getSession().getAttribute("incognita");
        }
        return numRandom;
    }

}
