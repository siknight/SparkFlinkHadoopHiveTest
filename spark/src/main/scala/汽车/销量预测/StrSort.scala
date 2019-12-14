package 汽车.销量预测

object StrSort {

  def main(args: Array[String]): Unit = {
    sort("12:1373,8:1391,4:1392,11:1372,9:1381,5:1437,10:1313,6:1415,1:1434,2:1352,7:1404,3:1388,")
  }
//数据库数据样式：12:1373,8:1391,4:1392,11:1372,9:1381,5:1437,10:1313,6:1415,1:1434,2:1352,7:1404,3:1388,
  def sort(str:String) ={

    val arrayStr: Array[String] = str.split(",")
    val strList: List[String] = arrayStr.toList
    //排序，从小到大排列
    val sortStr: List[String] = strList.sortWith((x1, x2) => {
      val s1: String = x1.split(":")(0)
      val s2: String = x2.split(":")(0)
      s1.toInt<s2.toInt
    })

    val sortResult: String = sortStr.mkString(",")

    sortResult

  }

}
