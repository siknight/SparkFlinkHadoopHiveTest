package scalatest.eight.泛型

object Ttest {
  def main(args: Array[String]): Unit = {
    test(new User)
//    test2(new Person) //不是子类会报错
    test2(new User)
  }

  def test[T](t:T): Unit ={
    print(t)
  }
  //<:表示T必须为User或者User的子类,表示泛型的上限
  //>:什么都可以传
  def test2[T <:User](t:T): Unit ={
    print(t)
  }


}

class Person{

}

class User extends  Person{

}
class Child extends User {

}