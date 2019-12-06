package scalatest.Actor通信模型

import scala.actors.Actor


/**
  * Actor是通信模型，Spark根据节点之间的通信
  * 就是使用的Akka，Akka是通信模型，Akka就是Actor实现的
  * Actor相当于我们理解的线程，Thread
  *
  *
  * 需要引用scala-actors包
  */

class MyActor  extends Actor{
  override def act(): Unit= {
    receive{   //用于接收客户端发送的消息,这个方法名字不能变
      case s:String=>println(s)
      case _=>println("default")
    }

  }
}

object  actorClient{
  def main(args: Array[String]): Unit = {
    val actor: MyActor = new MyActor
    actor.start();
    actor! "hello" //给actor发消息
  }
}


