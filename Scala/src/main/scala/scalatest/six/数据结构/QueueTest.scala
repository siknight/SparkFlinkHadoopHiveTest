package scalatest.six.数据结构

import scala.collection.mutable

object QueueTest {
  def main(args: Array[String]): Unit = {
    val queue = new mutable.Queue[String]()
    queue.enqueue("lisi");;
    println("queue="+queue);
    println("head="+queue.head);
    queue+="jiang";

    print("queue="+queue)
    println("********map******")
    val map = mutable.Map("wo" -> 10, "nihao" -> 20)
    println(map.get("wo"))
    println(map)
  }

}
