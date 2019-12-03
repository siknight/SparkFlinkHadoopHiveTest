package scalatest.third.面向对象.ImplicitTest

class ImplicitTest02 {

}

class Mysql{
  def insert( id : Int ): Unit = {
    println("向数据库中插入数据：" + id)
  }
}
class DB {
  def delete( id : Int ): Unit = {
    println("从数据库中删除数据：" + id)
  }
}


object TestMysql{
  def main(args: Array[String]): Unit = {
    val mysql = new Mysql();
    implicit def toMysql(mysql:Mysql)={
      new DB();
    }
    mysql.delete(1);
  }
}