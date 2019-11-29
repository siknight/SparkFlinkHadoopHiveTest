package scalatest.forth.ifwhile

object forTest {

  def main(args: Array[String]): Unit = {
      //to前闭后闭
      for(i<-1 to 3){
        println("i="+i);
      }
    println("----");
      //util前闭后开
      for(j<- 1 until 3){
        println("j="+j)
      }
    println("----");
    //此种写法类似于continue
    //循环守卫
    for(z<-1 to 3 if z!=2){
      println("z="+z);
    }
    //引入变量
   for(i<- 1 to 3 ; k = i+4){
     println("k="+k)
   }
    println("----")
    //循环嵌套
    for(i<- 1 to 3; j<- 1 to 3){
      println(i+" * "+ j +"="+i*j);
    }
    print("------")
    //循环返回值：（将遍历过程中处理的结果返回到一个新集合中，使用yield关键字）
    val for5 = for(i <- 1 to 10) yield i
    println(for5)

  }

}
