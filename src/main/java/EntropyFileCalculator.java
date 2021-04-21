import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Hello world!
 *
 */
public class EntropyFileCalculator {

    private static final int DEFAULT_SEGMENT = 1024;
    private static final int LOW_ENTROPY = 2;
    private static final int HIGH_ENTROPY = 7;

    private int byteSegment;
    private String filePath;
    private List<Double> entropyResults = new ArrayList<>();

    /**
     * Constructor for default 1024 block
     *
     * @param filePath
     */
    public EntropyFileCalculator(String filePath) {
        this.filePath = filePath;
        byteSegment = DEFAULT_SEGMENT;
    }

    /**
     * Constructor for custom blocking size
     *
     * @param byteSegment
     * @param filePath
     */
    public EntropyFileCalculator(int byteSegment, String filePath) {
        this.filePath = filePath;
        this.byteSegment = byteSegment;
    }

    /**
     * Main method to return the file entropy.
     */
    public void calculate() {
        File file = new File(filePath);
        try (FileInputStream fin = new FileInputStream(file)) {
            byte[] fileContent = new byte[(int) file.length()];
            int from = 0;
            int to = fileContent.length;
            if (fileContent.length >= byteSegment) {
                while (existSubset(fileContent, from, to)) {

                    byte[] subset = Arrays.copyOfRange(fileContent, from, from + byteSegment);

                    // Read data into the byte array
                    fin.read(subset);

                    // create array to keep track of frequency of bytes
                    int[] frequencyArray = new int[byteSegment];
                    int fileContentLength = subset.length - 1;
                    countFrequencyOfByteOccurence(subset, frequencyArray, fileContentLength);

                    // calculate entropy
                    double entropy = getEntropy(frequencyArray, fileContentLength);

                    entropyResults.add(entropy);

                    from += byteSegment;
                }
                printReport(entropyResults);
            } else {
                System.out.println("Cant get sub-sequence due to file size is < sub-sequent size requested");
            }
        } catch (
                FileNotFoundException e) {
            System.out.println("File not found " + "Exception: " + e);
        } catch (IOException ioe) {
            System.out.println("Exception while reading file " + "Exception: " + ioe);
        }
    }

    private void printReport(List<Double> entropyResults) {
        int segment = 0;
        int low = 0;
        int high = 0;
        System.out.println("block#              " + "entropy");
        for (Double d : entropyResults) {
            if (d < LOW_ENTROPY) {
                low++;
            } else if (d > HIGH_ENTROPY) {
                high++;
            }
            DecimalFormat df = new DecimalFormat("#.##");
            System.out.println(segment + "                    " + df.format(d));
            segment++;
        }
        System.out.println("Low entropy blocks: " + low);
        System.out.println("High entropy blocks: " + high);
    }

    private boolean existSubset(byte[] fileContent, int from, int to) {
        return (from < to) && (fileContent.length - from >= byteSegment);
    }

    private void countFrequencyOfByteOccurence(byte[] subset, int[] frequencyArray, int fileContentLength) {
        // count frequency of occuring bytes
        for (int i = 0; i < fileContentLength; i++) {
            byte byteValue = subset[i];
            frequencyArray[Byte.toUnsignedInt(byteValue)]++;
        }
    }

    private double getEntropy(int[] frequencyArray, double fileContentLength) {
        double entropy = 0;
        for (int i = 0; i < frequencyArray.length; i++) {
            if (frequencyArray[i] != 0) {
                // calculate the probability of a particular byte occurrence
                double probabilityOfByte = (double) frequencyArray[i] / fileContentLength;

                // calculate the next value to sum to previous entropy calculation
                double value = probabilityOfByte * (Math.log(probabilityOfByte) / Math.log(2));
                entropy = entropy + value;
            }
        }
        entropy *= -1;
        return entropy;
    }

    public List<Double> getEntropyResults() {
        return entropyResults;
    }
}
