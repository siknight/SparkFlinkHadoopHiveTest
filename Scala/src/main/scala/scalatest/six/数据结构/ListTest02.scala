package scalatest.six.数据结构

import scala.collection.immutable

/**
  * 很重要
  */
object ListTest02 {
  def main(args: Array[String]): Unit = {
    val list: List[String] = List("11","23","22","12");
    val tuple: (Int, Int, Int) = (22,333,44)

    println("********min***********")
    println("min="+list.min)
    println("********max*********")
    println("max="+list.max)
//    println("********乘积*********")
//    println("product="+list.product)
    println("********反转*********")
    println("reverse="+list.reverse)
    println("********分组***********")
    val map02: Map[String, List[String]] = list.groupBy(x => {
      x.substring(0, 1)
    })
    println(map02);
    println("*********foreach*************")
    map02.foreach(t=>{
      println(t._1+"="+t._2)
    })
    println("*********foreach2*************")
//    map02.foreach((s,l)=>{println("")})
    println("*********foreach2*************")

    println("*********soutBy*************")
    print(list.sortBy(x=>x))
    println("*********soutWith*************")
    //x<y 从小到大排
    println(list.sortWith((x, y) => x < y))
    //x>y,从大到小排列
    println(list.sortWith((x, y) => x > y))

    println("******map映射（转换）*********")
    val list02: List[Int] = List(1,2,3,4,1,1,2)
    val list3: List[(Int, Int)] = list02.map(x=>(x,1))
    println(list3)
    val maps: Map[Int, List[(Int, Int)]] = list3.groupBy(x=>x._1)
    println(maps)
    val maps2: Map[Int, Int] = maps.map(t=>(t._1,t._2.size))

    for ((k,v)<-maps2){
      println(s"$k $v")
    }

    println("****take*****")
    println(list02.take(3))
    println("*********业务需求**************")
    val strList: List[String] =
      List("hello", "scala", "spark", "hadoop", "scala", "scala", "spark","scala","spark","hello","word")
    //处理办法1
    val strMap01: List[(String, Int)] = strList.map(s=>(s,1))
    //这里的（x,y）键值对因为是map集合的，所以是一个整体，不能拆开
    val strgroup: Map[String, List[(String, Int)]] = strMap01.groupBy(t=>t._1)
    val  tolist: List[(String, Int)] = strgroup.map(t=>(t._1,t._2.size)).toList
    val sortList: List[(String, Int)] = tolist.sortWith((t1,t2)=>t1._2>t2._2).take(3)
    sortList.foreach(t=>println(t._1+" "+t._2))
    //处理办法2
    println("*******处理办法2*********")
    val gmap: Map[String, List[String]] = strList.groupBy(x=>x)
    val mapwc: Map[String, Int] = gmap.map(t=>(t._1,t._2.size))
    val wclist02: List[(String, Int)] = mapwc.toList
    wclist02.sortWith((t1,t2)=>t1._2>t2._2).take(3).foreach(t=>println(t._1+" "+t._2))

    println("*******flatmap*********")
    //flatmap扁平化操作，将一个整体中的内容拆成一个一个的个体
    val listflatmap: List[String] = List("hello world", "hello scala", "hello spark")
    val strflat: List[String] = listflatmap.flatMap(x=>{x.split(" ")})
    println("strflat="+strflat)
    val flatlist: List[(String, Int)] = strflat.groupBy(x=>x).map(t=>(t._1,t._2.size)).toList
    println(flatlist)

    print("*******filter****")
    //对集合中的数据进行筛选过滤，true
    val filterList: List[Int] = list02.filter(x => x%2==0)
    println(filterList)
    println("*****zip******")

    //拉链
    val listzip01: List[Int] = List(1,2,3,4)
    val listzip02: List[Int] = List(4,5,6)
    println(listzip01 zip listzip02)

    //集合并集
    println(listzip01.union(listzip02))
    //集合交集
    println(listzip01.intersect(listzip02));
    //集合差集
    println(listzip01.diff(listzip02))

    println("*****reduce******")
    val reduceList: List[Int] = List(1,2,3,4)
    //因为reduce本事k就是两个参数，所以参数可以写（x,y）
    println("*****reduce+******")
    val sum: Int = reduceList.reduce((x,y)=>x+y)
    println(sum)
    println("*****reduce-******")
    println(reduceList.reduce((x, y) => x - y))
    println("*****reduceleft+******")
    println(reduceList.reduceLeft((x, y) => x + y))  //1+2+3+4
    println("*****reduceleft-******")
    println(reduceList.reduceLeft((x, y) => x - y)) //（（（1-2）-3）-4）
    println("*****reduceright+******")
    println(reduceList.reduceRight((x, y) => x + y))
    println("*****reduceright-******") //right
    println(reduceList.reduceRight((x, y) => x - y)) //4321 （1-（2-（3-4）））=-2

    println("*****fold+******")
    println(reduceList.fold(5)((x, y) => x + y))
    println("*****fold-******")
    println(reduceList.fold(5)((x, y) => x - y))

    println("*****flatmap-******")
    val names = List("Alice", "Bob", "Nick")
    def upper( s : String ) : String = {
      s. toUpperCase
    }
    println(names.flatMap(upper))








  }

}
