package sparkContext.SPARK_STREAMING

import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, InputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}

object FileStream {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setAppName("spark conf").setMaster("local[*]")
    val ssc: StreamingContext = new StreamingContext(conf,Seconds(3))

    val fileds: DStream[String] = ssc.textFileStream("in/two")

    val dsflatmap: DStream[String] = fileds.flatMap(_.split(" "))

    val dsmap: DStream[(String, Int)] = dsflatmap.map((_,1))

    println(dsmap)
    println("***********")
    dsmap.print()

    ssc.start()
    ssc.awaitTermination()


  }

}
