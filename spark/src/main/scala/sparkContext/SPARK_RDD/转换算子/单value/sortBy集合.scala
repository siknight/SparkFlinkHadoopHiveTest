package sparkContext.SPARK_RDD.转换算子.单value

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  * repartition 重新分区，有shuffle过程
  */
object sortBy集合{

  def main(args: Array[String]): Unit = {
    //创建Rdd基本环境
    val conf: SparkConf = new SparkConf().setAppName("rddcreate").setMaster("local")
    val sc: SparkContext = new SparkContext(conf)
    val rdd: RDD[Int] = sc.makeRDD(List(1,2,3,4,5,6,7,8))
    //false 为倒序排列，true为正序排列。默认值为true
    val rddSort: RDD[Int] = rdd.sortBy(x=>x,false)
    rddSort.foreach(println(_))




  }

}
