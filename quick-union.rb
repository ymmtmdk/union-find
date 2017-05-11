class QuickUnion
  def initialize(size)
    @id = (0...size).to_a
  end

  def union(p, q)
    @id[root(q)] = root(p)
  end

  def connected?(p, q)
    root(p) == root(q)
  end

  private

  def root(i)
    until @id[i] == i
      i = @id[i]
    end
    i
  end
end

