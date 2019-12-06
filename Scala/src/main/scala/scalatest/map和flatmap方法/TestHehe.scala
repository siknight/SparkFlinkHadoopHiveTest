package scalatest.map和flatmap方法

object TestHehe {

  def main(args: Array[String]): Unit = {

    val str: List[String] = List("hello scala","hello lisi")
      //下面这个flatMap
     val flatten: List[String] = str.map(x=>x.split(" ")).flatten

    println(flatten.mkString(","))

  }

}
