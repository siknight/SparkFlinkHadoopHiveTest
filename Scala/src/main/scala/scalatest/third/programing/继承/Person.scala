package scalatest.third.programing.继承

class Person {
  var name:String = "zhangsan";
  def printName(): Unit ={
    println(name);
  }
}

class Emp extends Person{
 override def printName() {
   println(name);
 }
}


object PersonTest{
  def main(args: Array[String]): Unit = {
    val emp = new Emp()
    emp.name="niaho"
    emp.printName()
  }
}
