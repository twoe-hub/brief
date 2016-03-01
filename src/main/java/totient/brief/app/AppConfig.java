package totient.brief.app;

import totient.brief.controller.Abbreviatr;
import totient.brief.controller.Controller;
import totient.brief.controller.HeadController;
import totient.brief.controller.Retrievr;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

public class AppConfig {

  private final TemplateEngine templateEngine;
  private final Map<String, Controller> controllerMap;
  private final String default_key = ":default";
  private static AppConfig appConfig;

  public static AppConfig getInstance(ServletContext context) {
    if (appConfig == null) {
      appConfig = new AppConfig(context);
    }
    return appConfig;
  }

  private AppConfig(final ServletContext context) {

    ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(context);

    // This couple will convert, for example, "index" to "/WEB-INF/templates/index.html"
    templateResolver.setPrefix("/WEB-INF/templates/");
    templateResolver.setSuffix(".html");

    templateResolver.setCacheTTLMs(3600000L);
    templateResolver.setCacheable(true);

    this.templateEngine = new TemplateEngine();
    this.templateEngine.setTemplateResolver(templateResolver);

    this.controllerMap = new HashMap<>();
    this.controllerMap.put("/", new HeadController());
    this.controllerMap.put("/abbr", new Abbreviatr());
    this.controllerMap.put(default_key, new Retrievr());

  }

  public Controller resolveControllerForRequest(final HttpServletRequest request) {
    final String path = getRequestPath(request);
    
    Controller ctrlr = this.controllerMap.get(path);
    if (ctrlr != null)
      return ctrlr;
    
    String uri = request.getRequestURI();
    String key = uri.substring(uri.lastIndexOf("/")+1, uri.length());
    return key.startsWith("_") ? this.controllerMap.get(default_key) : null;
  }

  public TemplateEngine getTemplateEngine() {
    return this.templateEngine;
  }

  private static String getRequestPath(final HttpServletRequest request) {

    String requestURI = request.getRequestURI();
    final String contextPath = request.getContextPath();

    final int fragmentIndex = requestURI.indexOf(';');
    if (fragmentIndex != -1) {
      requestURI = requestURI.substring(0, fragmentIndex);
    }

    if (requestURI.startsWith(contextPath)) {
      return requestURI.substring(contextPath.length());
    }
    return requestURI;
  }
}
