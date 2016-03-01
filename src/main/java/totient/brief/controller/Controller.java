package totient.brief.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.thymeleaf.TemplateEngine;

public interface Controller {

  public void process(HttpServletRequest request, HttpServletResponse response,
          ServletContext servletContext, TemplateEngine templateEngine) throws Exception;

}
