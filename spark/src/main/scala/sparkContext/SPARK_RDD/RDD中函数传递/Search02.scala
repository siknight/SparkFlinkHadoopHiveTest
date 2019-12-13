package sparkContext.SPARK_RDD.RDD中函数传递

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD

/**
  * 第一种传递一个方法
  */
//Serializable这个用java的和scala都行
class Search02(query:String) extends  Serializable {

  //过滤出包含字符串的RDD

  def getMatch2(rdd: RDD[String]) = {
    //传递一个变量
    rdd.filter(x => x.contains(query))
  }
}

object TestSearch02{
  def main(args: Array[String]): Unit = {
    //创建Rdd基本环境
    val conf: SparkConf = new SparkConf().setAppName("rddcreate").setMaster("local")
    val sc: SparkContext = new SparkContext(conf)
    val rdd: RDD[String] =
          sc.makeRDD(List("hello","jiang","hehe","lisi"))
    //创建一个search对象
    val search02: Search02 = new Search02("he")
    val rddM: RDD[String] = search02.getMatch2(rdd)
    println(rddM.collect().mkString(","))




  }
}




