package sparkContext.RDD.读取JSON

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

import scala.util.parsing.json.JSON

object JSONTest {

  def main(args: Array[String]): Unit = {

    //创建Rdd基本环境
    val conf: SparkConf = new SparkConf().setAppName("rddcreate").setMaster("local")
    val sc: SparkContext = new SparkContext(conf)
    //读取json
    val rdd: RDD[String] =
      sc.textFile("in/json/user.json")

    //json map
    val rddMap: RDD[Option[Any]] = rdd.map(JSON.parseFull)
    //Any 是Map(name -> jiang, age -> 22.0)
    //Option[Any]是 Some(Map(name -> jiang, age -> 22.0))
    //空值时Option[Any]是None
    val options: Array[Option[Any]] = rddMap.collect()
    // println(options)
    println(options.mkString(","))




  }
}
