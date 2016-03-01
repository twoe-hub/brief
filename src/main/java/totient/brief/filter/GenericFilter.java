package totient.brief.filter;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thymeleaf.TemplateEngine;
import totient.brief.app.AppConfig;
import totient.brief.controller.Controller;

@WebFilter(filterName = "GenericFilter", urlPatterns = "/*")
public class GenericFilter implements Filter {

  private ServletContext servletContext;
  private AppConfig appConfig;

  @Override
  public void init(final FilterConfig filterConfig) throws ServletException {
    servletContext = filterConfig.getServletContext();
    appConfig = AppConfig.getInstance(servletContext);
  }

  @Override
  public void destroy() {
  }

  @Override
  public void doFilter(final ServletRequest request, final ServletResponse response,
          final FilterChain chain) throws IOException, ServletException {

    if (!process((HttpServletRequest) request, (HttpServletResponse) response)) {
      chain.doFilter(request, response);
    }
  }

  private boolean process(HttpServletRequest request, HttpServletResponse response)
          throws ServletException {

    // This prevents triggering engine executions for resource URLs
    if (isStaticResource(request.getRequestURI())) {
      return false;
    }

    Controller controller = appConfig.resolveControllerForRequest(request);
    TemplateEngine templateEngine = appConfig.getTemplateEngine();
    setResponseDefaults(response);

    try {
      controller.process(request, response, this.servletContext, templateEngine);
    } catch (Exception ex) {
      Logger.getLogger(GenericFilter.class.getName()).log(Level.SEVERE, null, ex);
      sendError(response, ex);
    }

    return true;
  }

  private void setResponseDefaults(HttpServletResponse response) {
    response.setContentType("text/html;charset=UTF-8");
    response.setHeader("Pragma", "no-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0);
  }

  private boolean isStaticResource(String resourceURI) {
    return resourceURI.startsWith("/images");
  }

  private void sendError(HttpServletResponse response, Exception e) throws ServletException {
    try {
      response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    } catch (IOException ioe) {
      throw new ServletException(e);
    }
  }
}
