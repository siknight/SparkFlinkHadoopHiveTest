package sparkContext.SPARK_SQL.文件读取和保存

import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, SparkSession}

/**
  *
  */
object WriteFileTest {
  def main(args: Array[String]): Unit = {
    //1.创建环境
    val conf: SparkConf =
      new SparkConf().setAppName("spark sql").setMaster("local")
    val spark: SparkSession = SparkSession.builder().config(conf).getOrCreate()

    //2.处理
    val sc: RDD[(String, Int)] = spark.sparkContext.makeRDD(List(("lisi",22),("jiang",20)))

    import  spark.implicits._
    val df: DataFrame = sc.toDF("name","age")
    df.show()
    //保存
    /**
      * "error"(default)	如果文件存在，则报错
      * "append"	追加
      * ""overwrite"	覆写
      * "ignore"	数据存在，则忽略
      *
      */
    df.write.mode("append").json("out/spark/sql/json")


  }

}
