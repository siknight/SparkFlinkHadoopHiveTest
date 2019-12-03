package scalatest.third
package 面向对象{
  object PackageTest {
    def main(args: Array[String]): Unit = {
      println("nihao")
    }
  }
}

package object 面向对象{
  def main(args: Array[String]): Unit = {
    println("wode")
  }
}

object PackageTest02{
  def main(args: Array[String]): Unit = {
    println("wode")
  }
}