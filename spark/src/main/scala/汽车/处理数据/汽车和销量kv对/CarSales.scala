package 汽车.处理数据.汽车和销量kv对


import java.util.Properties

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.{SparkConf, SparkContext}

/**
  * 原始数据： id ，下单月份，性别 ，购买地点，车型 ，价格 ，颜色
  *
  * 处理后格式：List((奥迪A6，List（（1月，销量），（2，月））),(宝马，List（（1月，销量），（2，月））))
  * List((奥迪A6，List(12,13,36,),())
  *
  * 这种：
  * mysql  ： List((奥迪A6，“12,13,36”）,())
  *
  * carsSale（销量预测量，每种车年销量饼装图，每种车年销量柱形图）
  * * id   carName  perMonthAmonunt
  * * 1     奥迪A6   “12,13,36”
  * * 2     宝马Q5   “12,13,36”
  *
  *sexYearAmount
  * //年男女数量（男女比率柱形图，男女饼装图）
  * id sex totalYearAmount（Long）
  * 1  男    500
  * 2  女
  *
  * //地区年销量比率（地区比率柱形图，地区饼装图）
  * id adress totalYearamount
  * 1  天津    500
  * 2  北京    1000
  *
  * //汽车颜色年销量比率（地区比率柱形图，地区饼装图）
  * id  carColor amount
  *1     黑色    10000
  * 2   红色     5000
  *
  *
  */
object CarSales {

  def main(args: Array[String]): Unit = {

      carsDataToMysql("local[*]","in/carData/source",
        "root","jiang","overwrite",
        "jdbc:mysql://localhost:3306/bigdatacar","carSale")
  }

  /**
    *
    * @param sparkMaster  local[*] yanr standand
    * @param readFilePosition   读取的文件位置  "in/carData/source/carSource.csv"
    * @param mysqlUsername   mysql用户名  root
    * @param MysqlPassword   mysql 密码  jiang
    * @param sparkSaveMode   spark保存模式  append overwrite
    * @param JDBCUrl     jdbc url   "jdbc:mysql://localhost:3306/bigdatacar"
    * @param tableName  表名   "carsSale"
    */

  def carsDataToMysql(sparkMaster:String,readFilePosition:String,mysqlUsername:String,
                      MysqlPassword:String,sparkSaveMode:String,JDBCUrl:String,tableName:String

                      )={
    /**
      * * 原始数据：
      * *
      * * 处理后格式：List((奥迪A6，List（（1月，销量），（2，月））),(宝马，List（（1月，销量），（2，月））))
      * * List((奥迪A6，List(12,13,36,),())
      * mysql  List((奥迪A6，“12,13,36”）,())
      * *
      */
    val conf:SparkConf = new SparkConf().setMaster(sparkMaster).setAppName("sparkContext/wc")

    val spark: SparkSession = SparkSession.builder().config(conf).getOrCreate()
    //创建spark上下文对象
    val sc = spark.sparkContext
    //读取文件
    val lines: RDD[String] =
      sc.textFile(readFilePosition)

    //原始数据：id ，下单月份，性别 ，购买地点，车名 ，价格 ，颜色
    //变形
    val mapline: RDD[(String, String, Int)] = lines.map(line => {
      val str: Array[String] = line.split(",")
      val id = str(0) //id
      val month = str(1) //购买月份
      val sex = str(2) //sex
      val adress = str(3) //购买地点
      val carname = str(4) //车名
      val price = str(5) //价格
      val color = str(6) //颜色
      //(奥迪A6，List（（1月，销量），（2，月）...）)
      (carname, month, 1)
    })
    //分组 (车名,Iterable[(车名, 月份, 销量1)])
    val groupLine: RDD[(String, Iterable[(String, String, Int)])]= mapline.groupBy(t=>t._1)
    //变形
    //RDD[(奥迪A6，List[(月份，销量)]]
    val result01: RDD[(String, List[(String, Int)])] = groupLine.map { //0
      //(车名,Iterable[(车名, 月份, 销量1)])
      case (key: String, value: Iterable[(String, String, Int)]) => { //1
        //List[(车名, 月份, 销量)]
        val valueList: List[(String, String, Int)] = value.toList
        //List[( 月份, 销量)]
        val valueListmap: List[(String, Int)] =
          valueList.map(t => (t._2, t._3))
        //Map[月份,List[( 月份, 销量1)]]
        val valueListGro: Map[String, List[(String, Int)]] =
          valueListmap.groupBy(t => t._1)
        val perMonthAmount: List[(String, Int)] = valueListGro.map { //2
          case
            (monthkey, monthCount: List[(String, Int)]) => { //3
            //(月份，每月销量)
            val perMonthCount: (String, Int) = (monthkey, monthCount.size)
            println("perMonthCount=" + perMonthCount)
            perMonthCount
          } //3
        }.toList //2
        (key, perMonthAmount)
      } //1

    } //0

    //再次处理  RDD[(车名，一年每月的销量组成字符串)]
    // List((奥迪A6，“2:12,3:13,36”）)  List((奥迪A6，“1月:销量,3月:销量,5月;销量”）)
    val resultRDD: RDD[(String, String)] = result01.map { //0
      //(车名，List[(月，数量)])
      case (carKey: String, everyMonthAmount: List[(String, Int)]) => { //1

        val strBuilder: StringBuilder = new StringBuilder()
        //k:月 v:k月对应的销量
        for((k, v) <- everyMonthAmount) {
          println("月=" + k + ",v=" + v) // 用于比对，是否排序正确
          strBuilder.append(s"$k:$v"+",")
        }
          //每个车一年的销量组成一个字符串
        val strResult: String = strBuilder.toString()
        (carKey, strResult)

      } //1
    }

    //改变结构
    var id=0
    val listId: List[(Int, String, String)] = resultRDD.collect().toList.map(t => {
      id = id + 1;
      (id, t._1, t._2)
    })
//    println("*************************************")
//    println(listId.mkString(","))


      val resultIdRDD: RDD[(Int, String, String)] = sc.makeRDD(listId)

    //将处理完数据导入到mysql数据库
    import spark.implicits._
    val df: DataFrame = resultIdRDD.toDF("id","carName","perMonthAmonunt")
    val connectionProperties  = new Properties()
    connectionProperties.setProperty("user",mysqlUsername)
    connectionProperties.setProperty("password",MysqlPassword)
    df.write.mode(sparkSaveMode).jdbc(JDBCUrl,
      tableName, connectionProperties)

    println("over")
  }
}

//case class CarsSale(id:Int,carName:String,perMonthAmonunt:String)

