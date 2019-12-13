package sparkContext.SPARK_RDD.RDD中函数传递

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD

/**
  * 第一种传递一个方法
  */
//Serializable这个用java的和scala都行
class Search01(query:String) extends  Serializable {

  //过滤出包含query字符串的数据
  def isMatch(s: String) = {
    s.contains(query)
  }

  //过滤出包含字符串的RDD
  def getMatch1(rdd: RDD[String]) = {
    rdd.filter(isMatch)
  }

}

object TestSearch01{
  def main(args: Array[String]): Unit = {
    //创建Rdd基本环境
    val conf: SparkConf = new SparkConf().setAppName("rddcreate").setMaster("local")
    val sc: SparkContext = new SparkContext(conf)
    val rdd: RDD[String] =
          sc.makeRDD(List("hello","jiang","hehe","lisi"))
    //创建一个search对象
     val search01: Search01 = new Search01("he")
    //第一种传递一个方法
    val rddMatch01: RDD[String] = search01.getMatch1(rdd)
    println(rddMatch01.collect().mkString(","))


  }
}




