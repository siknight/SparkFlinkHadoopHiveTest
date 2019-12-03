package scalatest.six.数据结构

import scala.io.{BufferedSource, Source}

object ListTest03 extends App {
  private val list: List[Int] = List(1,2,3,4,1,2,1);
//  list.groupBy(x=>x)
//  ()=>{
//    print("aa")
//  }
//  ()=>print("aa")
  println("111111111111111")
  list.foreach(x=>{
    print(x)
  })
  println("2222222222222222")
  list.foreach(x=>print(x))
  println("333333333333333333")
  list.foreach(print(_))

  val list2: List[String] = List("si","jiang","xiang");
  list2.foreach[Unit](print(_))
  println("*********groupby()***********")
  //groupby会将所有调用类型都转为Map类型
//  private val listgroup: Map[Int, List[Int]] = list.groupBy(x=>x)
  val listgroup: Map[Int, List[Int]] = list.groupBy(x=>x)
  println(listgroup)
  //map（）就是将当前集合的样式改变。
  private val map: Map[Int, Int] = listgroup.map(t=>(t._1,t._2.size));
  println(map)
  map.foreach(t=>println(t._1+" "+t._2))
  println("***************************************")
  //1.读数据
    private val listSource: List[String] = Source.fromFile("in/word1").getLines().toList
  //2.处理
  private val flatList: List[String] = listSource.flatMap(x=>x.split(" "))
  println(flatList)
//  flatList.map(x=>(x,1));
  private val flatmapfile: List[(String, Int)] = flatList.map((_,1))
  println(flatmapfile)
  private val flatmapGroup: Map[String, List[(String, Int)]] = flatmapfile.groupBy(t=>t._1)
  println(flatmapGroup)

    private val listgg: List[(String, Int)] = flatmapGroup.map(t=>(t._1,t._2.size)).toList.
              sortWith((x,y)=>x._2>y._2).take(2)
    println(listgg)

  println("模式匹配")
  val  (aa,bb,cc)=("11","22","33")
  print(aa)

  //控制抽象

  println("***********控制抽象****************")
  //控制抽象，函数参数没有输入值，也没有返回值
  def test(f: =>Unit) ={
    f
  }

  test{
    print("hehe")
  }





}
