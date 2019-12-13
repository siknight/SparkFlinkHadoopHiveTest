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
    * @return  随机返回哪个月
    */
  def  monthRandom(size:Int):Int ={
    (Math.random()*size+1).toInt
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
    * 循环次数
    * @param number
    */
  def forNumber(number:Int): Unit ={

  }


}
