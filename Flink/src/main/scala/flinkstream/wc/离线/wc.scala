package flinkstream.wc.离线

import org.apache.flink.api.scala.{AggregateDataSet, DataSet, ExecutionEnvironment, GroupedDataSet}

/**
  * Flink二种写法
  */
object wc2 {
  def main(args: Array[String]): Unit = {
    //1.创建环境
    val environment: ExecutionEnvironment = ExecutionEnvironment.getExecutionEnvironment

    //2.引入资源

    val lines: DataSet[String] = environment.readTextFile("in/word1")
    println("*******lines*******")
    lines.print()

    // 其中flatMap 和Map 中  需要引入隐式转换
    import org.apache.flink.api.scala.createTypeInformation
    //处理
    val words: DataSet[String] = lines.flatMap(_.split(" "))
    println("************words*********")
    words.print()
    val wcwords: DataSet[(String, Int)] = words.map((_,1))
    println("*******map()**********")
    wcwords.print()
    //第一种写法
    val wcgroup: GroupedDataSet[(String, Int)] = wcwords.groupBy(t=>t._1)
    println("*******wcgroup*******")

    val result: DataSet[(String, Int)] = wcgroup.reduce((t1,t2)=>(t1._1,t1._2+t2._2))
    println("********reduceresult*********")
    result.print()
    //第二种写法
    println("******第二种写法******")
    val wcgroup2: GroupedDataSet[(String, Int)] = wcwords.groupBy(0)

    val result02: AggregateDataSet[(String, Int)] = wcgroup2.sum(1)

    result02.print()




  }

}
