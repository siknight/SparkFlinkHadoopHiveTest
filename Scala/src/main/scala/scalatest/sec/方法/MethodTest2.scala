package scalatest.sec.方法

object MethodTest2 {

  def main(args: Array[String]): Unit = {
    /**
      * 下面是函数做为返回值
      */
    println("***1****")
    // 声明方法
    def f1(): Unit ={
      println("aa")
    }

    def f2() ={
      //返回函数
      //因为f1方法返回值是Unit的，所以直接写f1是直接把执行的结果返回了
//      f1
      //直接返回函数 在函数名后加_，加上后返回值是（）=> Unit
      f1 _
    }
    //当函数f2返回值是Unit时,直接写一个括号就行
    println("***1.1****")
    f2()
    //直接返回函数时，需要下面这样写
    println("***1.2****")
    f2()();

    println("***2****")
    //TODO 闭包
    //闭包是一个函数在实现过程中，将外部的变量引入到函数的内容
    //改变了这个变量的生命周期，称为闭包

    def f3(i:Int )={
      def f4(j:Int)={
        i*j   //闭包
      }
      f4 _;
    }
    println(f3(3)(4))

    println("***3****")
    //TODO 函数柯里化，只要有柯里化肯定会有闭包
    def f5(i:Int)(j:Int)={
      i*j
    }
    println(f5(10)(20))

    /**
      * 下面是函数作为参数
      */
    println("***函数作为参数***")
    //将函数作为参数传递给另一个函数，需要采用特殊的声明方式
    //()=>Unit
    //格式：参数列表=>返回值类型
    def f6(f:()=>(Int))={
      f()+5;
    }
    def aa()={
      5;
    }
    println(f6(aa))

    println("使用匿名函数作为改善上述代码")
    def f7(f:()=>Unit) ={
        f()
    }
    f7(()=>{println("aa hello")})
    //省略
    f7(()=>println("bb hello"))
    //省略

    def f8(f:(Int)=>Unit) ={
      f(10)
    }
    f8((i:Int)=>println(i))
    //因为自动推断，所以可以省略
    f8((i)=>println(i))
    //继续省略，只有一个参数时可以省略
    f8(println(_))
    //只有一个参数可以省略
    f8(println)

    def f9(f:(Int,Int)=>Int)={
      f(10,20)
    }
    println("双参数")
    println(f9((i:Int,j:Int)=>{i+j}))
    println(f9((i,j)=>i+j))
    //参数只用一次是可以省略的
    println(f9(_+_))


  }


}
