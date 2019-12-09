package sparkContext.RDD.转换算子.keyvalue类型

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  *
  */
object cogroup集合 {

  def main(args: Array[String]): Unit = {
    //创建Rdd基本环境
    val conf: SparkConf = new SparkConf().setAppName("rddcreate").setMaster("local")
    val sc: SparkContext = new SparkContext(conf)


      val rdd: RDD[(String, Int)] =
         sc.parallelize(Array(("a", 2), ("b", 3), ("a", 3)))
     val rdd2: RDD[(String, String)] =
      sc.parallelize(Array(("a", "a haha"), ("b", "b wode")))


   val rddco: RDD[(String, (Iterable[Int], Iterable[String]))] = rdd.cogroup(rdd2)

    println(rddco.collect().mkString(","))











  }

}
