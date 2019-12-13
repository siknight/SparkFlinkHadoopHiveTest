package sparkContext.SPARK_RDD.mysqlJDBC操作

import java.sql.{Connection, DriverManager, PreparedStatement}

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object WriteMysql {

  def main(args: Array[String]): Unit = {

    //创建Rdd配置
    val conf: SparkConf =
      new SparkConf().setAppName("rddcreate").setMaster("local")

    //2.创建SparkContext
    val sc = new SparkContext(conf)

    //3.定义连接mysql的参数
    val driver = "com.mysql.jdbc.Driver"
    val url = "jdbc:mysql://localhost:3306/rdd"
    val userName = "root"
    val passWd = "jiang"

    val rdd: RDD[(String,Int)] = sc.makeRDD(List(("hehe",22),("jiang",20),("xiang",24)))

    rdd.foreachPartition(x=>{

      //写基本和java一样
      val conn: Connection = DriverManager.getConnection(url, userName, passWd)
      x.foreach(y=>{
          Class.forName(driver)

          val ps: PreparedStatement = conn.prepareStatement("insert into user(username,age) values(?,?)")
          ps.setString(1,y._1)
          ps.setInt(2,y._2)

          val insert: Int = ps.executeUpdate()

          ps.close()

       }
    )
    })


  }

}
