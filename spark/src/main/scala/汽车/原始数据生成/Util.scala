package 汽车.原始数据生成

/**
  * 工具类
  *   方法封装
  */
object Util {
  /**
    *集合下标随机生成方法
    * @param seq 集合
    * @return 返回集合的随机下标
    */
  def  listRandom(seq:List[Any]):Int ={
    (Math.random()*seq.size).toInt
  }

  /**
    *   用处：随机返回一个月
    * @param size 多少个月份，比如：如果为一年就填参数12，6个月就填6
    *  @param   step 为1 表示选1-12月 为2表示选2-12月 依次类推
    * @return  随机返回哪个月
    */
  def  monthRandom(size:Int,step:Int):Int ={
    (Math.random()*size+step).toInt
  }

  /**
    *    用处：随机返回一个集合的元素
    * @param seq  集合
    * @return 随机返回一个集合的元素
    */
  def listResultData(seq:List[Any])={
    seq(listRandom(seq))
  }

  /**
    * 随机选择哪个车递增或者递减
    */

  def selectOneCar ={
    val oneCar: (String, Int) = listResultData(Initdata.cars).asInstanceOf[(String,Int)]
    val onecarName: String = oneCar._1
    onecarName
  }



}
