class UnionFind implements UnionFindInterface {
    private final int size;
    private final int[] id;
    private int count;

    UnionFind(int _size) {
        this.size = _size;
        this.count = size;
        this.id = new int[_size];
        for (int i = 0; i < size; i++) {
            id[i] = i;
        }
    }

    public void union(int p, int q) {
        if (connected(p, q)) {
            return;
        }

        count -= 1;

        int ip = id[p];
        int iq = id[q];
        for (int i = 0; i < size; i++) {
            if (id[i] == iq) {
                id[i] = ip;
            }
        }
    }

    public boolean connected(int p, int q) {
        return id[p] == id[q];
    }

    public int count() {
        return this.count;
    }
}
