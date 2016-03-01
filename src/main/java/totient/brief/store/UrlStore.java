package totient.brief.store;

import redis.clients.jedis.Jedis;
import static totient.brief.util.Config.CONF;

public class UrlStore {

  public final static UrlStore INSTANCE = new UrlStore();
  
  private final Jedis jedis;
    
  private UrlStore() {
    jedis = new Jedis(CONF.getServer(), 
            CONF.getPort());
    jedis.select(CONF.getDB());
  }
    
  public void set(String key, String url) {
    jedis.set(key, url);
  }
  
  public String get(String key) {
    return jedis.get(key);
  }
  
  public void flush() {
    jedis.flushDB();
  }
  
  boolean isEmpty() {
    return jedis.keys("t*").isEmpty();  
  }
  
}

