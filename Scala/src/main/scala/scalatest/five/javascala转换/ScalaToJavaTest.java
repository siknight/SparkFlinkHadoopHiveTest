package scalatest.five.javascala转换;

import org.junit.jupiter.api.Test;

/**
 * java调用scala,也是直接调用
 *对于object修饰的类里的方法相当于静态方法
 * 可以用类名直接调用
 * 用class修饰的类里的方法要用new 类名（）定义，
 * 然后调用对象方法
 */
public class ScalaToJavaTest {

    @Test
    public  void ScalaTest() {
        ScalaHelloWorld.Hello();
       System.out.println("-----");

        System.out.println("-----");
       new SayHi().sayHi();


    }
}
