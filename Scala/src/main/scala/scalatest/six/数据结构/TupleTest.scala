package scalatest.six.数据结构

object TupleTest {
  def main(args: Array[String]): Unit = {
    val tuple: (Int, Int, Int, String) = (1,2,3,"sisi");
    println(tuple._1)
    println("************  ")
    for (t <- tuple.productIterator){
      println(t)
    }
    println("**list***")
    val list = List(1, 2, 3)
    println(list)
    for(l <-list){
      println(l)
    }
    val list02 =Nil
    println("list02="+list02)
    val list03 = list02:+9;
    val list04 =100+:list03;
    println(list04)
    println("****list05***")
    val list05=1::2::3::list04
    println(list05)
  }

}
