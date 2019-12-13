package sparkContext.SPARK_RDD.转换算子.keyvalue类型

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  *
  */
object FoldByKey集合 {

  def main(args: Array[String]): Unit = {
    //创建Rdd基本环境
    val conf: SparkConf = new SparkConf().setAppName("rddcreate").setMaster("local")
    val sc: SparkContext = new SparkContext(conf)


      val rdd: RDD[(String, Int)] =
         sc.parallelize(Array(("a", 1), ("b", 1), ("a", 1), ("b",1), ("a", 1), ("b", 1)),2)


      //
     val rdd02: RDD[(String, Int)] = rdd.foldByKey(0)((x,y)=>x+y)


    println(rdd02.collect().mkString(","))
      //








  }

}
