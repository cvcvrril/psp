package servelts;

import common.Constantes;
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

@WebServlet(name = Constantes.ADIVINAR_SERVLET, value = Constantes.ADIVINA_VALUE)
public class AdivinaServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException{
        TemplateEngine tEngine = (TemplateEngine) getServletContext().getAttribute(ThymeLeafListener.TEMPLATE_ENGINE_ATTR);
        IWebExchange webExchange = JakartaServletWebApplication.buildApplication(getServletContext()).buildExchange(req, res);
        WebContext context = new WebContext(webExchange);
        String numRandom = generarRandom(req);
        String numero = req.getParameter(Constantes.NUMERO);

        if (numero == null || numero.isEmpty()){
            context.setVariable(Constantes.MENSAJE, Constantes.NO_NUM);
            tEngine.process(Constantes.HOME, context, res.getWriter());
        } else {
            if (!comprobarRepetido(req, numero)){
                if (contador(req) < 10){
                    String resul = comprobarNumero(numero, numRandom, context, req);
                    tEngine.process(resul, context, res.getWriter());
                } else {
                    context.setVariable(Constantes.MENSAJE, Constantes.NO_INTENTOS);
                    tEngine.process(Constantes.LOSE, context, res.getWriter());
                }
            } else {
                int contador = 10 - (Integer) req.getSession().getAttribute(Constantes.CONTADOR);
                context.setVariable(Constantes.MENSAJE, Constantes.NUM_REPETIDO);
                context.setVariable(Constantes.INTENTOS, contador);
                tEngine.process(Constantes.REP, context, res.getWriter());
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
            context.setVariable(Constantes.NUMERO,numero);
            context.setVariable(Constantes.INTENTOS, contadorInt);
            return Constantes.GANA;
        } else {
            String pista = Integer.parseInt(numero) < Integer.parseInt(numRandom)? Constantes.NUMERO_ALTO : "El numero es mas bajo";
            context.setVariable(Constantes.MENSAJE, pista);
            context.setVariable(Constantes.INTENTOS, 10-contadorInt);
            return Constantes.FALLO;
        }
    }

    private boolean comprobarRepetido(HttpServletRequest req, String numero){
        List<String> list = new ArrayList<>();
        boolean rep;
        if (req.getSession().getAttribute(Constantes.PREV) == null){
            req.getSession().setAttribute(Constantes.PREV, list);
        } else {
            list = (List<String>) req.getSession().getAttribute(Constantes.PREV);
        }
        if (list.contains(numero)){
            rep = true;
        } else {
            rep = false;
            list.add(numero);
            req.getSession().setAttribute(Constantes.PREV, list);
        }
        return rep;
    }

    private String generarRandom(HttpServletRequest req){
        String numRandom;
        if (req.getSession().getAttribute(Constantes.INCOGNITA) == null){
            Random r = new Random();
            numRandom = String.valueOf(r.nextInt(11));
            req.getSession().setAttribute(Constantes.INCOGNITA, numRandom);
        } else {
            numRandom = (String) req.getSession().getAttribute(Constantes.INCOGNITA);
        }
        return numRandom;
    }

}
