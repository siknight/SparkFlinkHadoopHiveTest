package sparkContext.RDD.转换算子.keyvalue类型

import org.apache.spark.rdd.RDD
import org.apache.spark.{HashPartitioner, SparkConf, SparkContext}

/**
  * 将每一个分区形成一个数组，形成新的RDD类型时RDD[Array[T]]
  */
object GroupByKey集合 {

  def main(args: Array[String]): Unit = {
    //创建Rdd基本环境
    val conf: SparkConf = new SparkConf().setAppName("rddcreate").setMaster("local")
    val sc: SparkContext = new SparkContext(conf)

    val rdd: RDD[String] = sc.parallelize(Array("hello","hello","scala","jiang"))
    val rddMap: RDD[(String, Int)] = rdd.map(x=>(x,2))
    val gg: RDD[(String, Iterable[Int])] = rddMap.groupByKey()
    gg.foreach(t=>println(t))
    println("--------------------")
    val result: RDD[(String, Int)] = gg.map(t=>(t._1,t._2.sum))  //sum求综合
    result.foreach(println)






  }

}
