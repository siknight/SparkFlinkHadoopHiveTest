package sparkContext.SPARK_RDD.血统lineage练习

import org.apache.spark.rdd.RDD
import org.apache.spark.{Dependency, SparkConf, SparkContext}

object Lineage {

  def main(args: Array[String]): Unit = {
    //创建Rdd基本环境
    val conf: SparkConf = new SparkConf().setAppName("rddcreate").setMaster("local")
    val sc: SparkContext = new SparkContext(conf)

    val rdd: RDD[String] =
       sc.makeRDD(List("hello scala","hello jiang","hello spark"))

    val rddwc: RDD[(String, Int)] =
        rdd.flatMap(_.split(" ")).map((_,1)).reduceByKey(_+_)


    //toDebugString 用于查看rdd的血统lineage
    println("*******查看rddwc的血统*********")
    val lineage: String = rddwc.toDebugString
    println(lineage)

    println("*******查看rddwc的依赖类型*********")
    val dependencies: Seq[Dependency[_]] = rddwc.dependencies
    //查看rddwc的依赖类型
    println(dependencies)


  }

}
