package scalatest.forth.ifwhile

import scala.util.control.Breaks


object BreakTest {

  def main(args: Array[String]): Unit = {
    var i =1;
    while (i<=10){
      if (i==5){
        Breaks.break();
      }else{
        println(i)
        i=i+1;
      }
    }
  }

}
