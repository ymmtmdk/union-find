import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class PercolationTest{
  @Test
  public void isOpen() {
    Percolation perc = new Percolation(2);
    assertEquals(false, perc.isOpen(1,1));
    perc.open(1,1);
    assertEquals(true, perc.isOpen(1,1));
    perc.open(1,1);
    assertEquals(true, perc.isOpen(1,1));
  }

  @Test
  public void isFull() {
    Percolation perc = new Percolation(2);
    assertEquals(false, perc.isFull(1,1));
    assertEquals(false, perc.isFull(2,1));
    assertEquals(false, perc.isFull(2,2));
    perc.open(2,2);
    assertEquals(false, perc.isFull(1,1));
    assertEquals(false, perc.isFull(2,1));
    perc.open(1,1);
    assertEquals(true, perc.isFull(1,1));
    assertEquals(false, perc.isFull(1,2));
    assertEquals(false, perc.isFull(2,1));
    assertEquals(false, perc.isFull(2,2));
  }

  @Test
  public void input3(){
    Percolation perc = new Percolation(3);
    perc.open(1,3);
    perc.open(2,3);
    perc.open(3,3);
    perc.open(3,1);
    perc.open(2,1);
    perc.open(1,1);
  }

  @Test
  public void numberOfOpenSites() {
    Percolation perc = new Percolation(2);
    assertEquals(0, perc.numberOfOpenSites());
    perc.open(1,1);
    assertEquals(1, perc.numberOfOpenSites());
    perc.open(2,1);
    assertEquals(2, perc.numberOfOpenSites());
    perc.open(2,1);
    assertEquals(2, perc.numberOfOpenSites());
  }
}
