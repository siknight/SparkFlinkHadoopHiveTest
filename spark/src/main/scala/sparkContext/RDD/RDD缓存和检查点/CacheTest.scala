package sparkContext.RDD.RDD缓存和检查点

import org.apache.spark.rdd.RDD
import org.apache.spark.{Dependency, SparkConf, SparkContext}

/**
  * cache缓存
  */
object Lineage {

  def main(args: Array[String]): Unit = {
    //创建Rdd基本环境
    val conf: SparkConf = new SparkConf().setAppName("rddcreate").setMaster("local")
    val sc: SparkContext = new SparkContext(conf)

    val rdd: RDD[String] =sc.makeRDD(List("scala"),3)

//    val rdd02: RDD[String] =
//      rdd.map(x=>x.toString+System.currentTimeMillis()).cache()
    //没有cache
    val rdd02: RDD[String] =
        rdd.map(x=>x.toString+System.currentTimeMillis())
    //有cache时，后面输出结果都一样
    //没有cache时，后面结果都不一样
    //一个SparkContext，所以就是一个Application
    //一个collect循环就是一个job
    //因为都没有shuffle过程，所以就一个阶段
    //这个阶段一共有三个分区，所以每个job三个分区
    while(true){
      println(rdd02.collect().mkString(","))
    }



  }

}
