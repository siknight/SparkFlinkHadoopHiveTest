package sparkContext.SPARK_SQL.用户自定义聚合UDAF函数

import org.apache.spark.SparkConf
import org.apache.spark.sql.{DataFrame, Row, SparkSession}
import org.apache.spark.sql.expressions.{MutableAggregationBuffer, UserDefinedAggregateFunction}
import org.apache.spark.sql.types.{DataType, DoubleType, LongType, StructType}

/**
  *   弱类型
  * 声明用户自定义聚合函数
  *   1.继承UserDefinedAggregateFunction
  *   2.实现方法
  */

class MyAverage extends UserDefinedAggregateFunction {
  //函数输入的数据结构,设置输入类型
  override def inputSchema: StructType = {
    new StructType().add("age",LongType)
  }

  //计算时的数据结构，sum用来+，count用来计数
  override def bufferSchema: StructType = {
    new StructType().add("sum",LongType).add("count",LongType)
  }

  //函数返回的数据类型
  override def dataType: DataType = DoubleType

  //函数是否稳定
  override def deterministic: Boolean = true
  //计算之前的缓冲区的初始化
  //
  override def initialize(buffer: MutableAggregationBuffer): Unit = {
    buffer(0) = 0L  //sum缓冲区 sum初始
    buffer(1) = 0L  //count缓冲区 count初始
  }

  //根据查询结果更新缓存区数据
  override def update(buffer: MutableAggregationBuffer, input: Row): Unit = {
    buffer(0) =buffer.getLong(0) + input.getLong(0)  //sum
    buffer(1) =buffer.getLong(1) +1 //count
  }

  //将多个节点的缓冲区合并
  override def merge(buffer1: MutableAggregationBuffer, buffer2: Row): Unit = {
    //sum
    buffer1(0) =buffer1.getLong(0) + buffer2.getLong(0) //sum
    //count
    buffer1(1) =buffer1.getLong(1) + buffer2.getLong(1) //count

  }
  //计算
  override def evaluate(buffer: Row): Any = {
    buffer.getLong(0).toDouble / buffer.getLong(1)  //sum/count
  }
}

object TestUDAF{
  def main(args: Array[String]): Unit = {
    val conf: SparkConf =
      new SparkConf().setAppName("spark sql").setMaster("local")
    //创建sparksession
    //创建SparkSQL的环境对象
    val spark: SparkSession = SparkSession.builder().config(conf).getOrCreate()

    import  spark.implicits._
    //自定义聚合函数
    //创建聚合函数对象
    val udaf = new MyAverage
    //注册聚合函数
    spark.udf.register("avgage",udaf)
    //使用聚合函数
    val df: DataFrame = spark.read.json("in/json/user.json")

    df.createOrReplaceTempView("user")

    spark.sql("select avgage(age) from user").show()

    //释放资源
    spark.stop()

  }
}
