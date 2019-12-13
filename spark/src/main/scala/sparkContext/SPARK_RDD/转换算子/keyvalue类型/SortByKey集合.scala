package sparkContext.SPARK_RDD.转换算子.keyvalue类型

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  * 将每一个分区形成一个数组，形成新的RDD类型时RDD[Array[T]]
  */
object SortByKey集合 {
  //有Bykey的方法会将key相同的聚合，
  def main(args: Array[String]): Unit = {

    //创建Rdd基本环境
    val conf: SparkConf = new SparkConf().setAppName("rddcreate").setMaster("local")
    val sc: SparkContext = new SparkContext(conf)

    val rdd = sc.parallelize(Array((3,"aa"),(6,"cc"),(2,"bb"),(1,"dd")))
    //默认从小到大排列，
    val rddSort: RDD[(Int, String)] = rdd.sortByKey(false)

    rddSort.foreach(println(_))



  }

}
