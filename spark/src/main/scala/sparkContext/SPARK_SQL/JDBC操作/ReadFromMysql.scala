package sparkContext.SPARK_SQL.JDBC操作

import java.util.Properties

import org.apache.spark.SparkConf
import org.apache.spark.sql.{DataFrame, SparkSession}

object ReadFromMysql {
  def main(args: Array[String]): Unit = {
    //创建配置信息
    val conf: SparkConf =
      new SparkConf().setAppName("spark sql").setMaster("local")
    //创建sparksession
    //创建SparkSQL的环境对象
    val spark: SparkSession = SparkSession.builder().config(conf).getOrCreate()
 //方式一
      println("******方式一******")
    //从mysql数据库加载数据，用format+option这种写法更简单
    val jdbcDF: DataFrame = spark.read.format("jdbc")
      .option("url", "jdbc:mysql://localhost:3306/rdd")
      .option("dbtable", "user").option("user", "root")
      .option("password", "jiang").load()
    jdbcDF.show()

  //方式2
    println("******方式二******")
    val connectionProperties  = new Properties()
    connectionProperties.setProperty("user","root")
    connectionProperties.setProperty("password","jiang")

    val dfJdbc2: DataFrame = spark.read.jdbc("jdbc:mysql://localhost:3306/rdd",
      "user", connectionProperties)
    dfJdbc2.show()


  }

}
