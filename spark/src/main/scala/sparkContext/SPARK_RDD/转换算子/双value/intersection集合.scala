package sparkContext.SPARK_RDD.转换算子.双value

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  *intersection 取两个集合交集
  */
object intersection集合 {

  def main(args: Array[String]): Unit = {
    //创建Rdd基本环境
    val conf: SparkConf = new SparkConf().setAppName("rddcreate").setMaster("local")
    val sc: SparkContext = new SparkContext(conf)


    val rdd1: RDD[Int] = sc.makeRDD(1 to 5)
    val rdd2: RDD[Int] = sc.makeRDD(3 to 8)
    //intersection 取两个集合交集
   val rddIntersection: RDD[Int] = rdd1.intersection(rdd2)
    rddIntersection.foreach(println)

  }

}
