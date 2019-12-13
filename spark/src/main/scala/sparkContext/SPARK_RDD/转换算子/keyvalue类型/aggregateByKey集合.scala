package sparkContext.SPARK_RDD.转换算子.keyvalue类型

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  * aggregateByKey
  * 1，按key先给一个初始值
  * 2.按key对分区内数据计算
  * 3.按key对分区内计算的数据再进行分区外计算
  */
object aggregateByKey集合 {
  //有Bykey的方法会将key相同的聚合，
  def main(args: Array[String]): Unit = {

    //创建Rdd基本环境
    val conf: SparkConf = new SparkConf().setAppName("rddcreate").setMaster("local")

    val sc: SparkContext = new SparkContext(conf)


    val rdd =
       sc.parallelize(List(("a",3),("a",2),("c",4),("b",3),("c",6),("c",8)),2)
    //进行统计计算
    val value: RDD[(String, Int)] =
        rdd.aggregateByKey(0)((x,y)=>Math.max(x,y),(v1,v2)=>v1+v2)
    //输出

    println(value.collect().mkString(","))  //(a,3)(c,11),(b,3)

    println("*********2222******")
    val value02: RDD[(String, Int)] = rdd.aggregateByKey(10)((x,y)=>Math.max(x,y),(v1,v2)=>v1+v2)
    //(b,10)(a,10),(c,20)
    println(value02.collect().mkString(","))



  }

}
