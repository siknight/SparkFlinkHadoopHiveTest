package scalatest.six.数据结构

import scala.collection.mutable
import scala.collection.mutable.{ArrayBuffer, ListBuffer}

//list是seq分支
object ListTest {
  def main(args: Array[String]): Unit = {
    //不可变集合
    val list: List[Int] = List(1,2,3)
    val list02: List[Int] = 4::5::list
    list02.foreach(x =>{println(x)})
    val list03:List[Int] = List(6, 7, 8)
    //是any类型
    val list04: List[Any] = list02::list03
    println(list04)
    //带:::会将每个集合的子元素分开
    val list05: List[Int] = list02:::list03
    println(list05)
    println("--------------------------------")
    val list06: List[Int] = list.+:(10) //每次回产生一个新的集合
    println(s"list=$list06")
    println(list06.mkString("|"))
    println(list06)
    println("-------------array可变集合-------------------")
    val listBuffer: ArrayBuffer[Int] = ArrayBuffer(1,2,3)
    listBuffer.+=:(5)

    println("listbuffer="+listBuffer)

    println("-------------array不可变集合-------------------")
    val array: Array[Int] = Array(1,2,3)
    val array02: Array[Int] = array.+:(5)
    println("array="+array.mkString(",")+",array02="+array02.mkString("|"))
    println("-------------list可变集合-------------------")
    val listBuffer02: ListBuffer[Int] = ListBuffer(1,2,3)
    listBuffer02.append(6)
    println(listBuffer02)
    println("-------------quene可变集合-------------------")
    val queue: mutable.Queue[Int] = mutable.Queue(1, 2, 3)//队列一定是可变的
    queue.enqueue(9);
    println(queue.mkString(","))
    queue.dequeue()
    println(queue)




  }

}
