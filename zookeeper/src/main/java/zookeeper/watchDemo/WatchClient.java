package zookeeper.watchDemo;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 *
 * 这个方法是核心
 *
 * 监听客户端，用于监听/fuxi的Znode的子节点变化，
 * 首先启动，然后启动WatchServer或者在shell命令上创建删除子节点
 *      或者直接用zookeeperTest进行单元测试;
 *
 *
 */
public class WatchClient {

    private static String server="hadoop102:2181";
    private static  String nodePath="/fuxi";
    private static ZooKeeper zooKeeper=null;
    public static void main(String[] args) throws IOException, InterruptedException {
        getConnection();
        System.out.println("long  hehehhehe");
        Thread.sleep(Long.MAX_VALUE);
        System.out.println("long hehehehehhe2");
    }

    /**
     * 遍历子节点，这个是放在new Watch（）方法里用于监听
     * @throws KeeperException
     * @throws InterruptedException
     */
    private static void listChildren() throws KeeperException, InterruptedException {
        List<String> children = zooKeeper.getChildren(nodePath, true);
        System.out.println("children="+new String(String.valueOf(children)));
    }

    /**
     * 连接到hdfs并启动监听事件
     * @throws IOException
     */
    private static void getConnection() throws IOException {
        zooKeeper=new ZooKeeper(server, 2000, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                System.out.println(watchedEvent.getType()+"---"+watchedEvent.getPath());
                try {
                    listChildren();
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
