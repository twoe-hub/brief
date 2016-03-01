package totient.brief;

import static totient.brief.IdGeneratr.ID_GEN;
import static totient.brief.Config.CONF;

public class BriefService {

  final static BriefService INSTANCE = new BriefService();  
  private final UrlStore store = UrlStore.INSTANCE;
  
  private BriefService() {
  }
  
  String abbreviate(final String url) {
    boolean exists;
    String id;

    do {
      id = ID_GEN.generateId();
      String value = store.get(id);
      exists = url.equals(value);
    }while(exists);
    
    store.set(id, url);
    return CONF.getBriefDomain()+id;
  }
  
  String retrieve(String key) {
    return store.get(key);
  }
}
