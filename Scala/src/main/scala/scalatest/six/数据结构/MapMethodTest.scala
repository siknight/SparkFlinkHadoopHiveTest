package scalatest.six.数据结构

object MapMethodTest {
  def main(args: Array[String]): Unit = {
    val list = List("si", "jiang")
    def toUpper(s:String) ={
      s.toUpperCase;
    }
    val s= list.map(toUpper(_))
    println(s)
    println("******")
    println(list.map(x=>x.toUpperCase))
    println("******")
    println(list.map(_.toUpperCase))
    println("******flatmap*******")
    println(list.flatMap(_.toUpperCase))
    println("***filter***")
    def fi(s:String) ={
      s.startsWith("s")
    }
    val flist = List("si", "jiang")
    println(flist.filter(fi(_)))

  }

}
