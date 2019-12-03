package test01.实时

import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}

object WcStream {
  def main(args: Array[String]): Unit = {
    val env: StreamExecutionEnvironment
        = StreamExecutionEnvironment.getExecutionEnvironment
    val ds: DataStream[String]
        = env.socketTextStream("hadoop102",9999)
    import org.apache.flink.api.scala._
    val result: DataStream[(String, Int)] =
      ds.flatMap(_.split(" ")).map((_,1)).keyBy(0).sum(1)

    //打印
    result.print()
    // 在hadoop102 执行下面命令nc -lk  9999
    env.execute()

  }

}
