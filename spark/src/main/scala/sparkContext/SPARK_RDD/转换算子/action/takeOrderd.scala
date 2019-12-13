package sparkContext.SPARK_RDD.转换算子.action

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object takeOrderd {

  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setAppName("rddcreate").setMaster("local")
    val sc: SparkContext = new SparkContext(conf)
    val rdd: RDD[Int] = sc.makeRDD(List(1,3,8,6,5))
    val takeOrder: Array[Int] = rdd.takeOrdered(3)
    println(takeOrder.mkString(","))
  }

}
