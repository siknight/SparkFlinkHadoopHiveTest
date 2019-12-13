package sparkContext.SPARK_STREAMING.kafka数据源

import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}

object KafkaTest {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setAppName("spark conf").setMaster("local[*]")
    val ssc: StreamingContext = new StreamingContext(conf,Seconds(3))
    /**
      * 参数一： StreamingContext
      * 参数二：zkQuorum zookeeper 连接地址
      * 参数三： groupId  分组id
      * 参数四：topics （键值对）
      */
    val kafkaDStream: ReceiverInputDStream[(String, String)] =
        KafkaUtils.createStream(ssc,
          "hadoop102:2181",
          "third",
          Map("third"->3))
    //扁平化
    val wcflatmap: DStream[String] = kafkaDStream.flatMap(t=>t._2.split(" "))

    //map
    val dsMap: DStream[(String, Int)] = wcflatmap.map((_,1))

    val dsreduce: DStream[(String, Int)] = dsMap.reduceByKey(_+_)
    //打印
    dsreduce.print()
    //开始
    ssc.start()
    ssc.awaitTermination()





  }

}
