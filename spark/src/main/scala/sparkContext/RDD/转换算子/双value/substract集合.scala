package sparkContext.RDD.转换算子.双value

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  *substract:计算差的一种函数，
  *   去除两个RDD中相同的元素，不同的RDD将保留下来
  */
object substract集合 {

  def main(args: Array[String]): Unit = {
    //创建Rdd基本环境
    val conf: SparkConf = new SparkConf().setAppName("rddcreate").setMaster("local")
    val sc: SparkContext = new SparkContext(conf)


    val rdd1: RDD[Int] = sc.makeRDD(1 to 5)
    val rdd2: RDD[Int] = sc.makeRDD(3 to 8)
    //substract rdd1调用rdd2时，会将rdd1中在rdd2中有的数据去掉，然后输出1,2
    val rddSubstract: RDD[Int] = rdd1.subtract(rdd2,1)
    rddSubstract.foreach(println(_))


  }

}
