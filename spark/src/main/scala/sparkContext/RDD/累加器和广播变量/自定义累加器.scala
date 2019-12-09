package sparkContext.RDD.累加器和广播变量

import java.util

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.util.AccumulatorV2

object 自定义累加器 {

  def main(args: Array[String]): Unit = {
    //创建Rdd基本环境
    val conf: SparkConf = new SparkConf().setAppName("rddcreate").setMaster("local")
    val sc: SparkContext = new SparkContext(conf)

    val rdd: RDD[String] =
         sc.makeRDD(List("hbase","hive","hbase","scala","spark"),2)

    //创建累加器
    val accumumator: wordAccumumator = new wordAccumumator

    //注册累加器
    sc.register(accumumator)

    rdd.foreach(word=>{
      accumumator.add(word)
    })


    //获取累加器的值
    println(accumumator.value)

    sc.stop();









  }
}

/**
  * 申明累加器
  *   1.继承AccumulatorV2
  *   2.继承抽象方法
  */
class wordAccumumator extends AccumulatorV2[String,java.util.ArrayList[String]]{
  val list = new util.ArrayList[String]()
  //当前的累加器是否为初始状态
  override def isZero: Boolean = {
    list.isEmpty
  }
  //复制累加器对象
  override def copy(): AccumulatorV2[String, util.ArrayList[String]] ={
    new wordAccumumator()
  }

  //重置累加器对象
  override def reset(): Unit = {
    list.clear()
  }

  //向累加器增加数据
  override def add(v: String): Unit = {
    if (v.contains("h")){
      list.add(v)
    }
  }

  //合并累加器
  override def merge(other: AccumulatorV2[String, util.ArrayList[String]]): Unit = {
    list.addAll(other.value)
  }

  //获取累加器的结果
  override def value: util.ArrayList[String] = list
}
