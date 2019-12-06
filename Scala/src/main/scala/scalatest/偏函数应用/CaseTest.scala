package scalatest.偏函数应用

import scala.collection.mutable

object CaseTest {

  def main(args: Array[String]): Unit = {

    val list: List[Any] = List(1,2,3,4,5,"heheh")
    val listTest: List[Any] = list.map {
      //这种写法和下面第二种写法，在java里编译后代码是一样的
      case i: Int => i + 1   //此种是偏函数
      case s:String=>s
    }
    println(listTest.mkString(","))
    println("************2***************");

    val list2: List[Any] = list.map(x => {
      x match {
        case i: Int => i + 1
        case s: String => s
      }
    })
    println(list2.mkString("|"))

    println("************3***************");
    val listif: List[Any] = list.map(x => {
      if (x.isInstanceOf[Int])
        x.asInstanceOf[Int] + 1
      else
        x
    })
    listif.foreach(x=>print(x+","))
    println()
    println("***********option****************");
    println(Option(listif))

//   PartialFunction
      val p:PartialFunction[Int, String] = { case 1 => "One" }

    val strings: List[String] = List("hello scala","hello lisi","jiang")

    val kkFlatMap: List[String] = strings.flatMap {
      case x => {
        if (x.split(" ").length > 1)
          x.split(" ")
        else
          List(x)
      }
    }

    println(kkFlatMap.mkString(","))
    println("***********end01***************");
    val strings2: List[String] = List("hello hello scala","hello lisi","jiang")

    strings2.flatMap(x=>{
      val stringToInt: Map[String, Int] = x.split(" ").groupBy(y=>y).mapValues(k=>k.toList.size)
      stringToInt

    }).foreach(println(_))





  }

}
