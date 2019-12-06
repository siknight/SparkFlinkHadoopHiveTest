package scalatest.ten.模式匹配

object 类型匹配 {




  def main(args: Array[String]): Unit = {

      println("hehe")
      val tuple: (String, Int, Double, String, List[Int])
          = ("hello",1,2.2,"wode",List(1,2))
      val iterator: Iterator[Any] = tuple.productIterator
      while (iterator.hasNext){
        val value: Any = iterator.next()
         matchTest(value)
      }


    //模式匹配不仅可以匹配值还可以匹配类型
    //从上到下匹配，匹配上一个之后就不会继续往下匹配
    //这个方法放到前面，居然不能调用 ，非常奇怪
    def matchTest(o:Any):Unit={
      o match {
        case 1 =>println("value is 1")
        case i:Int =>println("类型是Int,value is "+i)
        case "hello"=>println("value is hello")
        case k:Double=>println("type is double ,value is "+k)
        case s:String =>println("类型是String ，value is "+s)
        case _ =>println("default")
      }



    }






  }

}
