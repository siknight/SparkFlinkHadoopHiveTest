package sparkContext.SPARK_RDD.转换算子.双value

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  *union 对源RDD和参数RDD求并集后返回一个新的RDD
  */
object union集合 {

  def main(args: Array[String]): Unit = {
    //创建Rdd基本环境
    val conf: SparkConf = new SparkConf().setAppName("rddcreate").setMaster("local")
    val sc: SparkContext = new SparkContext(conf)


    val rdd1: RDD[Int] = sc.makeRDD(1 to 5)
    val rdd2: RDD[Int] = sc.makeRDD(3 to 8)
    //union 结合后不会去重
    val rddTotal: RDD[Int] = rdd1.union(rdd2).distinct()

    rddTotal.foreach(println(_))

  }

}
