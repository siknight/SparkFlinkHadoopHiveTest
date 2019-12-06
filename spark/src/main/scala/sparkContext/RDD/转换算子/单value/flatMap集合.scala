package sparkContext.RDD.转换算子.单value

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object flatMap集合 {

  def main(args: Array[String]): Unit = {
    //创建Rdd基本环境
    val conf: SparkConf = new SparkConf().setAppName("rddcreate").setMaster("local")
    val sc: SparkContext = new SparkContext(conf)


    val rdd: RDD[List[Int]] = sc.makeRDD(Array(List(1,2),List(3,4)))
    val unit: RDD[Int] = rdd.flatMap(x=>x)
    unit.collect().foreach(println)
  }

}
