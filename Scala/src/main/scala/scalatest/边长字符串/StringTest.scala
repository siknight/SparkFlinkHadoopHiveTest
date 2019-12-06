package scalatest.边长字符串

object StringTest {

  def aa(aa:String*)={
    for (k<-aa){
      println(k)
    }
  }
  def main(args: Array[String]): Unit = {
    aa("1","2","3")
  }

}
