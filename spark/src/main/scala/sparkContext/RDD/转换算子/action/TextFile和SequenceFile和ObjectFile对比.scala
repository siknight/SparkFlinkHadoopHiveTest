package sparkContext.RDD.转换算子.action

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object TextFile和SequenceFile和ObjectFile对比 {

  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setMaster("local").setAppName("hehe")
    val sc = new SparkContext(conf)
    val rdd: RDD[(String,Int)] = sc.makeRDD(List(("hello",1),("jiang",1)))

    //三种保存文件方式对比
    rdd.saveAsTextFile("out/spark/file/out1")

    rdd.saveAsSequenceFile("out/spark/file/out2")

    rdd.saveAsObjectFile("out/spark/file/out3")

  }

}
