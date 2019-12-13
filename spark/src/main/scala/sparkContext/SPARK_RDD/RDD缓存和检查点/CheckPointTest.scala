package sparkContext.SPARK_RDD.RDD缓存和检查点

import org.apache.spark.rdd.RDD
import org.apache.spark.{Dependency, SparkConf, SparkContext}

/**
  * 检查点练习
  */
object CheckPointTest {

  def main(args: Array[String]): Unit = {
    //创建Rdd基本环境
    val conf: SparkConf = new SparkConf().setAppName("rddcreate").setMaster("local")
    val sc: SparkContext = new SparkContext(conf)
    //设置检查点存储目录，如果存储在hdfs上，写hdfs://hadoop102:9000/xxx
    sc.setCheckpointDir("out/checkpoint")

    val rdd: RDD[String] =sc.makeRDD(List("scala"))


    val rdd02: RDD[String] =
        rdd.map(x=>x.toString+System.currentTimeMillis())

    println("---检查点前-----")
    val xuetong1: String = rdd02.toDebugString
    println(xuetong1)
    //scala1575704925433
     //设置检查点
     rdd02.checkpoint()

    for (i<- 1 to 3){
      println(rdd02.collect().mkString(","))
    }

    println("---检查点后-----")
    val xuetong: String = rdd02.toDebugString
    println(xuetong)



//    ---检查点前-----
//    (1) MapPartitionsRDD[1] at map at CheckPointTest.scala:22 []
//    |  ParallelCollectionRDD[0] at makeRDD at CheckPointTest.scala:18 []

//    ---检查点后-----
//    (1) MapPartitionsRDD[1] at map at CheckPointTest.scala:22 []
//    |  ReliableCheckpointRDD[2] at collect at CheckPointTest.scala:32 []





  }

}
