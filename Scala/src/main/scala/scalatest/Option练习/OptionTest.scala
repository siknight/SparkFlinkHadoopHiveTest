package scalatest.Option练习

object OptionTest {

  def main(args: Array[String]): Unit = {
    val myMap: Map[String, String] = Map("key1" -> "hehe")
    //Scala Option(选项)类型用来表示一个值是可选的（有值或无值)。
    //Option[T] 是一个类型为 T 的可选值的容器：
    // 如果值存在， Option[T] 就是一个 Some[T] ，
    // 如果不存在， Option[T] 就是对象 None 。
    val value01: Option[String] = myMap.get("key1")
    val value02: Option[String] = myMap.get("key2")

    println(value01)  //Some(hehe)
    println(value02) //None

  }

}
