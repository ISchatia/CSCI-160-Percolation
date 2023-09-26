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
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private boolean[][] n2_grid;
    private int virtual_top = 0;
    private int virtual_bottom;
    private int n_value;
    private WeightedQuickUnionUF uf;
    private int open_sites;

    // gets the index using row and col
    private int getIndex(int row, int col) {
        return (n_value * (row) + col + 1);
    }

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Value of n <= 0");
        }

        n2_grid = new boolean[n][n];
        virtual_bottom = n * n + 1;
        uf = new WeightedQuickUnionUF(n * n + 2);
        n_value = n;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row < 0 || col < 0 || row >= n_value || col >= n_value) {
            throw new IllegalArgumentException("Row and/or Column outside of its prescribed range.");
        }

        // used for indexing in the array, so (1,1) becomes (0,0). isOpen takes 'ROW/COL', getIndex takes 'row/col'.
        // int ROW = row - 1;
        // int COL = col - 1;
        if (n2_grid[row][col]) {
            return;
        }
        n2_grid[row][col] = true;
        open_sites++;

        // if in top row, union to virtual top
        if (row == 0) {
            uf.union(virtual_top, getIndex(row, col));
        }

        // if in bottom row, union to virtual bottom
        if (row == n_value-1) {
            uf.union(virtual_bottom, getIndex(row, col));
        }

        // union to possible neighbors
        if (row > 0 && isOpen(row - 1, col)) {
            uf.union(getIndex(row, col), getIndex(row - 1, col));
        }
        if (row < n_value-1 && isOpen(row + 1, col)) {
            uf.union(getIndex(row, col), getIndex(row + 1, col));
        }
        if (col > 0 && isOpen(row, col - 1)) {
            uf.union(getIndex(row, col), getIndex(row, col - 1));
        }
        if (col < n_value-1 && isOpen(row, col + 1)) {
            uf.union(getIndex(row, col), getIndex(row, col + 1));
        }

    }

    // is this site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row < 0 || col < 0 || row >= n_value || col >= n_value) {
            throw new IllegalArgumentException("Row and/or Column outside of its prescribed range.");
        }
        return n2_grid[row][col];
    }

    // is this site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row < 0 || col < 0 || row >= n_value || col >= n_value) {
            throw new IllegalArgumentException("Row and/or Column outside of its prescribed range.");
        }
        return uf.connected(getIndex(row, col), virtual_top);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return open_sites;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.connected(virtual_top, virtual_bottom);
    }

    // unit testing (required)
    public static void main(String[] args) {
        Percolation test = new Percolation(5);

        test.open(0, 0);
        test.open(1, 0);
        test.open(2, 0);
        test.open(3, 0);
        test.open(4, 0);

        StdOut.println(test.isFull(1, 0)); // true
        StdOut.println(test.isFull(1, 1)); // false
        StdOut.println(test.numberOfOpenSites());
        StdOut.println(test.percolates());
    }

}
