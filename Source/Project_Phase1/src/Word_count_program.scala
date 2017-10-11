val textFile = sc.textFile("hdfs://localhost:50071/wordcount/input/hdfs_tweet.txt")
val counts = textFile.flatMap(line => line.split(" ")).map(word => (word, 1)).reduceByKey(_ + _)
counts.saveAsTextFile("hdfs://localhost:50071/wordcount/output")