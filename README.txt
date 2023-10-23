# CSCI-160-Percolation

/* *****************************************************************************
 *  Name: Isaac Schatia
 *  NetID:
 *  Precept:
 *
 *  Partner Name:    N/A
 *  Partner NetID:   N/A
 *  Partner Precept: N/A
 *
 *  Operating system: Windows 10
 *  Compiler:
 *  Text editor / IDE:
 *
 *  Have you taken (part of) this course before: No
 *  Have you taken (part of) the Coursera course Algorithms, Part I or II: No
 *
 *  Hours to complete assignment (optional):
 *
 **************************************************************************** */

Programming Assignment 1: Percolation


/* *****************************************************************************
 *  Describe the data structures (i.e., instance variables) you used to
 *  implement the Percolation API.
 **************************************************************************** */
 boolean[][] n2_grid = This represents the n-by-n system of sites and it indicates
  if a site is either open or closed w/ true or false.
 int virtual_top = the index of the virtual top node
 int virtual_bottom = the index of the virtual bottom node
 int n_value = the value of n used to initialize the Percolation system, was useful
  in the getIndex function
 UF uf = the union find used
 int open_sites = the number of open sites

/* *****************************************************************************
 *  Briefly describe the algorithms you used to implement each method in
 *  the Percolation API.
 **************************************************************************** */
open(): this method set the corresponding site to true and checked if its neighbors were
 also open, if they were, they were the union-ed together
isOpen(): returns the boolean of the corresponding site in n2_grid
isFull(): checks if the given site and the virtual top are connected
numberOfOpenSites(): returns the counter of open sites using the instance variable
percolates(): tests to see if the top and bottom node are connected

/* *****************************************************************************
 *  Perform computational experiments to estimate the running time of
 *  PercolationStats.java for various values of n and T when implementing
 *  Percolation.java with QuickFindUF.java (not QuickUnionUF.java). Use a
 *  "doubling" hypothesis, where you successively increase either n or T by
 *  a constant multiplicative factor (not necessarily 2).
 *
 *  To do so, fill in the two tables below. Each table must have 5-10
 *  data points, ranging in time from around 0.25 seconds for the smallest
 *  data point to around 30 seconds for the largest one. Do not include
 *  data points that take less than 0.25 seconds.
 **************************************************************************** */

(keep T constant)
 T = 100
 multiplicative factor (for n) = 1.25

 n          time (seconds)       ratio         log ratio
--------------------------------------------------------
60          0.252                -----          -----
75          0.636                2.524          1.336
94          1.499                2.357          1.237
118         3.706                2.472          1.306
148         8.815                2.379          1.250
185         24.287               2.755          1.462
...

(keep n constant)
 n = 100
 multiplicative factor (for T) = 2

 T          time (seconds)       ratio         log ratio
--------------------------------------------------------
20          0.409                -----         -----
40          0.734                1.795         0.844
80          1.598                2.177         1.122
160         3.326                2.081         1.058
320         6.151                1.849         0.887
640         12.334               2.005         1.004
1280        24.561               1.991         0.994
...

/* *****************************************************************************
 *  Using the empirical data from the above two tables, give a formula
 *  (using tilde notation) for the running time (in seconds) of
 *  PercolationStats.java as function of both n and T, such as
 *
 *       ~ 5.3*10^-8 * n^5.0 * T^1.5
 *
 *  Briefly explain how you determined the formula for the running time.
 *  Recall that with tilde notation, you include both the coefficient
 *  and exponents of the leading term (but not lower-order terms).
 *  Use two significant figures for each coefficient and exponent
 *  (e.g., 5.3*10^-8 or 5.0).
 **************************************************************************** */

QuickFindUF running time (in seconds) as a function of n and T:

    ~  4.820*10^-5 * n^1.3 * T^1
       _______________________________________
I saw the running time as ' a * n^b * T^c '
I got b from the experiments when n was changing, and c from when T was changing.
Chose the experiment using n = 100, T = 1280 to then evaluate for a.

/* *****************************************************************************
 *  Repeat the previous two questions, but using WeightedQuickUnionUF
 *  (instead of QuickFindUF).
 **************************************************************************** */

(keep T constant)
 T = 100
multiplicative factor (for n) = 1.5

 n          time (seconds)       ratio         log ratio
--------------------------------------------------------
250         0.264                -----         -----
375         0.572                2.167         1.115
563         1.341                2.344         1.229
845         3.230                2.409         1.268
1268        10.252               3.174         1.666
...

(keep n constant)
 n = 100
multiplicative factor (for T) = 2

 T          time (seconds)       ratio         log ratio
--------------------------------------------------------
500         0.233                -----         -----
1000        0.408                1.751         0.808
2000        0.786                1.926         0.946
4000        1.458                1.851         0.891
8000        2.863                1.964         0.974
16000       5.751                2.009         1.006
32000       11.300               1.965         0.974
...

WeightedQuickUnionUF running time (in seconds) as a function of n and T:

    ~  8.87x10^-7 * n^1.3 * T ^1
      
