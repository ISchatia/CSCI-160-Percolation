/* *****************************************************************************
 *  Name:    Isaac Schatia
 *  NetID:   alovelace
 *  Precept: P00
 *
 *  Description:  Prints 'Hello, World' to the terminal window.
 *                By tradition, this is everyone's first program.
 *                Prof. Brian Kernighan initiated this tradition in 1974.
 *
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;

public class PercolationStats {

    private double[] fractionArray;
    private int trialsvalue;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("n <= 0 and/or trials <= 0");
        }

        fractionArray = new double[trials];
        trialsvalue = trials;

        for (int i = 0; i < trials; i++) {
            Percolation test = new Percolation(n);
            while (!test.percolates()) {
                int row = StdRandom.uniform(n);
                int col = StdRandom.uniform(n);
                test.open(row, col);
            }
            int openSites = test.numberOfOpenSites();
            double fractionOfOpenSites = (double) openSites / (n * n);
            fractionArray[i] = fractionOfOpenSites;
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(fractionArray);

        // double sum = 0;
        // for (int i = 0; i < fractionArray.length; i++) {
        //     sum += fractionArray[i];
        // }
        // return sum / trialsvalue;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(fractionArray);

        // double topSum = 0;
        // double mean = this.mean();
        // for (int i = 0; i < fractionArray.length; i++) {
        //     double inParentheses = fractionArray[i] - mean;
        //     topSum += Math.pow(inParentheses, 2);
        // }
        // double notRooted = topSum / (trialsvalue - 1);
        // return Math.sqrt(notRooted);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        double rightValue = (1.96 * this.stddev()) / Math.sqrt(trialsvalue);
        return this.mean() - rightValue;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        double rightValue = (1.96 * this.stddev()) / Math.sqrt(trialsvalue);
        return this.mean() + rightValue;
    }

    // test client (see below)
    public static void main(String[] args) {
        Stopwatch stopwatch = new Stopwatch();

        PercolationStats test = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        StdOut.println("mean()           = " + test.mean());
        StdOut.println("stddev()         = " + test.stddev());
        StdOut.println("confidenceLow()  = " + test.confidenceLow());
        StdOut.println("confidenceHigh() = " + test.confidenceHigh());

        double time = stopwatch.elapsedTime();
        StdOut.println("elapsed time     = " + time);
    }

}
