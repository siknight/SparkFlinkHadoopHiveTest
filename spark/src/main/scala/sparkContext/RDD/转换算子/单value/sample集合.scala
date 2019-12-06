package sparkContext.RDD.转换算子.单value

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  * 对集合元素随机取样
  */
object sample集合 {

  def main(args: Array[String]): Unit = {
    //创建Rdd基本环境
    val conf: SparkConf = new SparkConf().setAppName("rddcreate").setMaster("local")
    val sc: SparkContext = new SparkContext(conf)
    val rdd: RDD[Int] = sc.makeRDD(List(1,2,3,4,5,6,7,8,9,10))

    //第一个参数withReplacement为true表示有放回，为false表示无放回，
    //fraction表示抽取的数量
    //第三个参数seed可以不传，不传的话是真正的随机数，每次产生都不一样
       //传入后，是伪随机数，每次只要参数相同，产生的数据都是一样的
    val sampleRdd: RDD[Int] =
        rdd.sample(true,0.2,2)
    sampleRdd.foreach(println(_))

  }

}
