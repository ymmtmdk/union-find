class BalancedQuickUnion
  attr_reader :id, :sz
  def initialize(size)

    @id = (0...size).to_a
    @sz = Array.new(size){1}
  end

  def union(p, q)
    root_p  = root(p)
    root_q  = root(q)
    return if root_p == root_q
    if @sz[root_p] < @sz[root_q]
      merge(root_p, root_q)
    else
      merge(root_q, root_p)
    end
  end

  def connected?(p, q)
    root(p) == root(q)
  end

  private

  def merge(dst, src)
    @id[src] = @id[dst]
    @sz[dst] += @sz[src]
  end

  def root(i)
    until @id[i] == i
      i = @id[i]
    end
    i
  end
end

