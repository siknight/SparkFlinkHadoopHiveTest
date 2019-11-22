package flinkstream.wc.离线

import org.apache.flink.api.scala.{AggregateDataSet, DataSet, ExecutionEnvironment}

/**
  * flink离线处理
  */
//flink处理一共分为四步  1.env,创建执行flink环境 2.source，创建数据来源
// 3.transform 转换 4.sink  执行想要结果
object WorldCount {
  def main(args: Array[String]): Unit = {
    //1.env,创建执行flink环境
    val environment: ExecutionEnvironment = ExecutionEnvironment.getExecutionEnvironment

    // 2.source，创建数据来源
    val source: DataSet[String] = environment.readTextFile("in")

    //3.transform 转换
    // 其中flatMap 和Map 中  需要引入隐式转换
   import org.apache.flink.api.scala.createTypeInformation
    val result: AggregateDataSet[(String, Int)] = source.flatMap(_.split(" ")).map((_, 1)).groupBy(0).sum(1)

    //sink  执行想要结果
    result.print();
  }
}
