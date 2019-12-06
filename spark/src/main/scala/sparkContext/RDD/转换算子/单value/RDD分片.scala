package sparkContext.RDD.转换算子.单value

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  *   MakeRDD（）方法
    def makeRDD[T: ClassTag](
      seq: Seq[T],
       numSlices: Int = defaultParallelism): RDD[T] = withScope { //这里是默认并行度，注意和textFile区别
      parallelize(seq, numSlices)
     }
  *
  * local（并行度，核数）为1时
      *     Got job 0 (saveAsTextFile at RDD分片.scala:14) with 1 output partitions
      *  Adding task set 0.0 with 1 tasks
      *    Submitting 1 missing tasks from ResultStage 0 (MapPartitionsRDD[1] at saveAsTextFile
      *  Running task 0.0 in stage 0.0 (TID 0)
      *   Finished task 0.0 in stage 0.0 (TID 0). 1012 bytes result sent to driver
      *  Finished task 0.0 in stage 0.0 (TID 0) in 411 ms on localhost (executor driver) (1/1)
      *   Removed TaskSet 0.0, whose tasks have all completed, from pool
  *
  *
  *   分片数（并行度，核数）为3时
  *   Got job 0 (saveAsTextFile at RDD分片.scala:26) with 3 output partitions
  *      Submitting 3 missing tasks from ResultStage 0 (MapPartitionsRDD[1] at saveAsTextFile at RDD分片
  *   Adding task set 0.0 with 3 tasks
  *
  *      TODO 开启了三个task
  *    Starting task 0.0 in stage 0.0 (TID 0, localhost, executor driver, partition 0, PROCESS_LOCAL, 5892 bytes)
  *      Finished task 0.0 in stage 0.0 (TID 0). 1012 bytes result sent to driver
  *   Starting task 1.0 in stage 0.0 (TID 1, localhost, executor driver, partition 1, PROCESS_LOCAL, 5892 bytes)
  *       Finished task 1.0 in stage 0.0 (TID 1). 925 bytes result sent to driver
  *  Starting task 2.0 in stage 0.0 (TID 2, localhost, executor driver, partition 2, PROCESS_LOCAL, 5896 bytes)
  *     Finished task 2.0 in stage 0.0 (TID 2) in 59 ms on localhost (executor driver)
  *
  *     以上结果可以知道三个并行度会开启一个job，三个task，三个partition（默认都是从0开始）
  *     和hadoop异同。hadoop有多少个分片就有多少个maptask，每一个mapper类就是一个maptask。
  *       hadoop的inputformat分片机制取决于文件得个数和每一个文件的大小（0-128M）
  *
  *   总结：分片数=并行度=当前程序设置的核心数=task个数=分区数=输出文件个数
  *
  *  textFile（）方法
     def textFile(
        path: String,
        minPartitions: Int = defaultMinPartitions  //这个参数是最小分区数，注意和makeRDD区别
     )
      : RDD[String] = withScope {
      assertNotStopped()
      hadoopFile(path, classOf[TextInputFormat], classOf[LongWritable], classOf[Text],
        minPartitions).map(pair => pair._2.toString).setName(path)   //这里说明还要取决于hadoopFile的分片机制
                                                                    //主要是minPartitions为2时会有区别
  }

  *运行日志：
    默认并行度：
    def defaultMinPartitions: Int = math.min(defaultParallelism, 2)
           defaultParallelism这个就是makeRDD里的默认并行度，但是local[*]设置大于2时，默认取2
     override def defaultParallelism(): Int =
      scheduler.conf.getInt("spark.default.parallelism", totalCores)
  *  一个并行度
  *   submitting 1 missing tasks from ResultStage 0 (MapPartitionsRDD[3] at saveAsTextFil
  *   Adding task set 0.0 with 1 tasks
  *   Starting task 0.0 in stage 0.0 (TID 0, localhost, executor driver, partition 0, PROCESS_LOCAL, 6000 bytes)
  *    Finished task 0.0 in stage 0.0 (TID 0). 1245 bytes result sent to driver
  *
  *  三个并行度(和MakeRDD一样，这里省略)
  *    Submitting 3 missing tasks from ResultStage 0 (MapPartitionsRDD[3] at saveAs
  *
  *
  */

object RDD分片 {
  def main(args: Array[String]): Unit = {
    //创建Rdd基本环境
    val conf: SparkConf = new SparkConf().setAppName("rddcreate").setMaster("local")
    val sc: SparkContext = new SparkContext(conf)
    //从集合（内存）中创建
    //设置分片数
    val rddArray: RDD[Int] = sc.makeRDD(Array(1,2,3,4))

//    rddArray.saveAsTextFile("out/hadoop/rdd02")


    //从外部文件中创建中创建
    //读取文件时，传递的分区参数为最小分区数，但是不一定
    //就是这个分区数，取决于hadoop读取文件时分区规则
    val lines: RDD[String] = sc.textFile("in/two",1)
    //coalesce(1) 只会输出一个文件，reduce
    lines.coalesce(1).saveAsTextFile("out/hadoop/lines06")

  }

}
