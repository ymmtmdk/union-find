CP=lib/algs4.jar:target/scala-2.10/classes
java -cp "$CP" PercolationStats 1 5
java -cp "$CP" PercolationStats 2 5

cat ./mediumUF.txt | java -cp "$CP" UnionFindMain
cat ./input10.txt | java -cp "$CP" Percolation
java -cp "$CP" PercolationStats 200 100
java -cp "$CP" PercolationStats 200 100
java -cp "$CP" PercolationStats 2 100000
