package sparkContext.RDD.RDD中函数传递

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD

class Word extends  Serializable {

  def towc(rdd:RDD[String])={
    rdd.flatMap(x=>x.split(" ")).map((_,1)).
      reduceByKey(_+_)
  }
}

object WordCount {
  def main(args: Array[String]): Unit = {

    //创建Rdd基本环境
    val conf: SparkConf = new SparkConf().setAppName("rddcreate").setMaster("local")
    val sc: SparkContext = new SparkContext(conf)
    val rdd: RDD[String] =
      sc.makeRDD(List("hello","jiang","hello","lisi"))

    //封装后直接传rdd进去，直接就可以转为需要的内容
    val wc: RDD[(String, Int)] = new Word().towc(rdd)
    println(wc.collect().mkString(","))
  }
}
