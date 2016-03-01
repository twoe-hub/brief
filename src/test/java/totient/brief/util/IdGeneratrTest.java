package totient.brief.util;

import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import static totient.brief.util.IdGeneratr.ID_GEN;

public class IdGeneratrTest {

  @Ignore // Takes a lot of memory and time
  @Test
  public void testGenerateId() throws NoSuchAlgorithmException {
    Instant start = Instant.now();
    List<String> ids = new ArrayList<>();
    for (int i = 0; i < 100000; i++) {
      String s = ID_GEN.generateId();
      if (ids.contains(s)) {
        Assert.fail("Duplicate generated");
      } else if(s.length() > 8) {
        Assert.fail("Too large");
      } else {
        ids.add(s);
      }
    }
    
    Instant end = Instant.now();
    System.out.println(Duration.between(start, end));

  }
}
