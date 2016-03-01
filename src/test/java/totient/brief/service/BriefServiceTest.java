package totient.brief.service;

import totient.brief.store.UrlStore;
import org.junit.Test;
import static org.junit.Assert.*;

public class BriefServiceTest {
  
  UrlStore store = UrlStore.INSTANCE;
  BriefService service = BriefService.INSTANCE;

  @Test
  public void testAbbreviate() {
    store.flush();
    String url = "http://ymail.com";
    String abbr = service.abbreviate(url);
    String key = abbr.substring(abbr.lastIndexOf("/")+1, abbr.length());
    assertEquals(url, service.retrieve(key));
  }
}
