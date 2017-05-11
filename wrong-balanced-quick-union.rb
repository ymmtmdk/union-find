class UF
  def initialize(size)
    @id = (0...size).to_a
  end

  def union(p, q)
    root_p, depth_p = root_with_depth(p)
    root_q, depth_q = root_with_depth(q)
    if depth_q < depth_p
      @id[root_q] = root_p
    else
      @id[root_p] = root_q
    end
  end

  def connected?(p, q)
    root(p) == root(q)
  end

  def root_with_depth(i)
    depth = 0
    until @id[i] == i
      depth += 1
      i = @id[i]
    end
    [i, depth]
  end

  def root(i)
    until @id[i] == i
      i = @id[i]
    end
    i
  end
end

uf = UF.new(8)
uf.union(1, 4)
uf.union(4, 5)
uf.union(2, 3)
uf.union(3, 6)
uf.union(6, 7)
p uf.connected?(0,1) == false
p uf.connected?(1,4) == true
p uf.connected?(1,5) == true
p uf.connected?(1,7) == false
uf = UF.new(100)
99.times do |i|
  uf.union(i,i+1)
end

p uf.connected?(0,99)
p uf.root_with_depth(0)
