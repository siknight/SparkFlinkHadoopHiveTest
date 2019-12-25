package 流量统计

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  * 期望输入
  * 7 	13560436666	120.196.100.99		1116		 954			200
  * id	手机号码		网络ip			上行流量  下行流量     网络状态码
  * 期望输出
  * 13560436666 		1116		      954 			2070
  * 手机号码		    上行流量        下行流量		总流量

  */
object FlowSpark {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setAppName("flow test").setMaster("local[*]")
    val sc = new SparkContext(conf)
    val lines: RDD[String] = sc.textFile("in/phone_data.txt")
    //将有用的数据组合
    val words: RDD[(String, String, String)] = lines.map(x => {
      val split: Array[String] = x.split("\t")
      val phone = split(1) //月份
      val downFlow = split(split.length - 2) //下行流量
      val upFlow = split(split.length - 3)  //上行流量
      (phone, upFlow, downFlow)
    })
    //分组
    val gro: RDD[(String, Iterable[(String, String, String)])] = words.groupBy {
      case (phone, upflow, downflow) => phone
    }
    //result
    gro.map{
      case (phone,itr:Iterable[(String,String,String)])=>{
        var upflow:Int=0;
        var downflow:Int=0;
        var sumflow:Int=0;

        for (it<-itr){
          println("it2="+it._2)
          println("it3="+it._3)
          upflow=upflow+it._2.toInt  //用toInt
          downflow=downflow+it._3.toInt //用toInt
        }
        sumflow=upflow+downflow;
        s"$phone  $upflow $downflow $sumflow"
      }
    }.coalesce(1).saveAsTextFile("out/spark/flowbean2")




  }

}
