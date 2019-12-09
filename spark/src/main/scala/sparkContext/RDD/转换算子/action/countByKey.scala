package sparkContext.RDD.转换算子.action

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object foreach {

  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setAppName("rddcreate").setMaster("local")
    val sc: SparkContext = new SparkContext(conf)
    val rdd: RDD[(String, Int)] = sc.makeRDD(List(("hello",1),("hello",1)))
    val count: collection.Map[String, Long] = rdd.countByKey()
    println(count.mkString(","))

  }

}
