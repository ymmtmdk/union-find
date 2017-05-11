class QuickUnion implements UnionFindInterface{
  private final int size;
  private final int[] id;
  private int count;

  QuickUnion(int _size){
    this.size = _size;
    this.count = size;
    this.id = new int[_size];
    for (int i = 0; i < size; i++){
      id[i] = i;
    }
  }

  public void union(int p, int q){
    int root_p = root(p);
    int root_q = root(q);
    if (root_p == root_q){
      return;
    }

    count -= 1;
    id[root_q] = id[root_p];
  }

  public boolean connected(int p, int q){
    return root(p) == root(q);
  }

  public int count(){
    return this.count;
  }

  private int root(int p){
    while (id[p] != p){
      p = id[p];
    }
    return p;
  }
}
