package scalatest.to和util

object toAndUtil {

  def main(args: Array[String]): Unit = {
    println(1 to 10)
    println("***.to带步长*****")
    println(1.to(10,2)) //1,3,5

    println("******until*****")
    println(1 until  10)
    println("*******.util**********")
    println(1.until(10,3))
  }

}
