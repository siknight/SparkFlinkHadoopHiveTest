package 汽车

import 汽车.大数据处理.{AdressAmount, CarSales, ColorAmount, SexAmount}

/**
  * 数据处理后，导入到数据库
  *
  *
  */
object MainCarExecutor {
  def main(args: Array[String]): Unit = {
    /**
      * 1.生成mysql汽车表
      *   local[*] yanr standand
      * readFilePosition   读取的文件位置  "in/carData/source/carSource.csv"
      *  mysqlUsername   mysql用户名  root
      *  MysqlPassword   mysql 密码  jiang
      * sparkSaveMode   spark保存模式  append overwrite
      *  JDBCUrl     jdbc url   "jdbc:mysql://localhost:3306/bigdatacar"
      * tableName  表名   "carsSale"
      */
    CarSales. carsDataToMysql("local[*]","in/carData/source",
                "root","jiang","overwrite",
                "jdbc:mysql://localhost:3306/bigdatacar","carsSale")


    /**
      * 2.生成性别表 参数含义和上面的相同
      *   表名：sexYearAmount
      */
   SexAmount.perSexAmount("local[*]","in/carData/source",
      "root","jiang","overwrite",
      "jdbc:mysql://localhost:3306/bigdatacar","sexYearAmount")

    /**
      * 3.生成地区表
      */
    AdressAmount.perAdressAmount("local[*]","in/carData/source",
      "root","jiang","overwrite",
      "jdbc:mysql://localhost:3306/bigdatacar","adressYearAmount")

    /**
      * 4.生成汽车color表
      *
      */
    ColorAmount.perColorAmount("local[*]","in/carData/source",
      "root","jiang","overwrite",
      "jdbc:mysql://localhost:3306/bigdatacar","colorYearAmount")


  }
}
