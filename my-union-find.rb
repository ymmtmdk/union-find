class MyUnionFind
  def initialize(size)
    @map = (0...size).map{|i| [i]}
  end

  def union(p, q)
    mp = @map[p]
    mq = @map[q]
    mq.each do |i|
      @map[i] = mp
    end

    mp.concat(mq)
  end

  def connected?(p, q)
    @map[p] == @map[q]
  end
end

