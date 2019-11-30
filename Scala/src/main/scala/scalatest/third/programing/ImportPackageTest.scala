package scalatest.third.programing

import java.util.{HashMap=>JavaHashMap,ArrayList=>JavaArrayList}

object ImportPackageTest extends App{

    new JavaHashMap[String,Object]();
//    new ArrayList[String](); //上面重命名以后，这样定义直接不能用了
    private val strings = new JavaArrayList[String]()
  strings.add("lisi")
  println(strings.get(0));
}
