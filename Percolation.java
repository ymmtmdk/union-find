import java.security.SecureRandom;
import java.util.Random;

class Percolation{
  private final int n;
  private final boolean white_list[];
  private final int v_top, v_bottom;
  private final BalancedQuickUnionComp uf;
  private final Random random;

  Percolation(int n){
    this.n = n;
    this.white_list = new boolean[n*n];
    this.v_top = n*n;
    this.v_bottom = n*n+1;
    this.random = new SecureRandom();
    this.uf = new BalancedQuickUnionComp(n*n+2);
    for (int i = 0; i < n; i++){
      uf.union(v_top, i);
      uf.union(v_bottom, n*(n-1)+i);
    }
  }

  void union_side(int p, int q){
    if (white_list[q]){
      uf.union(p, q);
    }
  }

  void open(int x, int y){
    int p = p_index(x, y);
    if (white_list[p]){
      return;
    }

    white_list[p] = true;

    if (x > 0) union_side(p, p_index(x-1, y));
    if (x < n-1) union_side(p, p_index(x+1, y));
    if (y > 0) union_side(p, p_index(x, y-1));
    if (y < n-1) union_side(p, p_index(x, y+1));
  }

  private int p_index(int x, int y){
    return y*n+x;
  }

  void random_open(){
    open(random.nextInt(n), random.nextInt(n));
  }

  boolean is_percolates(){
    return uf.connected(v_top, v_bottom);
  }

  int white_count(){
    int c = 0;
    for (int i = 0; i < n*n; i++){
      if (white_list[i]) c+= 1;
    }
    return c;

  }
}
