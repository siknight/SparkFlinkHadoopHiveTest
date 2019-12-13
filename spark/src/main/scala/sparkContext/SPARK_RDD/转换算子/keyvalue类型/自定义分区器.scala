package sparkContext.SPARK_RDD.转换算子.keyvalue类型

import org.apache.spark.Partitioner

/**
  * 自定义分区器，应用于groupBy
  */
class 自定义分区器(partitions: Int)  extends Partitioner{
  override def numPartitions: Int ={  //分区数
    partitions
  }

  override def getPartition(key: Any): Int = {    //将数据放在哪个分区
    0
  }
}
