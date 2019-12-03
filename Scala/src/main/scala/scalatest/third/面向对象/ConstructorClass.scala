package scalatest.third.面向对象

import scala.beans.BeanProperty

class ConstructorClass {
  var a="hello";
}

object ConstructorClass{

}

class ConstructorClass2() {
  var a="hello";
    @BeanProperty
    var name:String ="hehe"
    @BeanProperty
    var age:Int =22
  def this(name:String,age:Int){
    //辅助构造器无论是直接或间接，最终都一定要调用主构造器，执行主构造器的逻辑
    this  //放在第一行，没有会报错

    this.name =name;
    this.age =age;
  }

  def this(name:String,age:Int,sex:String){
    this(name,age)
  }
}

class ConstructorClass3(arg : String) {

  var a="hello";
 def hello={
   print(arg)
 }
}

//将主构造器变成私有
class ConstructorClass4 private (){
  def this(name:String){
    this
    println(s"name=$name")
  }
}

object test{
  def main(args: Array[String]): Unit = {
    //构造函数没有参数时，new对象时()可以省略
    val clazz = new ConstructorClass
    println(clazz.a)

    println("***************1");
    val clazz2 = new ConstructorClass2
    println(clazz2.a)

    println("************2")
    new ConstructorClass3("nihao").hello

    println("************2")
    val person = new ConstructorClass2("lisi", 20)
    println(s"name:${person.getName} ------ age:${person.getAge}")

    println("*********主构造器私有*****")
    new ConstructorClass4("lisi");
    println("*********object伴生对象*****")


  }
}
