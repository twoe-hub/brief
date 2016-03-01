package totient.brief.controller;


import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

public class HeadController implements Controller {

  @Override
  public void process(final HttpServletRequest request, final HttpServletResponse response,
          final ServletContext servletContext, final TemplateEngine templateEngine)
          throws Exception {

    WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());
    templateEngine.process("index", ctx, response.getWriter());
  }
}
