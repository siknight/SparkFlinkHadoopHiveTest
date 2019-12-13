package 汽车.原始数据生成


import java.util

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{Dataset, SparkSession}
import org.apache.spark.{SparkConf, SparkContext}



//数据格式（中间用空格隔开）： id  下单月份 性别  购买地点 车型 价格 颜色
object 汽车数据生成 {


  //scala每次产生一个新的集合
  def main(args: Array[String]): Unit = {


    dataCreate(1 to 110,"out/carsSources")


  }

  /**
    *
    * @param range  生成的条数，是个范围
    * @param outputPosition
    */
  def dataCreate(range:Range,outputPosition:String): Unit ={


    //写文件


    val javaList: util.ArrayList[String] = new util.ArrayList[String]()
    for (i<-range){
      //随机生成1-12个月份
      val month: Int = Util.monthRandom(12)
      //随机生成性别：男，女
      val sex: String = Util.listResultData(Initdata.sex).asInstanceOf[String]

      //随机生成购买地点 ("上海","天津","北京","杭州","深圳","广州")
      val adress: String = Util.listResultData(Initdata.buyAddress).asInstanceOf[String]

      //随机生成车型和价格
      val carAndPrice: (String, Int) = Util.listResultData(Initdata.cars).asInstanceOf[(String,Int)]
      val carStyle: String = carAndPrice._1
      val carPrice: Int = carAndPrice._2

      //随机生成颜色
      val color: String = Util.listResultData(Initdata.colors).asInstanceOf[(String)]
      //将随机生成的数据封装为元组
      //id 下单月份 性别  购买地点 车型 价格 颜色
      val perData =s"$i $month $sex $adress $carStyle $carPrice $color"

      //将perData添加到集合里
      javaList.add(perData)

    }

    import scala.collection.JavaConversions
    //将存储的javaList转为scalaList
    val scalaList: List[String] = JavaConversions.asScalaBuffer(javaList).toList

//    scalaList.foreach(println(_))

    val conf:SparkConf = new SparkConf().setMaster("local[*]").setAppName("sparkContext/wc")

    val spark: SparkSession = SparkSession.builder().config(conf).getOrCreate()

    val rdd: RDD[String] = spark.sparkContext.makeRDD(scalaList)

    import  spark.implicits._
    val cars: Dataset[CarOrder] = rdd.map(x => {
      val str: Array[String] = x.split(" ")
      str(0)

      CarOrder(str(0), str(1), str(2), str(3), str(4), str(5), str(6))
    }).toDS()
    cars.coalesce(1).write.mode("append").csv(outputPosition)



    println("over")


  }


}
//id 下单月份 性别  购买地点 车型 价格 颜色
case class  CarOrder(id:String,month:String,sex:String,adress:String,carStyle:String,price:String,color:String)