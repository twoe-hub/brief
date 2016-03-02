package totient.brief.store;

import redis.clients.jedis.Jedis;
import static totient.brief.store.JedisFactory.*;

public enum UrlStore {
  URL_STORE;
 

  private UrlStore() {
  }
    
  public void set(String key, String url) {
    Jedis jedis = JEDIS_FACTORY.getJedis();
    jedis.set(key, url);
  }
  
  public String get(String key) {
    Jedis jedis = JEDIS_FACTORY.getJedis();
    return jedis.get(key);
  }
  
  public void flush() {
    Jedis jedis = JEDIS_FACTORY.getJedis();
    jedis.flushDB();
  }
  
  boolean isEmpty() {
    Jedis jedis = JEDIS_FACTORY.getJedis();
    return jedis.keys("t*").isEmpty();  
  }
  
}
