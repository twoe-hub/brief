package totient.brief.service;

import totient.brief.store.UrlStore;
import static totient.brief.util.Config.CONF;
import static totient.brief.util.KeyGeneratr.KEY_GEN;

public class BriefService {

  public final static BriefService INSTANCE = new BriefService();  
  private final UrlStore store = UrlStore.INSTANCE;
  
  private BriefService() {
  }
  
  public String abbreviate(final String url) {
    boolean exists;
    String key;

    do {
      key = KEY_GEN.generateKey();
      String value = store.get(key);
      exists = url.equals(value);
    }while(exists);
    
    store.set(key, url);
    return CONF.getHttpAddr()+key;
  }
  
  public String retrieve(String key) {
    return store.get(key);
  }
}
