package sparkContext.RDD.mysql操作

import java.sql.DriverManager

import org.apache.spark.rdd.JdbcRDD
import org.apache.spark.{SparkConf, SparkContext}

object SelectMysql {

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

    //创建jdbcRDD
    val jdbcRDD: JdbcRDD[Unit] = new JdbcRDD(sc, () => { //返回connection
      Class.forName(driver)
      DriverManager.getConnection(url, userName, passWd)
    },
      "select username,age from user where id>=? and id<=?",
      1,  // 下限
      2,    //上县
      2,  //分区数
      x =>   //结果集操作
        println(x.getString(1) + "," + x.getInt(2))
    )
    jdbcRDD.collect()

    //释放资源
    sc.stop();
  }

}
