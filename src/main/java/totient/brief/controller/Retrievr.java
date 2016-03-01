package totient.brief.controller;

import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.thymeleaf.TemplateEngine;
import totient.brief.service.BriefService;

public class Retrievr implements Controller {

  @Override
  public void process(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext, TemplateEngine templateEngine) throws Exception {
    BriefService service = BriefService.INSTANCE;
    
    String uri = request.getRequestURI();
    String key = uri.substring(uri.lastIndexOf("/")+1, uri.length());
    String url = service.retrieve(key);
    
    response.sendRedirect(url);
  }

}
