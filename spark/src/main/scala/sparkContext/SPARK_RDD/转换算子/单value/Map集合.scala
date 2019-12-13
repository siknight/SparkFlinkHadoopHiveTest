package sparkContext.SPARK_RDD.转换算子.单value

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Map集合 {

  def main(args: Array[String]): Unit = {
    //创建Rdd基本环境
    val conf: SparkConf = new SparkConf().setAppName("rddcreate").setMaster("local")
    val sc: SparkContext = new SparkContext(conf)
    //Ranage本身就是一个集合，相当于是Array(Range)，集合嵌套集合
    val rddArray: RDD[Range.Inclusive] = sc.makeRDD(Array(1 to 10))
    val rddInt: RDD[Int] = rddArray.flatMap(x=>x)  //先用flatmap将Range里面的每一个元素拆开
    val rddIntMap: RDD[Int] = rddInt.map(x=>x*2)
    rddIntMap.collect().foreach(println)


//     val arrayRDd: RDD[Int] = sc.makeRDD(1 to 10)  //可以直接这样写，这样写是int
  }

}
