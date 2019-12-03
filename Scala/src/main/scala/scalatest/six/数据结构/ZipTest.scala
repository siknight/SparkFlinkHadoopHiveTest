package scalatest.six.数据结构

object ZipTest {

  def main(args: Array[String]): Unit = {
    val list1 = List("1", "2", "3")
    val list2 = List(4, 5)
    var z1 = list1 zip list2
    println(z1)

//    print(z1.flatMap(_))
    val iterator: Iterator[String] = list1.iterator;
    while (iterator.hasNext){
      print(iterator.next())
    }

  }
}
