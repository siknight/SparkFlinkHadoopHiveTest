package sparkContext.RDD.转换算子.keyvalue类型

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  *
  */
object CombineByKey集合 {

  def main(args: Array[String]): Unit = {
    //创建Rdd基本环境
    val conf: SparkConf = new SparkConf().setAppName("rddcreate").setMaster("local")
    val sc: SparkContext = new SparkContext(conf)


      val rdd: RDD[(String, Int)] =
         sc.parallelize(Array(("a", 2), ("b", 3), ("a", 3), ("b", 4), ("a", 5), ("b", 6)),2)


      //
      val rddCombineByKey: RDD[(String, (Int, Int))] =
         rdd.combineByKey(x=>(x,1),(y:(Int,Int),z)=>(y._1+z,y._2+1),(v1:(Int,Int),v2:(Int,Int))=>(v1._1+v2._1,v1._2+v2._2))

      //
    println(rddCombineByKey.collect().mkString(","))







  }

}
