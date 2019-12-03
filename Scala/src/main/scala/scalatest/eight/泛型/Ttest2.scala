package scalatest.eight.泛型

object Ttest2 {
  def main(args: Array[String]): Unit = {
    val test01: Testhehe[User2] = new Testhehe[User2]
    //在scala为了丰富泛型的功能，提供了协变（+）和逆变（-）
    //协变
    val test02: Testhehe02[User2] = new Testhehe02[Child2]
    //逆变
    val test03: Testhehe02[User2] = new Testhehe02[Child2]

    val intList: List[Int] = List(1,2,3,4)
    val user: User = new User
    val reduceList: ((Int, Int) => Int) => Int = intList.reduceLeft[Int](_)
    print(reduceList)
    val intList2: List[String] = List("a","b","c","d")
//    intList2.reduceLeft()
  }
}

class Person2{

}

class User2 extends  Person2{

}
class Child2 extends User2 {

}

class Testhehe[User2]{

}
//表示User2的子类也可以用此类
class Testhehe02[+User2]{

}
//表示User2的父类也可以用此类
class Testhehe03[-User2]{

}
