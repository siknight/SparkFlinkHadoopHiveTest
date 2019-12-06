package scalatest.样例类
//加case后会变成样例类，会自动生成参数的set，get和equals方法
//样例类{}可以省略，一般不写，因为没啥意义
// 默认构造函数里面的值是val，当然也可以改成var
//样例类可以不写new 直接写类名构造对象

case class Man(name:String,var age:Int)
object testMan{
  def main(args: Array[String]): Unit = {
    val lisi01 = new Man("lisi",18)
    val lisi02 = Man("lisi",18)  //样例类可以省略new
    //如果不是样例类，这里会为false
    println(lisi01.equals(lisi02))
  }
}
