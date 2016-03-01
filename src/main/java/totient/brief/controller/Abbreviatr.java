package totient.brief.controller;

import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.thymeleaf.TemplateEngine;
import totient.brief.service.BriefService;

public class Abbreviatr implements Controller{

  @Override
  public void process(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext, TemplateEngine templateEngine) throws Exception {
    PrintWriter writer = response.getWriter();
    String url = request.getParameter("user_url");
    BriefService service = BriefService.INSTANCE;
    writer.write(service.abbreviate(url));
  }
  
}
