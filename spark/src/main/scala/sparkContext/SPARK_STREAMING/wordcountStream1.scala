package sparkContext.SPARK_STREAMING

import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  * dstream 和rdd一样操作就行
  */
object wordcountStream1 {
  def main(args: Array[String]): Unit = {
    //这个只写local不会循环显示  local[*],说明streaming要跑多核
    val sc: SparkConf = new SparkConf().setMaster("local[*]").setAppName("stream test")
    val ssc: StreamingContext = new StreamingContext(sc,Seconds(2))

    //监听7777端口数据
    val rid: ReceiverInputDStream[String] = ssc.socketTextStream("hadoop102",9999)
    rid.print()
    val ds: DStream[String] = rid.flatMap(_.split(" "))
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
