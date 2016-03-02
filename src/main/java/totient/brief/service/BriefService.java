package totient.brief.service;

import static totient.brief.store.UrlStore.*;
import static totient.brief.util.Config.CONF;
import static totient.brief.util.KeyGeneratr.KEY_GEN;

public class BriefService {

  public final static BriefService INSTANCE = new BriefService();  
 
  private BriefService() {
  }
  
  public String abbreviate(final String url) {
    boolean exists;
    String key;

    do {
      key = KEY_GEN.generateKey();
      String value = URL_STORE.get(key);
      exists = url.equals(value);
    }while(exists);
    
    URL_STORE.set(key, url);
    return CONF.getHttpAddr()+key;
  }
  
  public String retrieve(String key) {
    return URL_STORE.get(key);
  }
}
