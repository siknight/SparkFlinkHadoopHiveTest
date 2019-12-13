package sparkContext.SPARK_RDD.累加器和广播变量

import org.apache.spark.broadcast.Broadcast
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object 广播变量 {

  def main(args: Array[String]): Unit = {
    //创建Rdd基本环境
    val conf: SparkConf = new SparkConf().setAppName("rddcreate").setMaster("local")
    val sc: SparkContext = new SparkContext(conf)

    val rdd1: RDD[(Int, String)] = sc.makeRDD(List((1,"a"),(2,"b"),(3,"c")))

    //类似于joinRDD

    val list = List((1,1),(2,2),(3,3))
    //可以使用广播变量减少数据的传输
    //1.构建广播变量
   val broadcast: Broadcast[List[(Int, Int)]] = sc.broadcast(list)

    val resultRDD: RDD[(Int, (String, Any))] = rdd1.map {
      case (key, value) => {  //这个也可以用case直接分开
        var v2: Any = null
        for (t <- broadcast.value) {
          if (key == t._1) {
            v2 = t._2
          }
        }
        (key, (value, v2))
      }
    }
    resultRDD.foreach(println)

    //释放资源
    sc.stop()



  }

}
