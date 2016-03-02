package totient.brief.store;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import static totient.brief.util.Config.CONF;

enum JedisFactory {
  JEDIS_FACTORY;

  private JedisPool jedisPool = null;
  
  private JedisFactory() {
    try {
      jedisPool = new JedisPool(new URI(CONF.getRedisUri()), 60);
    } catch (URISyntaxException ex) {
      Logger.getLogger(JedisFactory.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public Jedis getJedis() {
    return jedisPool.getResource();
  }

}
