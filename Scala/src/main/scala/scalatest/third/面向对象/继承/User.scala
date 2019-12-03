package scalatest.third.面向对象.继承

class User {
  //申明属性，，scala给类声明属性，默认是私有的，但是底层
  //提供了公开的setter和getter方法
  var name:String = _
  //如果给属性增加private修饰符，name底层的setter和getter方法都是私有的
 private var age:Int = _
  //如果声明的属性使用val，那么属性是私有的，并且使用final修饰，底层只提供getter，而没有setter方法
//  val  address:String = _


}

  object UserTest{
    def main(args: Array[String]): Unit = {
      val user = new User()
      user.name="lisi"

    }
  }