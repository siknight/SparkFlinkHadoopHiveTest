package scalatest.five.javascala

import java.util


/**
  * scala调用java，直接写java代码，很简单
  */
object JavaToScalaTest {
  def main(args: Array[String]): Unit = {
    //scala 调用java
    val list = new util.ArrayList[String]()
    list.add("aa")
    println(list)
  }

  def aa(): Unit ={
    print("aaaa")
  }
}

object  ScalaHeHe{

  def aa= "niaho"

  def bb(a:Int,b:Int) ={
    println(s"${a}*****${b}");
  }
  def main (args: Array[String] ): Unit = {
    JavaToScalaTest.aa();

    println("-----")
   print(aa)
    print("----")
    print(aa)
    println("--")
    bb(1,2)

  }
}
