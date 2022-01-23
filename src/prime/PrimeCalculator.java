package prime;

// Calculates the first n primes, displaying them as they are found.
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingWorker;
import java.util.Random;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;

public class PrimeCalculator extends SwingWorker< Integer, Integer> {

    private final Random generator = new Random();
    private final JTextArea intermediateJTextArea; // displays found primes
    private final JButton getPrimesJButton;
    private final JButton cancelJButton;
    private final JLabel statusJLabel; // displays status of calculation
    private final boolean[] primes; // boolean array for finding primes

    // constructor
    public PrimeCalculator(int max, JTextArea intermediate, JLabel status,
            JButton getPrimes, JButton cancel) {
        intermediateJTextArea = intermediate;
        statusJLabel = status;
        getPrimesJButton = getPrimes;
        cancelJButton = cancel;
        primes = new boolean[max];

        // initialize all prime array values to true
        for (int i = 0; i < max; i++) {
            primes[i] = true;
        }
    } // end constructor

    // finds all primes up to max using the Sieve of Eratosthenes

    /**
     *
     * @return
     */
        @Override
    public Integer doInBackground() {
        int count = 0; // the number of primes found

      // starting at the third value, cycle through the array and put
        // false as the value of any greater number that is a multiple
        for (int i = 2; i < primes.length; i++) {
            if (isCancelled()) // if calculation has been canceled
            {
                return count;
            } else {
                setProgress(100 * (i + 1) / primes.length);

                try {
                    Thread.sleep(generator.nextInt(100));
                } // end try
                catch (InterruptedException ex) {
                    statusJLabel.setText("Worker thread interrupted");
                    return count;
                } // end catch

                if (primes[i]) // i is prime
                {
                    publish(i); // make i available for display in prime list
                    ++count;

                    for (int j = i + i; j < primes.length; j += i) {
                        primes[j] = false; // i is not prime
                    }
                } // end if
            } // end else 
        } // end for

        return count;
    } // end method doInBackground

    // displays published values in primes list
    @Override
    protected void process(List< Integer> publishedVals) {
        for (int i = 0; i < publishedVals.size(); i++) {
            intermediateJTextArea.append(publishedVals.get(i) + "\n");
        }
    } // end method process

    // code to execute when doInBackground completes
    @Override
    protected void done() {
        getPrimesJButton.setEnabled(true); // enable Get Primes button
        cancelJButton.setEnabled(false); // disable Cancel button

        int numPrimes;

        try {
            numPrimes = get(); // retrieve doInBackground return value
        } // end try
        catch (InterruptedException ex) {
            statusJLabel.setText("Interrupted while waiting for results.");
            return;
        } // end catch
        catch (ExecutionException ex) {
            statusJLabel.setText("Error performing computation.");
            return;
        } // end catch
        catch (CancellationException ex) {
            statusJLabel.setText("Cancelled.");
            return;
        } // end catch

        statusJLabel.setText("Found " + numPrimes + " primes.");
    } // end method done  
} // end class PrimeCalculator
