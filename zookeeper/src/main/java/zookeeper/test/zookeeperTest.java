package zookeeper.test;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * zookeeper 基本练习
 *
 */

public class zookeeperTest {
    //zookeeper连接地址
    private static String connectString =
            "hadoop102:2181,hadoop103:2181,hadoop104:2181";
    //sessionTime
    private static int sessionTimeout = 2000;
    //zookeeper客户端
    private ZooKeeper zkClient = null;

    /**
     * 初始化
     * @throws IOException
     */
    @Before
    public void init() throws IOException {
        //获取zookeeper客户端
        zkClient=new ZooKeeper(connectString, sessionTimeout, new Watcher() {
            public void process(WatchedEvent watchedEvent) {
                //收到事件通知后的回调函数（用户的业务逻辑）
                System.out.println(watchedEvent.getType()+"--"+watchedEvent.getPath());
            }
        });
    }


    /**
     * 创建子节点
     */
    @Test
    public void createZnodeTest() throws KeeperException, InterruptedException {
        zkClient.create(
            "/fuxi/lisi03","lisi01hehe".getBytes(),
                        ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

    }

    /**
     * 查看节点状态
     * @throws KeeperException
     * @throws InterruptedException
     */
    @Test
    public void seeZnodeStatusTest() throws KeeperException, InterruptedException {
        Stat exists = zkClient.exists("/fuxi/lisi02", true);
        if (exists==null){
            System.out.println("节点不存在");
        }else{
            System.out.println("节点存在");
        }
    }


    /**
     * 查看节点data
     * @throws KeeperException
     * @throws InterruptedException
     */
    @Test
    public void getZnodeDataTest() throws KeeperException, InterruptedException {
        byte[] data = zkClient.getData("/fuxi/lisi01", false, null);
        System.out.println(new String(data));
    }

    /**
     * 查看子节点
     * @throws KeeperException
     * @throws InterruptedException
     */
    @Test
    public void getZnodeChildrenTest() throws KeeperException, InterruptedException {
        List<String> children = zkClient.getChildren("/fuxi", false);
        for (String child : children){
            System.out.println("child="+child);
        }
    }


    /**
     * 改变节点内容
     * @throws KeeperException
     * @throws InterruptedException
     */
    @Test
    public void updateZnodeTest() throws KeeperException, InterruptedException {
        Stat stat = zkClient.setData("/fuxi/lisi01", "xiaosi".getBytes(), -1);
        System.out.println("stat="+stat);
    }

    /**
     * 删除某节点
     * @throws KeeperException
     * @throws InterruptedException
     */
    @Test
    public void deleteZnodeTest() throws KeeperException, InterruptedException {
       zkClient.delete("/fuxi/lisi02",-1);
    }

}
