package 汽车




import 汽车.原始数据生成.{DataSourceCreate, Initdata, Util}

/**
  *
  * 生成原始数据
  */
//数据格式（中间用,隔开）： id,下单月份,性别,购买地点,车型,价格,颜色
object 汽车数据生成 {


  def main(args: Array[String]): Unit = {
    //(下标范围，保存路径)  hdfs://hadoop102:/out/carsSources
    DataSourceCreate.dataCreate(1 to 500000,"out/carsSources")

  }





}
