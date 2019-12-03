package scalatest.six.数据结构

import scala.collection.mutable.ArrayBuffer

object ArrayTest {

  def main(args: Array[String]): Unit = {
    //[]表示数组的类型，（）里面表示数组的长度
    val array = new Array[String](3)
    array(1) = "lisi"; //用小括号来访问值
    println(array(1))
    println("*****")

    val array001 = Array(1,2,3);

    println("array001="+array001)
    print("*******")
    println("*****变长数组****")
    val array02 = new ArrayBuffer[Int]()
    //追加值
    array02.append(7)
    array02.append(10)
    println(array02)
    println("多维数组");

    val array03: Array[Array[Int]] = Array.ofDim[Int](3, 4)
    array03(1)(1)=12;

    println("*************")
    val buffer = ArrayBuffer("si","jiang")
//    println(buffer)
    for (s<- buffer){
     println("s="+s)
    }

  }

}
