class BalancedQuickUnionComp implements UnionFindInterface{
  private final int size;
  private final int[] id;
  private final int[] sz;
  private int count;

  BalancedQuickUnionComp(int _size){
    this.size = _size;
    this.count = size;
    this.id = new int[_size];
    this.sz = new int[_size];
    for (int i = 0; i < size; i++){
      id[i] = i;
      sz[i] = 1;
    }
  }

  public void union(int p, int q){
    int root_p = root(p);
    int root_q = root(q);
    if (root_p == root_q){
      return;
    }

    count -= 1;
    if (sz[root_p] < sz[root_q]){
      id[root_q] = id[root_p];
      sz[root_p] += sz[root_q];
    } else{
      id[root_p] = id[root_q];
      sz[root_q] += sz[root_p];
    }
  }

  public boolean connected(int p, int q){
    return root(p) == root(q);
  }

  public int count(){
    return this.count;
  }

  private int root(int p){
    while (id[p] != p){
      id[p] = id[id[p]];
      p = id[p];
    }
    return p;
  }
}
