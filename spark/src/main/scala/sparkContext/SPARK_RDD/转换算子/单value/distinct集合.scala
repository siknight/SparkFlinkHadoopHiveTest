package sparkContext.SPARK_RDD.转换算子.单value

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  * distinct 去重，有shuffle操作
  */
object distinct集合{

  def main(args: Array[String]): Unit = {
    //创建Rdd基本环境
    val conf: SparkConf = new SparkConf().setAppName("rddcreate").setMaster("local")
    val sc: SparkContext = new SparkContext(conf)
    val rdd: RDD[Int] = sc.makeRDD(List(1,2,1,5,2,9,6,1))
    //不传参是默认分区
//    val rddDistinct: RDD[Int] = rdd.distinct()
//    rddDistinct.saveAsTextFile("out/hadoop/distinct01")
    //传参是默认分区
    val rddDistinct: RDD[Int] = rdd.distinct(2)
    rddDistinct.saveAsTextFile("out/hadoop/distinct02")


  }

}
