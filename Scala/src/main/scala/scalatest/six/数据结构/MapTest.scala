package scalatest.six.数据结构

object MapTest {
  def main(args: Array[String]): Unit = {
    val map: Map[String, Int] = Map("si"->1,"jiang"->2,"xiang"->3)
    val map2: Map[String, Int] = map.+(("chun",4))
    println("map="+map.mkString+",map2="+map2.mkString("|"))

    val map3: Map[String, Int] = map.-("si")
    println("map3="+map3);
    println(map.getOrElse("hehe",0))

  }

}
