package 汽车.原始数据生成


import java.util

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{Dataset, SparkSession}
import org.apache.spark.{SparkConf, SparkContext}



//数据格式（中间用空格隔开）： id  下单月份 性别  购买地点 车型 价格 颜色
object 汽车数据生成 {


  //scala每次产生一个新的集合
  def main(args: Array[String]): Unit = {


    dataCreate(1 to 500000,"out/carsSources")


  }

  /**
    *
    * @param range  生成的条数，是个范围
    * @param outputPosition
    */
  def dataCreate(range:Range,outputPosition:String): Unit ={


    //写文件


    val javaList: util.ArrayList[String] = new util.ArrayList[String]()
    //随机选择一个随月份递增的车
    val dizengCar: String = Util.selectOneCar
    println("dizengCar="+dizengCar)
    //随机选择一个随月份递减的车

    var dijianCar: String = Util.selectOneCar
    println("dijianCar="+dijianCar)
    //如果相同，再次循环，知道选出不同的
    while(dijianCar.equals(dizengCar)){
      dijianCar = Util.selectOneCar
      println("dijianCar while="+dijianCar)
    }
    //range 大小
    val rangesize: Int = range.toList.size
    for (i<-range){
      //随机生成1-12个月份
      var month: Int = Util.monthRandom(12,1)





      //随机生成性别：男，女
      val sex: String = Util.listResultData(Initdata.sex).asInstanceOf[String]

      //随机生成购买地点 ("上海","天津","北京","杭州","深圳","广州")
      val adress: String = Util.listResultData(Initdata.buyAddress).asInstanceOf[String]

      //随机生成车型和价格
      val carAndPrice: (String, Int) = Util.listResultData(Initdata.cars).asInstanceOf[(String,Int)]
      val carStyle: String = carAndPrice._1
      val carPrice: Int = carAndPrice._2

      //对车型源数据稍加修饰
      //递增
      if(carStyle.equals(dizengCar)){

        month match {
          case 1 =>{
            if(i > rangesize*89.5/100){  //取消1月份汽车生成
              month=Util.monthRandom(12,2)
              println("dizeng取消了1月生成")
            }
          }
          case  2=> {
            if(i > rangesize*89.8/100){  //取消2月份汽车生成
              month=Util.monthRandom(12,3)
              println("dizeng取消了2月生成")
            }
          }
          case 3 =>{
            if(i > rangesize*90/100){  //取消3月份汽车生成
              month=Util.monthRandom(12,4)
              println("dizeng取消了3月生成")
            }
          }
          case  4=> {
            if(i > rangesize*90.5/100){  //取消4月份汽车生成
              month=Util.monthRandom(12,5)
              println("dizeng取消了4月生成")
            }
          }
          case 5 =>{
            if(i > rangesize*91/100){  //取消5月份汽车生成
              month=Util.monthRandom(12,6)
              println("dizeng取消了5月生成")
            }
          }
          case  6=> {
            if(i > rangesize*91.8/100){  //取消6月份汽车生成
              month=Util.monthRandom(12,7)
              println("dizeng取消了6月生成")
            }
          }
          case 7 =>{
            if(i > rangesize*92.9/100){  //取消7月份汽车生成
              month=Util.monthRandom(12,8)
              println("dizeng取消了7月生成")
            }
          }
          case  8=> {
            if(i > rangesize*93.5/100){  //取消8月份汽车生成
              month=Util.monthRandom(12,9)
              println("dizeng取消了8月生成")
            }
          }
          case 9 =>{
            if(i > rangesize*94.5/100){  //取消9月份汽车生成
              month=Util.monthRandom(12,10)
              println("dizeng取消了9月生成")
            }
          }
          case  10=> {
            if(i > rangesize*96/100){  //取消10月份汽车生成
              month = Util.monthRandom(12,11)
              println("dizeng取消了10月生成")
            }
          }
          case  11=> {
            if(i > rangesize*98.3/100){  //取消11月份汽车生成
              month = Util.monthRandom(12,12)
              println("dizeng取消了11月生成")
            }
          }
          case _=>println("dizeng本月是12月")
        }
      }


      //递减
      if(carStyle.equals(dijianCar)){

        month match {

          case  2=> {
            if(i > rangesize*98.5/100){  //取消2月份汽车生成
              month=Util.monthRandom(1,1)
              println("dijian取消了2月生成")
            }
          }
          case 3 =>{
            if(i > rangesize*97/100){  //取消3月份汽车生成
              month=Util.monthRandom(2,1)
              println("dijian取消了3月生成")
            }
          }
          case  4=> {
            if(i > rangesize*96/100){  //取消4月份汽车生成
              month=Util.monthRandom(3,1)
              println("dijian取消了4月生成")
            }
          }
          case 5 =>{
            if(i > rangesize*95.3/100){  //取消5月份汽车生成
              month=Util.monthRandom(4,1)
              println("dijian取消了5月生成")
            }
          }
          case  6=> {
            if(i > rangesize*95.2/100){  //取消6月份汽车生成
              month=Util.monthRandom(5,1)
              println("dijian取消了6月生成")
            }
          }
          case 7 =>{
            if(i > rangesize*94.6/100){  //取消7月份汽车生成
              month=Util.monthRandom(6,1)
              println("dijian取消了7月生成")
            }
          }
          case  8=> {
            if(i > rangesize*94/100){  //取消8月份汽车生成
              month=Util.monthRandom(7,1)
              println("dijian取消了8月生成")
            }
          }
          case 9 =>{
            if(i > rangesize*93.2/100){  //取消9月份汽车生成
              month=Util.monthRandom(8,1)
              println("dijian取消了9月生成")
            }
          }
          case  10=> {
            if(i > rangesize*92/100){  //取消10月份汽车生成
              month = Util.monthRandom(9,1)
              println("dijian取消了10月生成")
            }
          }
          case  11=> {
            if(i > rangesize*91.8/100){  //取消11月份汽车生成
              month = Util.monthRandom(10,1)
              println("dijian取消了11月生成")
            }
          }
          case  12=> {
            if(i > rangesize*90.9/100){  //取消11月份汽车生成
              month = Util.monthRandom(11,1)
              println("dijian取消了11月生成")
            }
          }
          case 1=>println("dijian本月是1月")
        }
      }




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


    val conf:SparkConf = new SparkConf().setMaster("local[*]").setAppName("sparkContext/wc")

    val spark: SparkSession = SparkSession.builder().config(conf).getOrCreate()

    val rdd: RDD[String] = spark.sparkContext.makeRDD(scalaList)



   //输出
    import  spark.implicits._
    val cars: Dataset[CarOrder] = rdd.map(x => {
      val str: Array[String] = x.split(" ")
      CarOrder(str(0), str(1), str(2), str(3), str(4), str(5), str(6))
    }).toDS()
    cars.coalesce(1).write.mode("append").csv(outputPosition)



    println("over")


  }




}
//id 下单月份 性别  购买地点 车型 价格 颜色
case class  CarOrder(id:String,month:String,sex:String,adress:String,carStyle:String,price:String,color:String)