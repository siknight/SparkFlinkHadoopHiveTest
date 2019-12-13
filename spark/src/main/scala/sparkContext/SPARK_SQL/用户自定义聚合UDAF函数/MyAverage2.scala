package sparkContext.SPARK_SQL.用户自定义聚合UDAF函数

import org.apache.spark.SparkConf
import org.apache.spark.sql._
import org.apache.spark.sql.expressions.Aggregator


/**
  *     强类型
  *  第二种方式
  * 声明用户自定义聚合函数
  *   1.继承Aggregate
  *   2.实现方法
  */
case class UserBean(var name:String,var age:BigInt)
case class AvgBuffer(var sum:BigInt,var count:Int)

/**
  * 声明用户自定义函数（强类型）
  *   1）继承Aggregator
  *   2）实现方法
  */
class MyAverage2 extends Aggregator[UserBean,AvgBuffer,Double] {
  //初始化
  override def zero: AvgBuffer = {
    AvgBuffer(0,0)
  }

  /**
    * 聚合函数
    * @param b
    * @param a
    * @return
    */
  override def reduce(b: AvgBuffer, a: UserBean): AvgBuffer = {
    b.sum =b.sum + a.age
    b.count =b.count +1
    b
  }
  //缓冲区合并操作
  override def merge(b1: AvgBuffer, b2: AvgBuffer): AvgBuffer = {
    b1.sum = b1.sum +b2.sum
    b1.count =b1.count + b2.count
    b1
  }

  override def finish(reduction: AvgBuffer): Double = {
    reduction.sum.toDouble / reduction.count
  }

  override def bufferEncoder: Encoder[AvgBuffer] = Encoders.product

  override def outputEncoder: Encoder[Double] = Encoders.scalaDouble
}

object TestUDAF2{
  def main(args: Array[String]): Unit = {
    val conf: SparkConf =
      new SparkConf().setAppName("spark sql").setMaster("local")
    //创建sparksession
    //创建SparkSQL的环境对象
    val spark: SparkSession = SparkSession.builder().config(conf).getOrCreate()

    import  spark.implicits._
    //自定义聚合函数
    //创建聚合函数对象
    val udaf = new MyAverage2
    //将聚合函数转换为查询列
    val avgCol: TypedColumn[UserBean, Double] = udaf.toColumn.name("avgAge")

    val frame: DataFrame = spark.read.json("in/json/user.json")

    val userDS: Dataset[UserBean] = frame.as[UserBean]
    //应用函数
    userDS.select(avgCol).show()


    //释放资源
    spark.stop()

  }
}
