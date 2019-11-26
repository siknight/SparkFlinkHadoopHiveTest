package zookeeper.watchDemo;

import org.apache.zookeeper.*;

import java.io.IOException;

/**
 * 这个类很单纯，就是用来在/fuxi的Znode节点里创建子节点，然后给WatchClient类监听
 *   先启动WatchClient类，然后在启动 此类
 */

public class WatchServer {

    private static String server="hadoop102:2181";
    private static  String nodePath="/servers";
    private static ZooKeeper zooKeeper=null;

    /**
     * 运行main方法
     * @param args
     * @throws IOException
     * @throws KeeperException
     * @throws InterruptedException
     */
    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        getconn();
        createNode();
    }



    /**
     * 创建zookeeper客户端，连接服务器
     * @throws IOException
     */
    private static void getconn() throws IOException {
        zooKeeper=new ZooKeeper(server, 2000, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                System.out.println("server="+watchedEvent.getType()+"--"+watchedEvent.getPath());
            }
        });
    }

    /**
     * 创造Znode节点
     * @throws KeeperException
     * @throws InterruptedException
     */
    private static  void createNode() throws KeeperException, InterruptedException {
        zooKeeper.create("/fuxi/lisi02","fuxi_lisi02".getBytes(),ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
        System.out.println("创建完毕");
    }


}
