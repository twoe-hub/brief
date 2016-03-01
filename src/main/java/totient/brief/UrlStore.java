package totient.brief;

import redis.clients.jedis.Jedis;
import static totient.brief.Config.CONF;

public class UrlStore {

  final static UrlStore INSTANCE = new UrlStore();
  
  private final Jedis jedis;
    
  private UrlStore() {
    jedis = new Jedis(CONF.getServer(), 
            CONF.getPort());
    jedis.select(CONF.getDB());
  }
    
  void set(String key, String url) {
    jedis.set(key, url);
  }
  
  String get(String key) {
    return jedis.get(key);
  }
  
  void flush() {
    jedis.flushDB();
  }
  
  boolean isEmpty() {
    return jedis.keys("t*").isEmpty();  
  }
  
}

