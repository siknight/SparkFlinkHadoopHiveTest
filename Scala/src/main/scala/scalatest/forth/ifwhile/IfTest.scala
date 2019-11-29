package scalatest.forth

import java.util

object IfTest {
  def main(args: Array[String]): Unit = {
    println("math="+math.sqrt(100))
    println("----------")



    val sumval = 0;
    val result: Unit = if (sumval == 0) {
      println("sumval="+sumval)
    }else{
      //这里调用的java的方法
      println("sumval="+Math.PI)
    }
    println("-----------")
    result

    println("-----")
    val sumval2 = 0;
    val str: String = if (sumval2 == 0) {
      "aa"
    }else{
      "bb"
    }
    str
    println("result02="+str)
  }


}


