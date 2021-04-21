import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class EntropyTest {

  @Test
  public void verifyForRandomBytes() {
    EntropyFileCalculator entropyFileCalculator = new EntropyFileCalculator("src/test/resources/random.txt");
    entropyFileCalculator.calculate();
    List<Double> results = entropyFileCalculator.getEntropyResults();
  }

  @Test
  public void verifyForEqualBytes() {
    EntropyFileCalculator entropyFileCalculator = new EntropyFileCalculator("src/test/resources/equalChars.txt");
    entropyFileCalculator.calculate();
    List<Double> results = entropyFileCalculator.getEntropyResults();
    for (Double d: results) {
      Assert.assertEquals(-0.0, d, "Entropy should be zero since all character are same");
    }
  }

  @Test
  public void verifyForEmptyFile() {
    EntropyFileCalculator entropyFileCalculator = new EntropyFileCalculator("src/test/resources/empty.txt");
    entropyFileCalculator.calculate();
    List<Double> results = entropyFileCalculator.getEntropyResults();
  }

}
