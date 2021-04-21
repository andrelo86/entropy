/**
 * Java main class used as runner.
 *
 */
public class Entropy {

    public static void main(String[] args) {
        try {
            EntropyFileCalculator entropyFileCalculator;
            if (args.length == 1) {
                String filePath = args[0];
                entropyFileCalculator = new EntropyFileCalculator(filePath);
            } else {
                int byteSegment = Integer.parseInt(args[0]);
                String filePath = args[1];
                entropyFileCalculator = new EntropyFileCalculator(byteSegment, filePath);
            }
            entropyFileCalculator.calculate();
        } catch (Exception e) {
            System.out.println("Invalid program inputs. " + "Exception" + e);
        }
    }

}

