package flinkstream.wc.实时

import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}

object WorldCountStreamApp {
  /**
    * finkStram实时计算
    */
  def main(args: Array[String]): Unit = {

    //1.env,创建执行flink环境
    val environment: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment

    // 2.source，创建数据来源
    val hostname:String="hadoop102";
    val port:Int=8888;
    val source: DataStream[String] = environment.socketTextStream(hostname, port)

    //3.transform 转换
    //flatMap和Map需要引用的隐式转换 keyBy(0) 0表示map的第一个参数 sum(1) 表示map的第二个参数
    import org.apache.flink.api.scala._
    val result: DataStream[(String, Int)] = source.flatMap(_.split(" ")).map((_, 1)).keyBy(0).sum(1)

    //4.sink
    result.print();
    //实时处理必须要带这个方法
    //在hadoop102 输入 nc -lk  8888 运行程序
    environment.execute();
  }

}
