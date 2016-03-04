package totient.brief.util;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public enum KeyGeneratr {
  KEY_GEN;
  
  public final char[] DIC_62 = new char[]{'0', '1', '2', '3', '4', 
    '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 
    'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 
    'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 
    'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
  
  private final long BASE = DIC_62.length;
  private final SecureRandom SEC_RAND = new SecureRandom();

  public String generateKey() {

    long value = SEC_RAND.nextLong();
    value *= (value < 0) ? -1 : 1;
    List<Character> seq = new ArrayList<>();
    int exponent = 1;
    
    long remaining = value;
    while (remaining != 0L) {
      long a = (long) Math.pow(BASE, exponent);
      long b = remaining % a;
      long c = (long) Math.pow(BASE, exponent - 1);
      int d = (int) (b/c);

      seq.add(DIC_62[d]);
      remaining = remaining - b;

      exponent++;
    }

    return "_"+reverse(format(seq));
  }

  private List<Character> format(List<Character> seq) {
    Random rand = ThreadLocalRandom.current();
    int n;
    int[] inds = new int[3];
    n = rand.nextInt((999 - 102) + 1) + 102;
    
    inds[0] = n / 100;
    int temp = n % 100;
    inds[1] = temp / 10;
    inds[2] = temp % 10;
    Arrays.sort(inds);
    
    char c = (seq.size()> inds[2]) ? seq.remove(inds[2]) : seq.remove(seq.size()-1);
    c = (seq.size()> inds[1]) ? seq.remove(inds[1]) : seq.remove(seq.size()-1);
    c = (seq.size()> inds[0]) ? seq.remove(inds[0]) : seq.remove(seq.size()-1);
    
    return seq;
  }

  private String reverse(List<Character> seq) {
    StringBuilder sb = new StringBuilder();
    for (int i = seq.size() - 1; i >= 0; i--) {
      sb.append(seq.get(i));
    }
    
    return sb.deleteCharAt(0).toString();
  }
}
