package scalatest.third.programing
//单例模式练习
class SingleMode {
  def aa(): Unit ={
    println("hello world")
  }
}

object SingleMode {
  def apply(): SingleMode = new SingleMode()
}

class SingleMode2 {
  def aa(): Unit ={
    println("hello world")
  }
}

object SingleMode2 {
  def apply: SingleMode2 = new SingleMode2()
}
object  testaa{
  def main(args: Array[String]): Unit = {
    SingleMode.apply.aa();
    println("******")
    val s = SingleMode() //
    s.aa()
    println("*****")
    val mode: SingleMode2.type = SingleMode2
    //不带括号调用不了
//    mode.aa();

  }
}