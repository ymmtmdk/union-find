import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private class Sites {
        private final boolean[] openSites;
        private int numberOfOpenSites;

        Sites(int size, int[] initial) {
            this.openSites = new boolean[size];
            for (int i = 0; i < initial.length; i++) {
                openSites[initial[i]] = true;
            }
            this.numberOfOpenSites = 0;
        }

        // number of open sites
        int numberOfOpenSites() {
            return numberOfOpenSites;
        }

        void open(int row, int col) {
            if (!isOpen(row, col)) {
                numberOfOpenSites += 1;
                openSites[indexOfP(row, col)] = true;
            }
        }

        boolean isOpen(int row, int col) {
            return openSites[indexOfP(row, col)];
        }
    }

    private final int n;
    private final Sites openSites;
    private final int virtualTop, virtualBottom, virtualLeft, virtualRight;
    private final WeightedQuickUnionUF unionFind, unionFindForFull;

    public Percolation(int n) {
        if (n <= 0) {
            throw new java.lang.IllegalArgumentException();
        }
        this.n = n;
        this.virtualTop = n * n;
        this.virtualBottom = virtualTop + 1;
        this.virtualLeft = virtualTop + 2;
        this.virtualRight = virtualTop + 3;
        this.openSites = new Sites(n * n + 4, new int[] {virtualTop, virtualBottom});
        this.unionFind = new WeightedQuickUnionUF(n * n + 4);
        this.unionFindForFull = new WeightedQuickUnionUF(n * n + 1);
    }

    // open site (row, col) if it is not open already
    public void open(int row, int col) {
        if (isOpen(row, col)) {
            return;
        }

        openSites.open(row, col);

        unionNeighbor(row, col, row, col - 1);
        unionNeighbor(row, col, row, col + 1);
        unionNeighbor(row, col, row - 1, col);
        unionNeighbor(row, col, row + 1, col);
    }

    // is site (row, col) open?
    public boolean isOpen(int row, int col) {
        boundCheck(row, col);
        return openSites.isOpen(row, col);
    }

    // is site (row, col) full?
    public boolean isFull(int row, int col) {
        boundCheck(row, col);
        return unionFindForFull.connected(virtualTop, indexOfP(row, col));
    }

    // number of open sites
    public int numberOfOpenSites() {
        return openSites.numberOfOpenSites();
    }

    // does the system percolate?
    public boolean percolates() {
        return unionFind.connected(virtualTop, virtualBottom);
    }

    private void boundCheck(int row, int col) {
        if (row > n || col > n || row < 1 || col < 1) {
            throw new java.lang.IndexOutOfBoundsException();
        }
    }

    private void unionNeighbor(int row1, int col1, int row2, int col2) {
        if (openSites.isOpen(row2, col2)) {
            int p = indexOfP(row1, col1);
            int q = indexOfP(row2, col2);
            unionFind.union(p, q);
            if (q <= virtualTop) {
                unionFindForFull.union(p, q);
            }
        }
    }

    private int indexOfP(int row, int col) {
        if (row < 1) return virtualTop;
        if (row > n) return virtualBottom;
        if (col < 1) return virtualLeft;
        if (col > n) return virtualRight;

        return (row - 1) * n + col - 1;
    }

    private static void show(Percolation perc, int n) {
        StdOut.println("------------");
        for (int row = 1; row <= n; row++) {
            for (int col = 1; col <= n; col++) {
                if (perc.isOpen(row, col)) {
                    StdOut.print(perc.isFull(row, col) ? "[31m￭" : "[37m￭");
                } else {
                    StdOut.print(" ");
                }
            }
            StdOut.println(" ");
        }
        StdOut.print("[37m");
    }

    public static void main(String[] args) {
        Percolation perc = new Percolation(2);
        show(perc, 2);
        StdOut.println("p: " + perc.percolates());
        perc.open(2, 2);
        show(perc, 2);
        StdOut.println("p: " + perc.percolates());
        perc.open(2, 1);
        show(perc, 2);
        StdOut.println("p: " + perc.percolates());
        perc.open(1, 2);
        show(perc, 2);
        StdOut.println("p: " + perc.percolates());
        perc.open(1, 1);
        show(perc, 2);
        StdOut.println("p: " + perc.percolates());

        int n = StdIn.readInt();
        perc = new Percolation(n);
        while (!StdIn.isEmpty()) {
            show(perc, n);
            int row = StdIn.readInt();
            int col = StdIn.readInt();
            perc.open(row, col);
        }
        show(perc, n);
    }
}
