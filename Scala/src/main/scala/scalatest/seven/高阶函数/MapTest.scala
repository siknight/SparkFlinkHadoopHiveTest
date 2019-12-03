package scalatest.seven.高阶函数

object MapTest {
  def main(args: Array[String]): Unit = {
    // 作为参数的函数
    def plus(s:Int)={
      s+3;
    }
    val array: Array[Int] = Array(1,2,3,4).map(plus(_))
   for (a<-array){
     print(a+" ")
   }
    //匿名函数
    val tripe =(x:Double) =>3*x;
    print(tripe(8))

  }

}
