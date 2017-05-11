require 'benchmark'
require_relative './my-union-find.rb'
require_relative './union-find.rb'
require_relative './quick-union.rb'
require_relative './balanced-quick-union.rb'
require_relative './balanced-quick-union-comp.rb'

def uf_run(uf_class, text)
  data = text.split(/\n/)

  uf = uf_class.new(data[0].to_i)
  data[1..-1].each do |line|
    p, q = line.split(/ /).map(&:to_i)
    uf.union(p, q)
  end
  [
    uf.connected?(0,0),
    uf.connected?(0,1),
    uf.connected?(2,1),
    uf.connected?(3,1),
    uf.connected?(4,2),
    uf.connected?(5,2),
  ]
end

def bench
  tiny = File.read('./tinyUF.txt')
  medium = File.read('./mediumUF.txt')
  large = File.read('./largeUF.txt')

  Benchmark.bm do |x|
    x.report("MyUnionFind, tiny") { uf_run(MyUnionFind, tiny) }
    # x.report("MyUnionFind, medium") { uf_run(MyUnionFind, medium) }
    x.report("QuickUnion, medium") { uf_run(QuickUnion, medium) }
    x.report("BalancedQuickUnion, medium") { uf_run(BalancedQuickUnion, medium) }
    x.report("BalancedQuickUnionComp, medium") { uf_run(BalancedQuickUnionComp, medium) }
    # x.report("QuickUnion, large") { uf_run(QuickUnion, large) }
    # x.report("BalancedQuickUnion, mlarge") { uf_run(BalancedQuickUnion, mlarge) }
    x.report("BalancedQuickUnionComp, large") { uf_run(BalancedQuickUnionComp, large) }
  end
end

tiny = File.read('./tinyUF.txt')
medium = File.read('./mediumUF.txt')
p uf_run(UnionFind, tiny)
p uf_run(QuickUnion, tiny)
p uf_run(BalancedQuickUnion, tiny)

bench
