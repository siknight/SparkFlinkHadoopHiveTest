package scalatest.forth.ifwhile

object ExceptionTest {

  @throws(classOf[NumberFormatException])
  def f11={
    "abc".toInt;
  }

  def main(args: Array[String]): Unit = {

    try{
        var a =10/0;
    }catch {
      case ex:ArithmeticException =>{
        print("发生了算术异常")
      }
      case ex:Exception =>print("发生了异常");
    }
  }

}
