package scalatest.sec.method

object MethodTest {

  def main(args: Array[String]): Unit = {
    // 将方法赋值给变量使用,没有参数时，（）可以省略
    val v1 =f1;
    // 调用方法
    v1
    println("----------")
    val v2= f2();
    v2
  }

  // 声明方法
  def f1(): Unit ={
    println("aa")
  }

  def f2(): Unit ={
    f1();
  }
}
