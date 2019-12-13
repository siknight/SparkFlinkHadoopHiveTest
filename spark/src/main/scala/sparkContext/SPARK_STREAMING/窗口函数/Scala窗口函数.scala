package sparkContext.SPARK_STREAMING.窗口函数

object Scala窗口函数 {
  def main(args: Array[String]): Unit = {
    val list = List(1,2,3,4,5)

    val iterator: Iterator[List[Int]] = list.sliding(3,3)

    for (list<-iterator){
      println(list.mkString(","))
    }
  }

}
