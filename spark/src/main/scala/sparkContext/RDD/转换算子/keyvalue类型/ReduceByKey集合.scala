package sparkContext.RDD.转换算子.keyvalue类型

import org.apache.spark.rdd.RDD
import org.apache.spark.{HashPartitioner, SparkConf, SparkContext}

/**
  * 将每一个分区形成一个数组，形成新的RDD类型时RDD[Array[T]]
  */
object ReduceByKey集合 {
  //有Bykey的方法会将key相同的聚合，
  def main(args: Array[String]): Unit = {

    //创建Rdd基本环境
    val conf: SparkConf = new SparkConf().setAppName("rddcreate").setMaster("local")
    val sc: SparkContext = new SparkContext(conf)

    val rdd: RDD[String] = sc.parallelize(Array("hello","hello","scala","jiang"))
    val rddMap: RDD[(String, Int)] = rdd.map(x=>(x,2))
    val reduceRdd: RDD[(String, Int)] = rddMap.reduceByKey(_+_)
    reduceRdd.foreach(println(_))



  }

}
