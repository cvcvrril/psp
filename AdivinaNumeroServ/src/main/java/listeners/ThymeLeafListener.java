package listeners;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.annotation.WebListener;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.WebApplicationTemplateResolver;
import org.thymeleaf.web.IWebApplication;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.util.Set;

@WebListener
public class ThymeLeafListener {

    public static final String TEMPLATE_ENGINE_ATTR = "thymeleaf.TemplateEngineInstance";
    private ITemplateEngine templateEngine;
    private JakartaServletWebApplication application;

    public ThymeLeafListener(){}

    public void contextInitialized(ServletContextEvent sce){
        this.application = JakartaServletWebApplication.buildApplication(sce.getServletContext());
        this.templateEngine = templateEngine(this.application);

    }

    private ITemplateEngine templateEngine(IWebApplication application) {
        TemplateEngine templateEngine = new TemplateEngine();
        WebApplicationTemplateResolver templateResolver = templateResolver(application);
        templateEngine.setTemplateResolvers((Set<ITemplateResolver>) templateResolver);
        return templateEngine;
    }

    private WebApplicationTemplateResolver templateResolver(IWebApplication application) {
        WebApplicationTemplateResolver templateResolver = new WebApplicationTemplateResolver(application);
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setPrefix("/WEB-INF/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setCacheTTLMs(Long.valueOf(360000L));  //???????????????
        templateResolver.setCacheable(true);
        return templateResolver;
    }
}
