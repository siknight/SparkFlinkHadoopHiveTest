package scalatest.first.main方法
class aa{
  println("haha ")

  def wode: Unit ={
    println("你好")
  }
}

object aa{

}
/**
  * scala第一个程序，用于测试scala main方法
  * object里的方法和变量都是静态的
  * main方法的类必须是object修饰
  */
object MainTest {

  def main(args: Array[String]) {
    //后面的;可以省略
    println("first scala main method");

    new aa



  }

}
