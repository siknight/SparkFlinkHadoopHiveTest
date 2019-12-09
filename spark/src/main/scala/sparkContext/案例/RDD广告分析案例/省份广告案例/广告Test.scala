package sparkContext.案例.RDD广告分析案例.省份广告案例

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable
//* 需求统计出每一个省份广告被点击次数的TOP3
object 广告Test {
  /**
    * 时间戳	省份 城市 用户 广告 (每一个时间戳表示一次)
    * 1516609143867 6 7 64 16
    * 1516609143869 9 4 75 18
    * 1516609143869 1 7 87 12
    *
    *
    * 需求统计出每一个省份广告被点击次数的TOP3
    *
    * 最终格式（按第几次数从大到小排序）
    * 城市（key）- （广告-点击次数）
    * @param args
    *
    *
    */
  def main(args: Array[String]): Unit = {
    //创建Rdd基本环境
    val conf: SparkConf = new SparkConf().setAppName("rddcreate").setMaster("local")
    val sc: SparkContext = new SparkContext(conf)

    //获取数据源
    val sources: RDD[String] = sc.textFile("in/aid/agent.log")

    //对每一行点击处理
    val linewc: RDD[((String, String), Int)] = sources.map(line => {
      val words: Array[String] = line.split(" ")
      ((words(1), words(4)), 1)
    })

    //对key相同的点击进行相加,并进行排序
    val keyadd: RDD[((String, String), Int)] = linewc.reduceByKey(_+_)

    //将省份提出来当做key
    val provinceKey: RDD[(String, (String, Int))] = keyadd.map {
      case ((pro, aid), count) => {
        (pro, (aid, count))
      }
    }

    //分组
    val provinceGro: RDD[(String, Iterable[(String, Int)])] = provinceKey.groupByKey()

   //result

    val result: RDD[(String, List[(String, Int)])] = provinceGro.mapValues(t => t.toList.sortWith {
        case ((x, y), (x2, y2)) => y > y2
      }.take(3)
    )

    result.foreach{
      case (x,y: List[(String, Int)])=>{
        println(x+"省:")
        y.foreach{
          case (aid,count)=>{
            println("aid="+aid+",count="+count)
          }
        }
      }
    }


    println("*****************第二种处理方式******************")
    val result02: RDD[(String, List[(String, Int)])] = provinceGro.map {
      case (x, y:Iterable[(String, Int)])=> {
        val listvalue: List[(String, Int)] = y.toList.sortWith {
          case ((k1, v1), (k2, v2)) => v1 > v2
        }.take(3)
        (x,listvalue)

      }

    }



//    println(result02.collect().mkString("\n"))
//
    result02.foreach{
      case (x,y: List[(String, Int)])=>{
        println(x+"省:")
        y.foreach{
          case (aid,count)=>{
            println("aid="+aid+",count="+count)
          }
        }
      }
    }
















  }

}
