package sparkContext.SPARK_SQL

import org.apache.spark.SparkConf
import org.apache.spark.sql.{DataFrame, SparkSession}

object DataFrame创建 {

  def main(args: Array[String]): Unit = {
    //class SparkSession private ,可以知道
    //SparkSession是私有的，不能构造对象
//    new SparkSession()
    //创建配置信息
    val conf: SparkConf =
       new SparkConf().setAppName("spark sql").setMaster("local")
    //创建sparksession
    //创建SparkSQL的环境对象
    val spark: SparkSession = SparkSession.builder().config(conf).getOrCreate()

   //读取文件,构建DataFrame
    val frame: DataFrame = spark.read.json("in/json/user.json")

    //展示数据,不用println输出
    frame.show()


    //将DataFrame转换成一张表，创建临时表
    frame.createTempView("user")
    println("**********")
    //采用sql的语法访问数据
    val sqldf: DataFrame = spark.sql("select name from user")

    sqldf.show()

    //释放资源
    spark.stop()









  }

}
