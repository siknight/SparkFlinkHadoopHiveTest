package scalatest.third
package programing{
  object PackageTest {
    def main(args: Array[String]): Unit = {
      println("nihao")
    }
  }
}

package object programing{
  def main(args: Array[String]): Unit = {
    println("wode")
  }
}

object PackageTest02{
  def main(args: Array[String]): Unit = {
    println("wode")
  }
}