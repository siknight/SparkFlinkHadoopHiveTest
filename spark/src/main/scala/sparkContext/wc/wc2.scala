package sparkContext.wc

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object wc2 {
  def main(args: Array[String]): Unit = {
    //1. 创建环境
    val conf: SparkConf = new SparkConf().setMaster("local[1]").setAppName("test01")
    val sc: SparkContext = new SparkContext(conf)
    //2.读取数据
    /**
      * hello world
      * hello scala
      * hello spark
      */
    val lines: RDD[String] = sc.textFile("in/word1")
    //每一行为数组的一个元素，因为有三行数据，所以数组的大小为3
    val linesStr: Array[String] = lines.collect()
    println("linesSize="+linesStr.size+",lines="+linesStr.mkString(","))
    //每一个单词为数组中的一个元素
    val words: RDD[String] = lines.flatMap(_.split(" "))
    val wordsStr: Array[String] = words.collect()
    println("wordsSize="+wordsStr.size+",wordsStr="+wordsStr.mkString(","))

    val wcmap: RDD[(String, Int)] = words.map((_,1))
    val wcmapStr: Array[(String, Int)] = wcmap.collect()
    println("wcmapStr="+wcmapStr.size+",wcmapStr="+wcmapStr.mkString(","));

    val wcgroup: RDD[(String, Iterable[Int])] = wcmap.groupByKey()
    val wcgroupStr: Array[(String, Iterable[Int])] = wcgroup.collect()
    println("wcgroupSize="+wcgroupStr.size+",wcgroup="+wcgroupStr.mkString(","))


    val wccount: RDD[(String, Int)] = wcmap.reduceByKey((x, y)=>x+y)
    val wccountStr: Array[(String, Int)] = wccount.collect()
    println("wccountSize="+wccountStr.size+",wccountSize="+wccountStr.mkString(","))
    //    val wcsort: RDD[(String, Int)] = wccount.sortBy(t=>t._1,false)
    //默认情况下是按照value从大到小排，false是从小到大排列
     val wcsort: RDD[(String, Int)] = wccount.sortByKey(true)
    wcsort.foreach(t=>println(t._1+" "+t._2))
//    wcsort.saveAsTextFile("out/spark/test02")



  }

}
