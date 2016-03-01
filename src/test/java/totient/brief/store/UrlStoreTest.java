package totient.brief.store;

import totient.brief.store.UrlStore;
import org.junit.Test;
import static org.junit.Assert.*;

public class UrlStoreTest {
 
  private final UrlStore store = UrlStore.INSTANCE;
  
  @Test
  public void testSetGet() {
    String key = "test:key";
    String value = "http://test.value";
    store.set(key, value);
    assertEquals(value, store.get(key));
  }
  
  @Test
  public void testIsEmpty() {
    store.flush();
    assertTrue("It's not empty; expected empty" , store.isEmpty());
  }
}
