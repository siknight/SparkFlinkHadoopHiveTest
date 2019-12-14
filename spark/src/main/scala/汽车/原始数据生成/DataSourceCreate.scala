package 汽车.原始数据生成

import java.util

import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{Dataset, SparkSession}


object DataSourceCreate {
  /**
    *
    * @param range  生成的条数，是个范围
    * @param outputPosition
    */
  def dataCreate(range:Range,outputPosition:String): Unit ={


    //写文件


    val javaList: util.ArrayList[String] = new util.ArrayList[String]()
    //随机选择一个随月份递增的车
//    val dizengCar: String = Util.selectOneCar
//    println("dizengCar="+dizengCar)
    //随机选择一个随月份递减的车
     var dizengCar="宝马3系"
     var dizengCar2="奥迪Q2L"
     var dijianCar: String ="奔驰EQC"
      var dijianCar2: String ="宝马X1"
//    var dijianCar: String = Util.selectOneCar
//    println("dijianCar="+dijianCar)
//    //如果相同，再次循环，知道选出不同的
//    while(dijianCar.equals(dizengCar)){
//      dijianCar = Util.selectOneCar
//      println("dijianCar while="+dijianCar)
//    }
    //range 大小
    val rangesize: Int = range.toList.size
    for (i<-range){
      //随机生成1-12个月份
      var month: Int = Util.monthRandom(12,1)

      //随机生成性别：男，女
      var sex: String = Util.listResultData(Initdata.sex).asInstanceOf[String]

      //随机生成购买地点 ("上海","天津","北京","杭州","深圳","广州")
      var adress: String = Util.listResultData(Initdata.buyAddress).asInstanceOf[String]

      //随机生成车型和价格
      val carAndPrice: (String, Int) = Util.listResultData(Initdata.cars).asInstanceOf[(String,Int)]
      val carStyle: String = carAndPrice._1
      val carPrice: Int = carAndPrice._2

      //对车型源数据稍加修饰
      //递增  策略：先判断目前随机生成的车是否与递增的车相同，如果相同，当生成数量大于某一设置的量时，就只生成2-12月
      //再大于另一个生成量时，就只生成3-12月，依次类推，到最后只生成12月的该车 因而实现递增
      if(carStyle.equals(dizengCar)||carStyle.equals(dizengCar2)){

        month match {
          case 1 =>{
            if(i > rangesize*70/100){  //取消1月份汽车生成

              month= Util.listResultData(Initdata.month2to12).asInstanceOf[Int]
//              println("dizeng取消了1月生成")
            }
          }
          case  2=> {
            if(i > rangesize*73.8/100){  //取消2月份汽车生成
              month= Util.listResultData(Initdata.month3to12).asInstanceOf[Int]
//              println("dizeng取消了2月生成")
            }
          }
          case 3 =>{
            if(i > rangesize*77.2/100){  //取消3月份汽车生成
              month= Util.listResultData(Initdata.month4to12).asInstanceOf[Int]
//              println("dizeng取消了3月生成")
            }
          }
          case  4=> {
            if(i > rangesize*80.5/100){  //取消4月份汽车生成
              month= Util.listResultData(Initdata.month5to12).asInstanceOf[Int]
//              println("dizeng取消了4月生成")
            }
          }
          case 5 =>{
            if(i > rangesize*83.9/100){  //取消5月份汽车生成
              month= Util.listResultData(Initdata.month6to12).asInstanceOf[Int]
//              println("dizeng取消了5月生成")
            }
          }
          case  6=> {
            if(i > rangesize*86.44/100){  //取消6月份汽车生成
              month= Util.listResultData(Initdata.month7to12).asInstanceOf[Int]
//              println("dizeng取消了6月生成")
            }
          }
          case 7 =>{
            if(i > rangesize*89.22/100){  //取消7月份汽车生成
              month= Util.listResultData(Initdata.month8to12).asInstanceOf[Int]
//              println("dizeng取消了7月生成")
            }
          }
          case  8=> {
            if(i > rangesize*89.99/100){  //取消8月份汽车生成
              month= Util.listResultData(Initdata.month9to12).asInstanceOf[Int]
//              println("dizeng取消了8月生成")
            }
          }
          case 9 =>{
            if(i > rangesize*90.2/100){  //取消9月份汽车生成
              month= Util.listResultData(Initdata.month10to12).asInstanceOf[Int]
//              println("dizeng取消了9月生成")
            }
          }
          case  10=> {
            if(i > rangesize*92.5/100){  //取消10月份汽车生成
              month= Util.listResultData(Initdata.month11to12).asInstanceOf[Int]
//              println("dizeng取消了10月生成")
            }
          }

          case  11=> {
            if(i > rangesize*93.3/100){  //取消11月份汽车生成
              month= Util.listResultData(Initdata.month12).asInstanceOf[Int]
//              println("dizeng取消了11月生成")
            }
          }
          case _=>println("数据正在生成，请等待。。。")
        }
      }


      //递减
      if(carStyle.equals(dijianCar)||carStyle.equals(dijianCar2)){

        month match {

          case  2=> {
            if(i > rangesize*94.3/100){  //取消2月份汽车生成
              month=Util.monthRandom(1,1)
//              println("dijian取消了2月生成")
            }
          }
          case 3 =>{
            if(i > rangesize*93.2/100){  //取消3月份汽车生成
              month=Util.monthRandom(2,1)
//              println("dijian取消了3月生成")
            }
          }
          case  4=> {
            if(i > rangesize*92.8/100){  //取消4月份汽车生成
              month=Util.monthRandom(3,1)
//              println("dijian取消了4月生成")
            }
          }
          case 5 =>{
            if(i > rangesize*90.11/100){  //取消5月份汽车生成
              month=Util.monthRandom(4,1)
//              println("dijian取消了5月生成")
            }
          }
          case  6=> {
            if(i > rangesize*89.55/100){  //取消6月份汽车生成
              month=Util.monthRandom(5,1)
//              println("dijian取消了6月生成")
            }
          }
          case 7 =>{
            if(i > rangesize*85.9/100){  //取消7月份汽车生成
              month=Util.monthRandom(6,1)
//              println("dijian取消了7月生成")
            }
          }
          case  8=> {
            if(i > rangesize*84.9/100){  //取消8月份汽车生成
              month=Util.monthRandom(7,1)
//              println("dijian取消了8月生成")
            }
          }
          case 9 =>{
            if(i > rangesize*82/100){  //取消9月份汽车生成
              month=Util.monthRandom(8,1)
//              println("dijian取消了9月生成")
            }
          }
          case  10=> {
            if(i > rangesize*80/100){  //取消10月份汽车生成
              month = Util.monthRandom(9,1)
//              println("dijian取消了10月生成")
            }
          }
          case  11=> {
            if(i > rangesize*75.33/100){  //取消11月份汽车生成
              month = Util.monthRandom(10,1)
//              println("dijian取消了11月生成")
            }
          }
          case  12=> {
            if(i > rangesize*72.27/100){  //取消11月份汽车生成
              month = Util.monthRandom(11,1)
//              println("dijian取消了11月生成")
            }
          }
          case 1=>println("数据正在生成，请等待。。。")
        }
      }




      //随机生成颜色,规则黑色最多，红色最少
      var color: String = Util.listResultData(Initdata.colors).asInstanceOf[(String)]

      //对数据修饰
      if(i>rangesize*97.6/100){
        color=Util.listResultData(Initdata.colors_white).asInstanceOf[(String)]
        adress=Util.listResultData(Initdata.buyAddress_shenZhen).asInstanceOf[(String)]
      }else if (i>rangesize*79.2/100){

        color=Util.listResultData(Initdata.colors_blue).asInstanceOf[(String)]
        adress=Util.listResultData(Initdata.buyAddress_hangzhou).asInstanceOf[(String)]
      }else if (i>rangesize*68/100){
        sex =Util.listResultData(Initdata.sex_woman).asInstanceOf[(String)]
        color=Util.listResultData(Initdata.colors_red).asInstanceOf[(String)]
        adress=Util.listResultData(Initdata.buyAddress_tianjin).asInstanceOf[(String)]
      }



      //将随机生成的数据封装为元组
      //id 下单月份 性别  购买地点 车型 价格 颜色
      val perData =s"$i $month $sex $adress $carStyle $carPrice $color"

      //将perData添加到集合里
      javaList.add(perData)

    }

    import scala.collection.JavaConversions
    //将存储的javaList转为scalaList
    val scalaList: List[String] = JavaConversions.asScalaBuffer(javaList).toList


    val conf:SparkConf = new SparkConf().setMaster("local[*]").setAppName("sparkContext/wc")

    val spark: SparkSession = SparkSession.builder().config(conf).getOrCreate()

    val rdd: RDD[String] = spark.sparkContext.makeRDD(scalaList)



    //输出
    import spark.implicits._
    val cars: Dataset[CarOrder] = rdd.map(x => {
      val str: Array[String] = x.split(" ")
      CarOrder(str(0), str(1), str(2), str(3), str(4), str(5), str(6))
    }).toDS()
    cars.coalesce(1).write.mode("append").csv(outputPosition)



    println("sourceData  create over")


  }

}
//id 下单月份 性别  购买地点 车型 价格 颜色
case class  CarOrder(id:String,month:String,sex:String,adress:String,carStyle:String,price:String,color:String)