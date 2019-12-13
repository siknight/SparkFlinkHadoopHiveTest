package sparkContext.SPARK_RDD.累加器和广播变量

import org.apache.spark.rdd.RDD
import org.apache.spark.util.LongAccumulator
import org.apache.spark.{SparkConf, SparkContext}

/**
  * 系统累加器  accumulate
  */
object AccumulateTest {
  def main(args: Array[String]): Unit = {
    //创建Rdd基本环境
    val conf: SparkConf = new SparkConf().setAppName("rddcreate").setMaster("local")
    val sc: SparkContext = new SparkContext(conf)

    val rdd: RDD[Int] = sc.makeRDD(List(1,2,3,4),2)


    //因为sum在driver上，foreach在exector
    var sum =0;
    rdd.foreach(x=>sum=sum+x)
    println(sum)  //sum =0

    //累加器
    println("******累加器*****")
    //通过累加器来共享变量，来累加数据
    //创建累加器对象
    val accumulator: LongAccumulator = sc.longAccumulator
    rdd.foreach(x=>{
      // 创建累加器对象
      accumulator.add(x)
    })

    println("元素个数="+accumulator.count)
    //获取累加器的值
    println(accumulator.value)

    //释放资源
    sc.stop()

  }

}
