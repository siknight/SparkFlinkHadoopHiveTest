package 汽车

import java.util.Random

/**
  * 初始数据：
      * 模拟生成1到12个月汽车模拟数据
      *
      * 数据格式（中间用空格隔开）： id  下单月份 性别  购买地点 车型和价格 颜色
  */


object Initdata {


  //下单性别
  val sex: List[String] = List("男","女")

  //购买地点
  val buyAddress: List[String] = List("上海","天津","北京","杭州","深圳","广州")

  //随机生成，(车型，价格区间)，因为车型和价格关联性很大，所以用元组表示
  //      val cars: List[String] = List("奥迪Q2L","宝马X1","奥迪A6L","宝马3系","奔驰EQC","大众朗逸")
  val cars: List[(String, Int)] = List(("奥迪Q2L",65),("宝马X1",95),("奥迪A6L",87),("宝马3系",96),("奔驰EQC",150),("大众朗逸",23))

  //颜色
  val colors: List[String] = List("红色","黑色","蓝色","灰色")


}
