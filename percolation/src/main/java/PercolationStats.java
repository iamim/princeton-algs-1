import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private final double mean;
    private final double stddev;
    private final double confidenceLo;
    private final double confidenceHi;

    // perform trials independent experiments on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0)
            throw new IllegalArgumentException("Both arguments must be non-negative");

        double[] vals = new double[trials];

        for (int i = 0; i < trials; i++) {
            Percolation perc = new Percolation(n);

            while (!perc.percolates()) {
                int row = StdRandom.uniform(n) + 1;
                int col = StdRandom.uniform(n) + 1;

                perc.open(row, col);
            }

            vals[i] = (double) perc.numberOfOpenSites() / (n * n);
        }

        mean = StdStats.mean(vals);
        stddev = StdStats.stddev(vals);
        confidenceLo = mean - 1.96 * stddev / Math.sqrt(vals.length);
        confidenceHi = mean + 1.96 * stddev / Math.sqrt(vals.length);
    }

    // sample mean of percolation threshold
    public double mean() {
        return mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return stddev;
    }

    // low  endpoint of 95% confidence interval
    public double confidenceLo() {
        return confidenceLo;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return confidenceHi;
    }

    // test client (described below)
    public static void main(String[] args) {
        int n;
        int trials;

        double mean;
        double stddev;
        double[] confInt = new double[2];

        try {
            n = Integer.parseInt(args[0]);
            trials = Integer.parseInt(args[1]);
        }
        catch (NumberFormatException wrongArgs) {
            StdOut.print("Something went wrong: " + wrongArgs.getMessage());
            return;
        }
        catch (ArrayIndexOutOfBoundsException numOfArgs) {
            StdOut.print("PercolationStats should be invoked with two integer arguments");
            return;
        }

        try {
            PercolationStats runner = new PercolationStats(n, trials);

            mean = runner.mean();
            stddev = runner.stddev();
            confInt[0] = runner.confidenceLo();
            confInt[1] = runner.confidenceHi();
        }
        catch (IllegalArgumentException simException) {
            StdOut.print("Something went wrong while running one of your simulations: "
                    + simException.getMessage());
            return;
        }

        StdOut.println("mean:                    " + mean);
        StdOut.println("stddev:                  " + stddev);
        StdOut.print("95% confidence interval: " + "[" + confInt[0] + ", " + confInt[1] + "]");
    }
}
