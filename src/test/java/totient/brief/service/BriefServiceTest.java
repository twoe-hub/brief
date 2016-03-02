package totient.brief.service;

import org.junit.Test;
import static org.junit.Assert.*;
import static totient.brief.store.UrlStore.*;

public class BriefServiceTest {
  
  BriefService service = BriefService.INSTANCE;

  @Test
  public void testAbbreviate() {
    URL_STORE.flush();
    String url = "http://ymail.com";
    String abbr = service.abbreviate(url);
    String key = abbr.substring(abbr.lastIndexOf("/")+1, abbr.length());
    assertEquals(url, service.retrieve(key));
  }
}
