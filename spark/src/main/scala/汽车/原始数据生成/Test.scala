package 汽车.原始数据生成

object Test {

  def main(args: Array[String]): Unit = {



    //下单性别
    for (i <- 1 to 20){
      println(Util.listResultData(Initdata.sex))
    }
  }

}
