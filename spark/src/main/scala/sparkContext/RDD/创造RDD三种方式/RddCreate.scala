package sparkContext.RDD.创造RDD三种方式

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Rdd创建三种方式。从集合中创建RDD；从外部存储创建RDD；从其他RDD创建。
  */
object RddCreate {
  def main(args: Array[String]): Unit = {
    //创建Rdd基本环境
    val conf: SparkConf = new SparkConf().setAppName("rddcreate").setMaster("local")
    val sc: SparkContext = new SparkContext(conf)
    //1.从集合（内存）中创建,makeRdd底层实现就是parallelize,没有任何区别
    val rddArray: RDD[Int] = sc.makeRDD(Array(1,2,3,4))  //Array用List也行
    rddArray.foreach(print(_))  //没有collect也可以直接输出结果


    println()
    println("**************")
    //2.从集合内存中创建
    val rddList: RDD[Int] = sc.parallelize(List(1,2,3,4))
    rddList.collect().foreach(print(_))   //collect


    //3.从外部存储中创建
    println()
    println("******从外部存储中创建********")
    //如果是hdfs写hdfs://hadop102:9000/
    val lines: RDD[String] = sc.textFile("in/two")  //读文件一行一行是String类型
    lines.collect().foreach(println(_))


  }
}
