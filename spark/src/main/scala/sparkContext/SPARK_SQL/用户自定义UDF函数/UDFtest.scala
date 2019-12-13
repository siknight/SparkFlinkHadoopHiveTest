package sparkContext.SPARK_SQL.用户自定义UDF函数

import org.apache.spark.SparkConf
import org.apache.spark.sql.{DataFrame, SparkSession}

object UDFtest {
  def main(args: Array[String]): Unit = {
    //1.创建sql基本环境
    val conf: SparkConf = new SparkConf().setMaster("local").setAppName("sql tranform")
    val spark: SparkSession = SparkSession.builder().config(conf).getOrCreate()

    val df: DataFrame = spark.read.json("in/json/user.json")

    //自定义udf函数
    spark.udf.register("nameadd",(name:String)=>{"name:"+name})

    df.createTempView("user")

    val df2: DataFrame = spark.sql("select nameadd(name) from user")

    df2.show()
  }

}
