package sparkContext.SPARK_RDD.转换算子.单value

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object mapPartitionsWithIndex集合 {

  def main(args: Array[String]): Unit = {
    //创建Rdd基本环境
    val conf: SparkConf = new SparkConf().setAppName("rddcreate").setMaster("local")
    val sc: SparkContext = new SparkContext(conf)

    val rdd: RDD[Int] = sc.makeRDD(1 to 10,2)
    //mapPartitions可以对RDD所有的分区进行遍历
    //mapPartitions效率高于map算子，减少了发送到执行器交互次数
    //mappartitions可能会导致内存溢出
//    val mpw: RDD[(Int, String)] = rdd.mapPartitionsWithIndex((x,y)=>y.map((_,"分区号"+x)))
    val mpw: RDD[(Int, String)] = rdd.mapPartitionsWithIndex {
      case (x, y) => y.map((_, "分区号" + x))
    }
    mpw.collect().foreach(t=>println(t))


  }

}
