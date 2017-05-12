import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
  private final double mean, stddev, confidenceLo, confidenceHi;

  private int trial(int n){
    Percolation perc = new Percolation(n);

    while (!perc.percolates()){
      perc.open(StdRandom.uniform(n)+1, StdRandom.uniform(n)+1);
    }
    return perc.numberOfOpenSites();
  }

  public PercolationStats(int n, int trials)    // perform trials independent experiments on an n-by-n grid
  {
    if (n <= 0 || trials <= 0){
      throw new java.lang.IllegalArgumentException();
    }

    double[] list = new double[trials];
    double total = 0;
    for (int i = 0; i < trials; i++){
      double x = trial(n);
      list[i] = x / (n * n);
      total += list[i];
    }
    this.mean = total / trials;
    this.stddev = StdStats.stddev(list);
    this.confidenceLo = mean - ((1.96*stddev)/Math.sqrt(trials));
    this.confidenceHi = mean + ((1.96*stddev)/Math.sqrt(trials));
  }

  public double mean()                          // sample mean of percolation threshold
  {
    return mean;
  }

  public double stddev()                        // sample standard deviation of percolation threshold
  {
    return stddev;
  }

  public double confidenceLo()                  // low  endpoint of 95% confidence interval
  {
    return confidenceLo;
  }

  public double confidenceHi()                  // high endpoint of 95% confidence interval
  {
    return confidenceHi;
  }

  public static void main(String[] args)        // test client (described below)
  {
    int n = Integer.parseInt(args[0]);
    int trials = Integer.parseInt(args[1]);
    PercolationStats stats = new PercolationStats(n, trials);
    StdOut.println("mean                    = " + stats.mean());
    StdOut.println("stddev                  = " + stats.stddev());
    StdOut.println("95% confidence interval = [" + stats.confidenceLo() + ", " + stats.confidenceHi() + "]");
  }
}
