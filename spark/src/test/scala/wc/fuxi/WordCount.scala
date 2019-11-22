package wc.fuxi

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object WordCount {
  def main(args: Array[String]): Unit = {
    //1.设置sparkContext（上下文sc）基本环境
    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("wctest")
    val context = new SparkContext(conf);
    //获取source
    val source: RDD[String] = context.textFile("in")
    println("source="+source.collect());

    //处理source
    val result: RDD[(String, Int)] = source.flatMap(_.split(" ")).map((_,1)).reduceByKey(_+_)
    //sink
    result.foreach(println)

  }

}
