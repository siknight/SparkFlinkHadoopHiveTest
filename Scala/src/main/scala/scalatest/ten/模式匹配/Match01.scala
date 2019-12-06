package scalatest.ten.模式匹配

object Match01 {

  def main(args: Array[String]): Unit = {
    val op = "-"
    val aa="haha";
    var result:Int = 0
    op match {
      case "+" =>result =1
      case "/" =>result = -1
      case _ if "haha".equals(aa) =>result= 22
      case _ =>result = 0;
    }

    println(result)
    println("****************")

  }

}
