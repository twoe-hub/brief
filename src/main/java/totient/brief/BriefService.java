package totient.brief;

import static totient.brief.IdGeneratr.ID_GEN;
        
public class BriefService {

  final static BriefService INSTANCE = new BriefService();
  final UrlStore store = UrlStore.INSTANCE;

  private BriefService() {
  }
  
  String abbreviate(final String url) {
    boolean exists;
    String id;

    do {
      id = ID_GEN.generateId();
      String value = store.get(id);
      exists = value.equals(url);
    }while(exists);
    
    store.set(id, url);
    return "http://bri.ef/"+id;
  }
  
  String retrieve(String key) {
    return store.get(key);
  }
}
