import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private final double mean, stddev, confidenceLo, confidenceHi;

    // perform trials independent experiments on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new java.lang.IllegalArgumentException();
        }

        double[] list = new double[trials];
        double total = 0;
        for (int i = 0; i < trials; i++) {
            double x = trial(n);
            list[i] = x / (n * n);
            total += list[i];
        }
        this.mean = total / trials;
        this.stddev = StdStats.stddev(list);
        this.confidenceLo = mean - ((1.96 * stddev) / Math.sqrt(trials));
        this.confidenceHi = mean + ((1.96 * stddev) / Math.sqrt(trials));
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

    private int trial(int n) {
        Percolation perc = new Percolation(n);

        while (!perc.percolates()) {
            perc.open(StdRandom.uniform(n) + 1, StdRandom.uniform(n) + 1);
        }
        return perc.numberOfOpenSites();
    }

    // test client (described below)
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats stats = new PercolationStats(n, trials);
        StdOut.println("mean                    = " + stats.mean());
        StdOut.println("stddev                  = " + stats.stddev());
        StdOut.println(
                "95% confidence interval = ["
                        + stats.confidenceLo()
                        + ", "
                        + stats.confidenceHi()
                        + "]");
    }
}
