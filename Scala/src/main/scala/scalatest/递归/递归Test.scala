package scalatest.递归

object 递归Test {

  def digui(n:Int):Int ={
    if (n==1){
      1
    }else{
      n*digui(n-1)
    }
  }

  def main(args: Array[String]): Unit = {
    println(digui(4))  //4*3*2
  }

}
