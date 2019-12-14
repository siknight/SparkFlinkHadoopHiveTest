package 汽车.大数据处理

import java.util.Properties

import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, SparkSession}

/**
*
* 原始数据： id ，下单月份，性别 ，购买地点，车型 ，价格 ，颜色
*
  *adressYearAmount
  * //地区年销量比率（地区比率柱形图，地区饼装图）
  * id adress totalYearamount
  * 1  天津    500
  * 2  北京    1000
*
*/
object AdressAmount {



  def perAdressAmount(sparkMaster:String,readFilePosition:String,mysqlUsername:String,
             MysqlPassword:String,sparkSaveMode:String,JDBCUrl:String,tableName:String): Unit ={
    val conf:SparkConf = new SparkConf().setMaster(sparkMaster).setAppName("sex wc")
    val spark: SparkSession = SparkSession.builder().config(conf).getOrCreate()

    val lines: RDD[String] = spark.sparkContext.textFile(readFilePosition)
    //处理
    //(地点，数量)
    val sexResult: RDD[(String, Int)] = lines.map(x => {
      val lineArray: Array[String] = x.split(",")
      val lineAdress: String = lineArray(3)
      (lineAdress, 1)
    }).groupByKey().map(t => (t._1, t._2.size))

    //导入到数据库
    import spark.implicits._
    val df: DataFrame = sexResult.toDF("adress","totalYearamount")
    val connectionProperties  = new Properties()
    connectionProperties.setProperty("user",mysqlUsername)
    connectionProperties.setProperty("password",MysqlPassword)
    df.write.mode(sparkSaveMode).jdbc(JDBCUrl,
      tableName, connectionProperties)

    println("adress表生成over")
    spark.stop()
  }


}
