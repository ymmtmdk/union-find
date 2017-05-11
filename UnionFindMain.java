public class UnionFindMain{
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
            // StdOut.println(p + " " + q);
        }
        StdOut.println(uf.count() + " components");
    }
}
