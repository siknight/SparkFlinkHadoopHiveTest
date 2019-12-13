package sparkContext.SPARK_STREAMING.窗口函数

import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}

object Spark窗口函数 {
  def main(args: Array[String]): Unit = {
    //这个只写local不会循环显示  local[*],说明streaming要跑多核
    val sc: SparkConf = new SparkConf().setMaster("local[*]").setAppName("stream test")
    val ssc: StreamingContext = new StreamingContext(sc,Seconds(2))
    //监听9999端口数据
    val rid: ReceiverInputDStream[String] = ssc.socketTextStream("hadoop102",9999)
    //窗口函数，第一个参数是几个窗口，第二个参数是步长
    val winds: DStream[String] = rid.window(Seconds(4),Seconds(2))

    val ds: DStream[String] = winds.flatMap(_.split(" "))
    val wc: DStream[(String, Int)] = ds.map((_,1))
    //输出
    val reds: DStream[(String, Int)] = wc.reduceByKey(_+_)
    println("hahhahhahaha")
    //打印
    reds.print()

    //启动SparkStreamingContext
    ssc.start()
    ssc.awaitTermination()
  }

}
