package sparkContext.SPARK_SQL

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.{DataFrame, Row, SparkSession}

/**
  *
  *
  */
object DataFrame和RDD转换{

  def main(args: Array[String]): Unit = {
    //1.创建sql基本环境
    val conf: SparkConf = new SparkConf().setMaster("local").setAppName("sql tranform")
    val spark: SparkSession = SparkSession.builder().config(conf).getOrCreate()

    //session转context
    val sc: SparkContext = spark.sparkContext

    //2.操作
    val rdd: RDD[(String, Int)] = sc.makeRDD(List(("lisi",22),("jiang",21)))

    //rdd转DataFrame
    //这里的spark是sparkSession的对象
    import  spark.implicits._
    //rdd转为df直接加上字段就行
    println("*********rdd转为DataFrame***********")
    val df: DataFrame = rdd.toDF("name","age")
    df.show()

    println("*********DataFrame转为rdd***********")
    //会将数据类型变成Row类型
    val tordd: RDD[Row] = df.rdd
    println(tordd.collect().mkString(","))
    tordd.foreach(x=>println(x))

    println("**************rdd转为DataFrame,第二种方式*********************")

    //隐式转换
    //spark 为SparkSession创建的对象
    import  spark.implicits._

    val userRDD: RDD[user] = rdd.map {
      case (name, age) => {
        user(name, age)
      }
    }

    //已经有结构了，就不用增加结构了，这里可以加也可以不加
    val userDF: DataFrame = userRDD.toDF
    userDF.show()
    println("****selectname*****")
    userDF.createTempView("user")
    spark.sql("select name from user").show()
    spark.close()

  }

}

//这个尽量放在外边，放在main里边可能出问题
case class  user(name:String,age:Int)
