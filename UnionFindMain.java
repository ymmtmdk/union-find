import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class UnionFindMain {
    public static void main(String[] args) {
        int n = StdIn.readInt();
        // UnionFindInterface uf = new UnionFind(n);
        // UnionFindInterface uf = new QuickUnion(n);
        // UnionFindInterface uf = new BalancedQuickUnion(n);
        UnionFindInterface uf = new BalancedQuickUnionComp(n);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (uf.connected(p, q)) continue;
            uf.union(p, q);
        }
        StdOut.println(uf.count() + " components");

        Percolation perc = new Percolation(3);
        perc.open(1, 1);
        perc.open(2, 1);
        perc.open(3, 1);
        StdOut.println("is_percolates: " + perc.percolates());
        perc = new Percolation(1000);
    }
}
