package scalatest.six.数据结构

object ReduceTest {

  val list = List(1, 2, 3, 4, 5)
  def minus( num1 : Int, num2 : Int ) : Int = {
    num1 - num2
  }

  def main(args: Array[String]): Unit = {
    val i1 = list.reduceLeft(minus)
    println(i1)
    val i2 = list.reduceRight(minus)
    println(i2)
  }

}
