def log_star(n)
  n <= 1 ? 0 : 1+log_star(Math.log2(n))
end

def log_star2(n)
  c = 0
  while n > 1
    n = Math.log2(n)
    c += 1
  end
  c
end

p log_star(2**16r)
p log_star(2**16r+1)
p log_star2(2**16r)
p log_star2(2**16r+1)
p log_star2(2**(2**16+1))
