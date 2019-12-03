package scalatest.seven.高阶函数

object HighOrderTest {

  def main(args: Array[String]): Unit = {
    def highOrderFunction1(f: Double => Double) = f(10)
    def minus7(x: Double) = x - 7
    val result2 = highOrderFunction1(minus7)
    println(result2);
    println("***********");
    def aa(f:Double =>Int)={

    }
    def toInt(d:Double)={
      d.toInt
    }

  }

}
