class UnionFind
  def initialize(size)
    @size = size
    @count = size
    @id = (0...size).to_a
  end

  def union(p, q)
    return if connected?(p, q)

    ip = @id[p]
    iq = @id[q]

    @count -= 1
    @size.times do |i|
      @id[i] = ip if @id[i] == iq
    end
    p([p, q], @id, @count, @id.uniq.size)
  end

  def connected?(p, q)
    @id[p] == @id[q]
  end
end

