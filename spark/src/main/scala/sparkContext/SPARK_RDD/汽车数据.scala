package sparkContext.SPARK_RDD

import java.util
import java.util.Random

import org.apache.spark.{SparkConf, SparkContext}

/**
  * 模拟生成1到12个月汽车模拟数据
  *
  * 数据格式（中间用空格隔开）： id  下单月份 性别  购买地点 车型和价格 颜色
  */
object 汽车数据 {

  def main(args: Array[String]): Unit = {
//    //创建Rdd基本环境
//    val conf: SparkConf = new SparkConf().setAppName("rddcreate").setMaster("local")
//    val sc: SparkContext = new SparkContext(conf)


      //TODO 数据初始化


      //下单性别
      val sex: List[String] = List("男","女")

      //购买地点
      val buyAddress: List[String] = List("上海","天津","北京","杭州","深圳","广州")

      //随机生成，(车型，价格区间)，因为车型和价格关联性很大，所以用元组表示
//      val cars: List[String] = List("奥迪Q2L","宝马X1","奥迪A6L","宝马3系","奔驰EQC","大众朗逸")
       val cars: List[(String, Int)] = List(("奥迪Q2L",65),("宝马X1",95),("奥迪A6L",87),("宝马3系",96),("奔驰EQC",150),("大众朗逸",23))

      //颜色
      val colors: List[String] = List("红色","黑色","蓝色","灰色")

      //TODO 将初始化数据随机生成


     for(i<- 1 to 20){   //i为id号

       //每一行为一个元组  （id,下单月份,性别,购买地点,车型和价格,颜色）


       //随机数 ，下单时间，生成1-12个月数据
       val random: Random = new java.util.Random()
       val monthSelect: Int = (Math.random()*12+1).toInt

       //随机取性别，生成0和1两个数
       val sexint: Int = (Math.random()*2).toInt
       val sexSelect: String = sex(sexint)
       (i,monthSelect,sexSelect)

     }





    }



}
