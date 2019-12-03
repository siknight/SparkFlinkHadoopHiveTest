package scalatest.third.面向对象.ImplicitTest

object ImplicitTest {
  def main(args: Array[String]): Unit = {
    implicit  def toInt(d:Double)=d.toInt;

    var i:Int = 3.5;
    println(i)

    //隐式值
    implicit val name = "nick";
    def person(implicit name:String)={
      println(name)
    }
    println("***person***")
    person  //隐式值，在运行时不用带参数
    //隐式类,必须有有一个参数的主构造函数
    implicit  class LeiTest(var s:String){
      def wodeheheh=s+"scala";
    }

    println("******隐式类****")
    println("ss".wodeheheh)

  }

}
