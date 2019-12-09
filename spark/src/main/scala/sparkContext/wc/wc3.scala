package sparkContext.wc

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object wc3 {
  def main(args: Array[String]): Unit = {
    //1. 创建环境
    val conf: SparkConf = new SparkConf().setMaster("local[1]").setAppName("test01")
    val sc: SparkContext = new SparkContext(conf)
    //2.读取数据
    /**
      * hello world
      * hello scala
      * hello spark
      */
    val lines: RDD[String] = sc.textFile("in/word1")

    val words: RDD[String] = lines.flatMap {
      case x => x.split(" ")
    }

    val wc: RDD[(String, Int)] = words.map {
      case x => (x, 1)
    }


    val rdd2: RDD[(String, Int)] = wc.map {
      case (x, y) => (x, 2)  //case可以直接将A =》B 这种形式的A转化为（x,y）

    }


    println(rdd2.collect().mkString(","))



  }

}
