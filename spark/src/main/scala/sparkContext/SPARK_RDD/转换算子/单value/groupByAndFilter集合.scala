package sparkContext.SPARK_RDD.转换算子.单value

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object groupByAndFilter集合{

  def main(args: Array[String]): Unit = {
    //创建Rdd基本环境
    val conf: SparkConf = new SparkConf().setAppName("rddcreate").setMaster("local")
    val sc: SparkContext = new SparkContext(conf)
    val rdd: RDD[String] = sc.makeRDD(List("hello","lisi","hello","hello","lisi","jiang"))

    //分组,按照传入的函数返回值进行分组
    val rddGrooup: RDD[(String, Iterable[String])] = rdd.groupBy(x=>x)
    rddGrooup.foreach(println(_))

    //过滤,。返回一个新的RDD，该RDD由经过func函数计算后返回值为true的输入元素组成。
    val rddFilter: RDD[String] = rdd.filter(str=>str.contains("hello"))
    rddFilter.foreach(println(_))


  }

}
