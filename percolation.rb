require_relative './balanced-quick-union-comp.rb'

class Percolation
  def initialize(n)
    @n = n
    @white_list = Array.new(@n**2){false}
    @v_top = @n**2
    @v_bottom = @n**2+1
    @uf = BalancedQuickUnionComp.new(@n**2+2)
    @n.times do |i|
      @uf.union(@v_top, i)
      @uf.union(@v_bottom, @n*(@n-1)+i)
    end
  end

  def union_side(p, q)
    @uf.union(p, q) if @white_list[q]
  end

  def open(x, y)
    p = p_index(x, y)
    return if @white_list[p]
    @white_list[p] = true

    union_side(p, p_index(x-1,y)) if x > 0
    union_side(p, p_index(x+1,y)) if x < @n-1
    union_side(p, p_index(x,y-1)) if y > 0
    union_side(p, p_index(x,y+1)) if y < @n-1
  end

  def p_index(x, y)
    y*@n+x
  end

  def random_open
    open(rand(@n), rand(@n))
  end

  def percolates?
    @uf.connected?(@v_top, @v_bottom)
  end

  def white_count
    @white_list.count(true)
  end

  def show
    @n.times do |y|
      @n.times do |x|
        print @white_list[p_index(x,y)] ? "O" : " "
      end
      puts ""
    end
  end
end


def monte(n)
  perc = Percolation.new(n)
  until perc.percolates?
    perc.random_open
  end
  perc
end

return unless  __FILE__ == $0
srand(0)
perc = Percolation.new(3)
perc.open(0,0)
perc.open(0,1)
perc.open(0,2)
p perc.percolates?
p perc.white_count
perc = Percolation.new(3)
perc.open(2,0)
perc.open(2,1)
perc.open(2,2)
perc.show
p perc.percolates?
p perc.white_count
p monte(100).white_count
p monte(100).white_count
monte(7).show
p "---"
monte(7).show
