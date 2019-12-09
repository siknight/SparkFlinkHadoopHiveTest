package sparkContext.RDD.RDD任务划分

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD

object TaskTest {

  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setAppName("rddcreate").setMaster("local")
    val sc: SparkContext = new SparkContext(conf)

    val rdd: RDD[String] =
       sc.makeRDD(List("hello scala","hello hadoop"),2)

    val wc: RDD[(String, Int)] =
       rdd.flatMap(_.split(" ")).map((_,1)).reduceByKey(_+_)
    //一个SparkContext，所以就是一个Application
    //一个collect所以就一个job
    //因为flatmap和map没有shuffle，所以这是一个阶段，因为
    //reduceByKey有shuffle过程，所以又加一个阶段，一共两个阶段
    //第一个阶段有两个分区，有两个task
    //第二个阶段也有两个分区，也有两个task，所以一共4个task
    wc.collect().foreach(println(_))

  }

}
