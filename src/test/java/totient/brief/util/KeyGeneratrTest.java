package totient.brief.util;

import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import static totient.brief.util.KeyGeneratr.KEY_GEN;

public class KeyGeneratrTest {

  @Ignore // Takes a lot of memory and time
  @Test
  public void testGenerateKey() throws NoSuchAlgorithmException {
    Instant start = Instant.now();
    List<String> keys = new ArrayList<>();
    for (int i = 0; i < 100000; i++) {
      String s = KEY_GEN.generateKey();
      if (keys.contains(s)) {
        Assert.fail("Duplicate generated");
      } else if(s.length() > 8) {
        Assert.fail("Too large");
      } else {
        keys.add(s);
      }
    }
    
    Instant end = Instant.now();
    System.out.println(Duration.between(start, end));

  }
}
