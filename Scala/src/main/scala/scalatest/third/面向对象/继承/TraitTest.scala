package scalatest.third.面向对象.继承

class TraitTest {

}

trait Logger{
  def log(msg:String): Unit ={
    println("你好")
  }
}

class Console extends Logger with Cloneable with Serializable{

}
