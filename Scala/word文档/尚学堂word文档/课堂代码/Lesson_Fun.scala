package com.bjsxt.scala

import java.util.Date

object Lesson_Fun {
  def main(args: Array[String]): Unit = {
    /**
     * 1.方法定义
     * 
     *  1.方法定义用def,函数的参数 要写类型，不写类型不可以。
     *  2.函数的返回值类型可以不写，会自动推断
     *  3.scala会将函数体中最后一行计算的结果当做返回值返回
     *  4.可以写“return”,写了return要显式的声明方法体的放回类型。
     *  5.定义方法时，如果不写“=”，那么无论方法体中最后一行计算的结果返回是什么，都会被丢弃，返回Unit
     *  6.方法体可以一行搞定，那么方法体的“{... ...} ” 可以省略不写
     */
//    def max(x:Int,y:Int) = {
//      if(x>y){
//         x
//      }else{
//         y
//      }
//    }
//    
//    
//    println(max(100,2))
    
//    def max(x:Int,y:Int) = if(x>y) x else y 
//    
//    println(max(100,2))
    
    /**
     * 2.递归函数
     * 
     *   递归函数要显式的声明返回类型
     * 
     */
//    def fun(x:Int):Int = {
//      if(x==1){
//        1
//      }else{
//        x*fun(x-1)
//      }
//    }
//    println(fun(5))
    
    
    /**
     * 3.函数的参数有默认值
     */
    
//    def fun(x:Int=100,y:Int=200) = x+y
//    
//    
//    println(fun(y=80))
    
    /**
     * 4.可变长参数的函数
     */
    
//    def fun(s:String*)= {
//      s.foreach((elem:String)=>{
//        println(elem)
//      })
//    }
//    fun("hello","a","b","c")
    
    /**
     * 5.匿名函数
     *   1."=>"就是匿名函数
     *   2.匿名函数调用要赋值给一个变量，调用直接调用这个变量就可以
     *   3.匿名函数不能显式的写函数返回类型
     */
    
//    val fun: (Int, Int) => Int = (x:Int,y:Int)=>{
//      x+y
//    }
//    
//    
//    println(fun(10,20))

    /**
     * 6.偏应用函数
     * 
     * 	偏应用函数是一个表达式，将方法中不变的参数写上，变化的参数使用“_”表示，
      * 	下次直接调用这个偏应用表达式直接传入变化的参数就可以
     */
    
//    def showLog(date:Date,log:String) = {
//      println("date is "+date+",log is "+log)
//    }
//    
//    val date = new Date()
//    showLog(date,"a")
//    showLog(date,"b")
//    showLog(date,"c")
//    
//    val fun = showLog(date,_:String)
//    
//    fun("aaa")
//    fun("bbb")
//    fun("ccc")
    
    /**
     * 7.嵌套函数
     */
    
//    def fun(x:Int) = {
//      
//      def fun1(num:Int):Int = {
//    		  if(num==1){
//    			  1
//    		  }else{
//    			  num*fun1(num-1)
//    		  }
//      }
//      
//      fun1(x)
//      
//    }
//    
//    println(fun(5))
    
    /**
     * 8.高阶函数
     *  1.函数的参数是函数
     *  2.函数的返回是函数  --函数的返回是函数时，要显式声明函数的返回类型
     *  3.函数的参数和返回都是函数
     */
    //函数的参数是函数
    
//    def fun1(f:(Int,Int)=>Int,s:String):String ={
//      val result = f(100,200)
//      result +"~"+s
//    }
//    
//    val result = fun1((a:Int,b:Int)=>{a*b},"hello")
//    println(result)
    
    //函数的返回是函数
    
//    def fun(a:Int,b:Int) :(String,String)=>String = {
//      val result = a*b
//      def fun1(s:String,s1:String) :String= {
//        s+"@"+s1+"#"+result
//      }
//      fun1
//    }
//    
//    println(fun(10,20)("hello","world"))//hello@world#200
    
    //函数的参数和返回都是函数
//    def fun(f:(Int,Int)=>Int) :(String,String)=>String= {
//      
//      val result = f(8,9)
//      
//      def fun1(s1:String,s2:String):String ={
//        s1+"@"+s2+"$"+result
//      }
//      fun1
//      
//    }
//    
//    
//    println(fun((a:Int,b:Int)=>{a-b})("hello","world"))
//    
    
    /**
     * 9.柯里化函数  
     *  柯里化函数就是高阶函数的简化版
     */
//    def fun(a:Int,b:Int)(c:Int,d:Int) ={
//      a+b+c+d
//    }
//    
//   println( fun(1,2)(3,4))
    
  }
}


