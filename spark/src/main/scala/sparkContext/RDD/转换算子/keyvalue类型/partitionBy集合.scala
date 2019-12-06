package sparkContext.RDD.转换算子.keyvalue类型

import org.apache.spark.rdd.RDD
import org.apache.spark.{HashPartitioner, SparkConf, SparkContext}

/**
  * 将每一个分区形成一个数组，形成新的RDD类型时RDD[Array[T]]
  */
object partitionBy集合 {

  def main(args: Array[String]): Unit = {
    //创建Rdd基本环境
    val conf: SparkConf = new SparkConf().setAppName("rddcreate").setMaster("local")
    val sc: SparkContext = new SparkContext(conf)

    val rdd = sc.parallelize(Array((1,"aaa"),(2,"bbb"),(3,"ccc"),(4,"ddd")),4)

    println("开始分区："+rdd.partitions.size)
    //HashPartitioner是分区器
    val rddPartitionBy: RDD[(Int, String)] = rdd.partitionBy(new HashPartitioner(2))

    println("分区器分区："+rddPartitionBy.partitions.size)

    val arrRDD: Array[(Int, String)] = rddPartitionBy.collect()
    // glom把同一分区的放在同一个数组里
    val glomRDd: RDD[Array[(Int, String)]] = rddPartitionBy.glom()

    glomRDd.foreach(x=>println("("+x.mkString(",")+")"))


  }

}
