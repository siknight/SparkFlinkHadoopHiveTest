package com.bjsxt.scala

/**
 * 1.scala中定义在object中的变量，方法都是静态的,object叫对象，相当于java中的单例对象。object不可以传参,Trait也不可传参。
 * 2.scala 中一行代码后可以写“;”也可以不写，会有分号推断机制。多行代码写在一行要用分号隔开。
 * 3.定义变量用var,定义常量用val ， a: Int ": Int" 是变量的类型，可以写也可以不写，不写会自动推断变量类型 
 * 4.scala中变量，类，对象，方法定义建议符合驼峰命名法。
 * 5.class 是scala中的类,类可以传参，传参就有了默认的构造函数。类中的属性默认就有getter，setter方法。重写构造，第一行要调用默认的构造
 * 6.当new 一个class时，类中除了方法不执行，其他都执行。同一个包下，class的名称不能相同。
 * 7.scala中 如果一个class名称和object的名称一致，那么这个class叫做这个object的伴生类，这个object叫做这个class的伴生对象，他们之间可以互相访问私有变量。
 */

class Person(xname:String,xage:Int){
  private val name = xname
  var age = xage
  var gender = 'm'
  
//  println("************************")
  
  /**
   * 重写构造
   */
  def this(yname:String,yage:Int,ygender:Char){
    this(yname,yage)
    this.gender = ygender 
  }
  
  def showHello() ={
    println("hello world")
  }
  
  
}


object Person {
//	println("=====================")
  
//  val score = 200
  
  
  def main(args: Array[String]): Unit = {
    
	  /**
	   * if ... else
	   */
//	  val a = 100
//	  if(a<50){
//	    println("a 小于50")
//	  }else if(a>=50 && a<=100){
//		  println("50<=a<=100")
//	    
//	  }else{
//		  println("a>100")
//	  }
    
    /**
     * for
     * 
     * 1 to 10 这种表示是scala中的操作符操作。
     * 步长。
     */
	  
//    println(1.to(10,2))
//    println(1 until (10,3))//1,4,7
    
//    for(i <- 1 to 10 ){
//      for(j <- 1 to 10){
//        println("i = "+i+" j="+j)
//      }
//    }
    
    /**
     * 小九九
     */
//    for(i <- 1 to 9;j <- 1 to 10){
//      if(i>=j){
//        
//    	  print(i+"*"+j+"="+i*j+"\t")
//      }
//      if(i==j){
//        println()
//      }
//    }
    
//    for(i <- 1 to 100 if(i%2==0) if(i==98)){
//      println(i)
//    }
    
//		 val result =  for(i<- 1 to 1000 if(i%10==0)) yield i
//	   println(res/ult)
//		 result.foreach(a=>{
//		   println(a)
//		 })
//		 
		 
		 
//    val person = new Person("zhangsan",18)
//    
//    println(person.name)
    
//    person.age = 100
//    println(person.gender)
//    
//    val p1 = new Person("diaochan",20,'f')
//    println(p1.gender)
//    p1.showHello()
    
    /**
     * 变量 常量
     */
//    var b = 20
//    val a = 100
//    println(a)
    
    
  }
}