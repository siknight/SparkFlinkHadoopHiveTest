package sparkContext.wc

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object wordCount {
  def main(args: Array[String]): Unit = {
    //使用开发工具完成Spark WordCount的开发

    //local模式
    //创建SparkConf对象
    //设定Spark技术框架的（部署）环境
   val conf:SparkConf = new SparkConf().setMaster("local[*]").setAppName("sparkContext/wc")
    val sc = new SparkContext(conf)
    //创建spark上下文对象
    val lines: RDD[String] = sc.textFile("in")
    //将一行一行的数据分解为一个一个的单词
    val words: RDD[String] = lines.flatMap(_.split(" "))
    //为了统计方便，将单词数据进行结构的转换
    val wordToOne: RDD[(String, Int)] = words.map((_, 1))
    //对转换结构的数据进行分组聚合
    val wordToSum: RDD[(String, Int)] = wordToOne.reduceByKey(_+_)
//    将统计结果打印到控制台
    val result: Array[(String, Int)] = wordToSum.collect()

    //打印结果
    println(result);
    println("---------")
    //循环打印
    result.foreach(println)

  }

}
