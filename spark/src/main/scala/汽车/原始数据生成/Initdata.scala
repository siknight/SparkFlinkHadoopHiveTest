package 汽车.原始数据生成

/**
  * 初始数据：
      * 模拟生成1到12个月汽车模拟数据
      *
      * 数据格式（中间用空格隔开）： id  下单月份 性别  购买地点 车型和价格 颜色
  */


object Initdata {
  //月数
  val month1to12 = List(1,2,3,4,5,6,7,8,9,10,11,12)
  val month2to12 = List(2,3,4,5,6,7,8,9,10,11,12)
  val month3to12 = List(3,4,5,6,7,8,9,10,11,12)
  val month4to12 = List(4,5,6,7,8,9,10,11,12)
  val month5to12 = List(5,6,7,8,9,10,11,12)
  val month6to12 = List(6,7,8,9,10,11,12)
  val month7to12 = List(7,8,9,10,11,12)
  val month8to12 = List(8,9,10,11,12)
  val month9to12 = List(9,10,11,12)
  val month10to12 = List(10,11,12)
  val month11to12 = List(11,12)
  val month12 = List(12)
  //下单性别
  val sex: List[String] = List("男","女")
  val sex_woman: List[String] = List("男")

  //购买地点
  val buyAddress: List[String] = List("上海","天津","北京","杭州","深圳","广州")
  val buyAddress_tianjin: List[String] = List("上海","北京","杭州","深圳","广州")
  val buyAddress_hangzhou: List[String] = List("上海","北京","深圳","广州")
  val buyAddress_shenZhen: List[String] = List("上海","北京")


  //随机生成，(车型，价格区间)，因为车型和价格关联性很大，所以用元组表示
  //      val cars: List[String] = List("奥迪Q2L","宝马X1","奥迪A6L","宝马3系","奔驰EQC","大众朗逸")
  val cars: List[(String, Int)] = List(("奥迪Q2L",65),("宝马X1",95),("奥迪A6L",87),("宝马3系",96),("奔驰EQC",150),("大众朗逸",23))

  //颜色
  val colors: List[String] = List("红色","黑色","白色","灰色","蓝色")
  val colors_red: List[String] = List("黑色","白色","灰色","蓝色")
  val colors_blue: List[String] = List("黑色","白色","灰色")
  val colors_white: List[String] = List("黑色","灰色")


}
