package sparkContext.SPARK_RDD.转换算子.action

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object taken {

  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setAppName("rddcreate").setMaster("local")
    val sc: SparkContext = new SparkContext(conf)
    val rdd: RDD[Int] = sc.makeRDD(1 to 3)
    val take: Array[Int] = rdd.take(2)
    println(take.mkString(","))
  }

}
