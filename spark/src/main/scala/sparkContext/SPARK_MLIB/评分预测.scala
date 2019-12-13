package sparkContext.SPARK_MLIB

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.mllib.recommendation.{ALS, MatrixFactorizationModel, Rating}

/**
  * MLlib(Machine Learnig lib) 是Spark对常用的机器学习算法的实现库
  *
  *   协同过滤算法
  *
  *   MLlib是Spark实现一些常见的机器学习算法和实用程序，
  *   包括分类、回归、聚类、协同过滤、降维以及底层优化，该算法可以进行可扩充
  *
  *   MLlib支持本地的密集向量和稀疏向量，并且支持标量向量。
  *
  */
object 评分预测 {
  var conf: SparkConf = _
  var sc: SparkContext = _
  //我们拿训练好的模型进行预测：用户ID=1，产品ID=2的评分   用户id,产品id，评分
//  var data: Array[String] = Array("1,1,1.0", "1,2,2.0", "1,3,4.5", "2,3,4.0", "2,4,5.0")
  var data: Array[String] = Array("1,1,1.0","1,2,2.0","1,3,4.5","2,3,4.0", "2,4,5.0")
  var dataRDD: RDD[(Int, Int, Float)] = _
  var ratings: RDD[Rating] = _
  var model: MatrixFactorizationModel = _

  //rdd环境搭建
  def init: Unit = {
    conf = new SparkConf().setAppName("spark als demo").setMaster("local")
    sc = new SparkContext(conf)
  }

  /**
    * 处理数据，生成RDD
    */
  def makeRdd: Unit = {
    //将原始数据拆开组成元组
    val value: RDD[(Int, Int, Float)] = sc.parallelize(data).map {
      //先将Array("1,1,1.0", "1,2,2.0", "1,3,4.5", "2,3,4.0", "2,4,5.0")按，拆分
      x =>
        val lines = x.split(",");
        //将拆分数据按元组返回
        (lines(0).toInt, lines(1).toInt, lines(2).toFloat)
    }
    //赋值
    dataRDD=value

     //转为rating存储
      val ratingValue: RDD[Rating] = dataRDD.map(x => Rating(x._1, x._2, x._3))
      ratings = ratingValue
  }

  /**
    * 训练
    */
  def trainModel: Unit = {
    val rank = 10 // 向量大小，默认10
    val iterations = 10 // 迭代次数，默认10
    model = ALS.train(ratings, rank, iterations)
  }


  def main(args: Array[String]): Unit = {
    // 初始化
    init
    // 生成RDD
    makeRdd
    // 训练模型
    trainModel
   //user product
    val result: Double = model.predict(1,2)

    println("预测评分：" + result)
  }

}
