package sparkContext.RDD.转换算子.action

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object foreach2 {

  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setAppName("rddcreate").setMaster("local")
    val sc: SparkContext = new SparkContext(conf)
    val rdd: RDD[Int] = sc.makeRDD(1 to 3)
     rdd.foreach(println(_))
  }

}
