package sparkContext.RDD.转换算子.双value

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  * //cartisian 笛卡尔积
  */
object cartesian集合 {

  def main(args: Array[String]): Unit = {
    //创建Rdd基本环境
    val conf: SparkConf = new SparkConf().setAppName("rddcreate").setMaster("local")
    val sc: SparkContext = new SparkContext(conf)


    val rdd1: RDD[Int] = sc.makeRDD(1 to 5)
    val rdd2: RDD[Int] = sc.makeRDD(3 to 8)
    //cartisian 笛卡尔积
   val rddCartisian: RDD[(Int, Int)] = rdd1.cartesian(rdd2)
    rddCartisian.foreach(x=>println(x))

  }

}
