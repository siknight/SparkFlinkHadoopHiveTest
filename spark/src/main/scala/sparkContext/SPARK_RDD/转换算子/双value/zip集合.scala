package sparkContext.SPARK_RDD.转换算子.双value

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  *zip 拉链，
  *       注意点：两个集合的分区数量和元素数量必须相同，否则会报错
  */
object zip集合 {

  def main(args: Array[String]): Unit = {
    //创建Rdd基本环境
    val conf: SparkConf = new SparkConf().setAppName("rddcreate").setMaster("local")
    val sc: SparkContext = new SparkContext(conf)


    val rdd1: RDD[Int] = sc.makeRDD(Array(1,2,3))
    val rdd2: RDD[Int] = sc.makeRDD(Array(4,5,6))

    val rddZip: RDD[(Int, Int)] = rdd1.zip(rdd2)

    rddZip.foreach(x=>println(x))

  }

}
