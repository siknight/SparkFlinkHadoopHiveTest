package scalatest.third.面向对象.继承

class Person {
  var name:String = "zhangsan";
  def printName(): Unit ={
    println(name);
  }
}

class Emp extends Person{
  def this(name:String){
    this;
    this.name=name;
  }
 override def printName() {
   println(name);
 }
}

abstract  class Person2 { // 抽象类
  var name:String;  //  抽象字段,属性未初始化时，就是抽象的
                    //用抽象字段时，class前面必须带 abstract
  def hello; //抽象方法

}

class Emp2 extends Person2{
   var name: String = _ //注：子类重写抽象方法不需要override
  //注：子类重写抽象方法不需要override
   def hello: Unit = ???
}

object PersonTest{
  def main(args: Array[String]): Unit = {
    val emp = new Emp()
    emp.name="niaho"
    emp.printName()
    //匿名内部类
    val person: Person2 = new Person2 {
      //_ 表示初始化
      override var name: String = _
      var age: Int = _

      override def hello: Unit = ???
    }

  }
}
