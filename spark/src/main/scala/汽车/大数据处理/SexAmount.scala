package 汽车.大数据处理

import java.util.Properties

import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, Dataset, SparkSession}

/**
*
* 原始数据： id ，下单月份，性别 ，购买地点，车型 ，价格 ，颜色
*
*sexYearAmount
* //年男女数量（男女比率柱形图，男女饼装图）
* id sex totalYearAmount（Long）
* 1  男    500
* 2  女     200
*
*/
object SexAmount {



  def perSexAmount(sparkMaster:String,readFilePosition:String,mysqlUsername:String,
             MysqlPassword:String,sparkSaveMode:String,JDBCUrl:String,tableName:String): Unit ={
    val conf:SparkConf = new SparkConf().setMaster(sparkMaster).setAppName("sex wc")
    val spark: SparkSession = SparkSession.builder().config(conf).getOrCreate()

    val lines: RDD[String] = spark.sparkContext.textFile(readFilePosition)
    //处理
    //(性别，数量)
    val sexResult: RDD[(String, Int)] = lines.map(x => {
      val lineArray: Array[String] = x.split(",")
      val lineSex: String = lineArray(2)
      (lineSex, 1)
    }).groupByKey().map(t => (t._1, t._2.size))

    //导入到数据库
    import  spark.implicits._
    val df: DataFrame = sexResult.toDF("sex","totalYearAmount")
    val connectionProperties  = new Properties()
    connectionProperties.setProperty("user",mysqlUsername)
    connectionProperties.setProperty("password",MysqlPassword)
    df.write.mode(sparkSaveMode).jdbc(JDBCUrl,
      tableName, connectionProperties)

    println("sex表生成over")
    spark.stop()
  }


}
