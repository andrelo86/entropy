import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.File;
import java.util.List;

public class EntropyTest {

  @Test(testName = "verifyForRandomBytesByDefault")
  @Parameters({"randomFileBytesPath"})
  public void verifyForRandomBytesByDefault(String randomFileBytesPath) {
    File file = new File(randomFileBytesPath);
    byte[] fileContent = new byte[(int) file.length()];
    EntropyFileCalculator entropyFileCalculator = new EntropyFileCalculator(randomFileBytesPath);
    entropyFileCalculator.calculate();
    List<Double> results = entropyFileCalculator.getEntropyResults();
    Assert.assertFalse(results.isEmpty(), "Entropy is not being calculated");
    Assert.assertEquals(results.size(), fileContent.length/1024, "Block of bytes are not correctly separated");
  }

  @Test(testName = "verifyForEmptyFile")
  @Parameters({"emptyFilePath"})
  public void verifyForEmptyFile(String emptyFilePath) {
    EntropyFileCalculator entropyFileCalculator = new EntropyFileCalculator(emptyFilePath);
    entropyFileCalculator.calculate();
    List<Double> results = entropyFileCalculator.getEntropyResults();
    Assert.assertTrue(results.isEmpty(), "Entropy results should be empty due to the bytes restriction violation");
  }

  @Test(testName = "verifyForEqualBytes")
  @Parameters({"equalCharsFilePath"})
  public void verifyForEqualBytes(String equalCharsFilePath) {
    EntropyFileCalculator entropyFileCalculator = new EntropyFileCalculator(equalCharsFilePath);
    entropyFileCalculator.calculate();
    List<Double> results = entropyFileCalculator.getEntropyResults();
    for (Double d: results) {
      Assert.assertEquals(-0.0, d, "Entropy should be zero since all character are same");
    }
  }

}
