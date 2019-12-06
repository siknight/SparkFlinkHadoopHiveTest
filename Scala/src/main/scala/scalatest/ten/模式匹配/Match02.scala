package scalatest.ten.模式匹配

object Match02 {

  def main(args: Array[String]): Unit = {
    val op = "-"
    val aa="haha";
    var result:Int = 0
    op match {
      case "+" =>result =1
      case "/" =>result = -1
      case bb => println(bb)
      case _ =>result = 0;
    }



  }

}
