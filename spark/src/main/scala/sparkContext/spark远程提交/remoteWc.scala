package sparkContext.spark远程提交

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  * 2.1）本地情况，conf = SparkConf().setMaster("local[*]") ——>也就是拿本机的spark来跑程序
  * 2.2）远程情况，conf = SparkConf().setMaster("spark://hadoop102:7077") ——>远程spark主机
  * 2.3）yarn情况，conf = SparkConf().setMaster("yarn-client") ——>远程或本地 yarn集群代理
  */

object remoteWc {
  def main(args: Array[String]): Unit = {
    //1. 创建环境 yarn模式
    val conf: SparkConf = new SparkConf().setMaster("yarn-client").setAppName("test01")
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
