package scalatest.偏函数应用

import java.util.Date

object funcTest {

  //正常函数
  def da01(d:Date,log:String): Unit ={
    println(s"日期:$d  log:$log")
  }

  val data = new Date();
  //正常函数，每一次相同data都得传
  da01(data,"a");
  da01(data,"b")
  da01(data,"c")

  //偏函数,data在外部传过来，变量用_表示
 val fun = da01(data:Date,_:String)
  fun("aaa")
  fun("bbb")
}
