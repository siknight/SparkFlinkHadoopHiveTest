package sparkContext.RDD.转换算子.单value

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  * 减少分区数
  */
object coalesce集合 {

  def main(args: Array[String]): Unit = {
    //创建Rdd基本环境
    val conf: SparkConf = new SparkConf().setAppName("rddcreate").setMaster("local")
    val sc: SparkContext = new SparkContext(conf)
    val rdd: RDD[Int] = sc.makeRDD(1 to 16,4)
    println("分区前="+rdd.partitions.size)
    //默认没有suffle,可以在参数里传，没有suffle的时候就是将后面的两个分区合并成一个
    val rddCoal: RDD[Int] = rdd.coalesce(3)
    println("分区后="+rddCoal.partitions.size)


  }

}
