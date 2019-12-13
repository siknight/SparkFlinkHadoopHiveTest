package 汽车



import org.apache.spark.{SparkConf, SparkContext}

//数据格式（中间用空格隔开）： id  下单月份 性别  购买地点 车型 价格 颜色
object 汽车数据生成 {
  //scala每次产生一个新的集合
  def main(args: Array[String]): Unit = {
   //创建spark RDD
    val conf:SparkConf = new SparkConf().setMaster("local[*]").setAppName("sparkContext/wc")
    //创建spark上下文对象
    val sc = new SparkContext(conf)


    for (i<-1 to 20){
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
      //id  下单月份 性别  购买地点 车型 价格 颜色
       val perData: (Int, Int, String, String, String, Int, String) = (i,month,sex,adress,carStyle,carPrice,color)
      //将数据添加到集合
      println(perData)
      


    }

  }

}
