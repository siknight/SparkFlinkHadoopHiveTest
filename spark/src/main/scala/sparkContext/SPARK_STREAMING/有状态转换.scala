package sparkContext.SPARK_STREAMING

import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  * dstream 和rdd一样操作就行
  */
object 有状态转换 {
  def main(args: Array[String]): Unit = {
    //这个只写local不会循环显示  local[*],说明streaming要跑多核
    val sc: SparkConf = new SparkConf().setMaster("local[*]").setAppName("stream test")
    val ssc: StreamingContext = new StreamingContext(sc,Seconds(2))
    //设置检查点
    ssc.checkpoint("in/checkpoint")
    //监听9999端口数据
    val rid: ReceiverInputDStream[String] = ssc.socketTextStream("hadoop102",9999)

    val ds: DStream[String] = rid.flatMap(_.split(" "))
    val wc: DStream[(String, Int)] = ds.map((_,1))

    val result: DStream[(String, Int)] = wc.updateStateByKey {
      case (seq, buffer) => {
        val sum = seq.sum + buffer.getOrElse(0)
        Option(sum)
      }
    }
    result.print()
    //输出
//    val reds: DStream[(String, Int)] = wc.reduceByKey(_+_)

    println("hahhahhahaha")

    //打印
//    reds.print()

    //启动SparkStreamingContext
    ssc.start()
    ssc.awaitTermination()


  }

}
