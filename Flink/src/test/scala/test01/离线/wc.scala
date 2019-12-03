package test01.离线

import org.apache.flink.api.scala.{DataSet, ExecutionEnvironment, GroupedDataSet}

object wc {
  def main(args: Array[String]): Unit = {
    //1.创建环境
    val environment: ExecutionEnvironment = ExecutionEnvironment.getExecutionEnvironment

    //2.引入资源

    val lines: DataSet[String] = environment.readTextFile("in/word1")

    // 其中flatMap 和Map 中  需要引入隐式转换
    import org.apache.flink.api.scala.createTypeInformation
    //处理
    val words: DataSet[String] = lines.flatMap(_.split(" "))

    val wcwords: DataSet[(String, Int)] = words.map((_,1))

    val wcgroup: GroupedDataSet[(String, Int)] = wcwords.groupBy(t=>t._1)
    println(wcgroup)
    val result: DataSet[(String, Int)] = wcgroup.reduce((t1,t2)=>(t1._1,t1._2+t2._2))

    result.print()




  }

}
