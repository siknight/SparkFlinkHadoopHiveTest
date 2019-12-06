package scalatest.seven.高阶函数

object HanShuTest {

  def main(args: Array[String]): Unit = {
    //函数
    def aa(f:(Int,Int)=>Int):(String,String)=>String={
      val i = f(10,20)
      def f2(a:String,b:String):String={
        a+"@"+b+"@"+i
      }
      f2
    }
    //函数

    val str: String = aa((x,y)=>x*y)("1","2")
    println(str)
    println("___________")
    def bb(f:(Int,Int)=>Int)={
      val i = f(10,20)
      def f2(a:String,b:String):String={
        a+"@"+b+"@"+i
      }
      f2 _
    }

    val bbstr: String = bb((x,y)=>x*y)("1","2")
    println(bbstr)


    //柯里化
    def cc(a:Int)(b:Int)={
      a*b
    }

    println(cc(1)(2))
  }
}
