package scalatest.third.programing


//没有修饰符
class ConstructorTest(name:String) {

}
//val修饰符
class ConstructorTest2(val name:String) {

}
//var修饰符
class ConstructorTest3(var name:String) {

}

object test22{
  def main(args: Array[String]): Unit = {
    val hehe = new ConstructorTest("hehe01")
    //此对象不能set和get
    println(hehe)
    println("*********1")
    //val 定义的构造方法，只能取值不能
    val hehe2 = new ConstructorTest2("hehe2")
    println(hehe2.name)
    //var定义的构造方法，会自动生成set和get构造方法
    println("********2")
    val hehe3 = new ConstructorTest3("hehe3");
    hehe3.name="sisi"
    println(hehe3.name)
  }
}
