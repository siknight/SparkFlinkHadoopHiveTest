package sparkContext.SPARK_STREAMING.自定义数据源

import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}

object ReceiverStream {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf()
        .setMaster("local[*]").setAppName("context test")
    val ssc = new StreamingContext(conf,Seconds(3))

    val receiver: ReceiverInputDStream[String] = ssc.receiverStream(new CustomerReceiver("hadoop102",9999))

    val strflat: DStream[String] = receiver.flatMap(_.split(" "))

    val strmap: DStream[(String, Int)] = strflat.map((_,1))

    strmap.print()

    ssc.start()
    ssc.awaitTermination()






  }

}
