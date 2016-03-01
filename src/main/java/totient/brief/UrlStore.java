package totient.brief;

import redis.clients.jedis.Jedis;

public class UrlStore {

  final static UrlStore INSTANCE = new UrlStore();
  private final Jedis jedis;
  
  private UrlStore() {
    jedis = new Jedis("localhost", 6379);
    jedis.select(1);
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
    return jedis.keys("*").isEmpty();  
  }
  
}

