package scalatest.nine.file

import scala.io.Source

object FileTest {
  def main(args: Array[String]): Unit = {
    val list: List[String] = Source.fromFile("in/word1").getLines().toList
    val map: Map[String, Int] =
      list.flatMap(x=>x.split(" ")).map((_,1)).groupBy(g=>g._1).map(m=>(m._1,m._2.size))
    println(map.mkString("|"))

    println("*************拆开****************")
    //flatMap返回类型会依赖调用的类型，调用的类型是什么，返回的就是什么类型
    val listflap01: List[String] = list.flatMap(_.split(" "))  //List(hello, world, hello, scala, hello, spark)
    println(listflap01)
    //map返回类型会依赖调用的类型，调用的类型是什么，返回的就是什么类型
    val mapList: List[(String, Int)] = listflap01.map((_,1)) //List((hello,1), (world,1), (hello,1), (scala,1), (hello,1), (spark,1))
    println(mapList)
    //GroupBy()方法会将所有类型转换为map集合
    val mapGroup: Map[String, List[(String, Int)]] = mapList.groupBy(t => t._1)
    print(mapGroup)
    //map集合通过map（）方法后还是map集合
    val stringToInt: Map[String, Int] = mapGroup.map(t=>(t._1,t._2.size))


    Source.

  }

}
