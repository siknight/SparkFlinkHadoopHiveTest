package hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class HdfsTest {

    /**
     * 第一种在hdfs上创建文件的方法
     * hdfs创建文件方式01
     */
    @Test
    public void createMkdirTest(){
        //hadoop的基本配置
        Configuration conf=new Configuration();
        //hadoop配置hdfs地址
        conf.set("fs.defaultFS","hdfs://hadoop102:9000");

        //设置用户身份，以lisi用户，否者没有权限
        System.setProperty("HADOOP_USER_NAME","lisi");
        FileSystem fs = null;
        try {
            //获取hdfs客户端
             fs =FileSystem.get(conf);
            //客户端创建文件
            fs.create(new Path("/lisi/lisi06"));
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                fs.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("创建文件over");
    }

    /**
     * 第二种在hdfs上创建文件的方法
     */
    @Test
    public void createMkdir02() throws URISyntaxException, IOException, InterruptedException {

        FileSystem fs = FileSystem.get(
                  new URI("hdfs://hadoop102:9000"), new Configuration(), "lisi");
        fs.mkdirs(new Path("/lisi/lisi07"));
        System.out.println("over");
    }

    /**
     * 上传文件
     * @throws URISyntaxException
     * @throws IOException
     * @throws InterruptedException
     */
    @Test
    public void uploadFile() throws URISyntaxException, IOException, InterruptedException {
        FileSystem fs = FileSystem.get(
                new URI("hdfs://hadoop102:9000"), new Configuration(), "lisi");
        fs.copyFromLocalFile(new Path("in/"),new Path("/lisi/lisi01/"));

        System.out.println("over");
    }
    /**
     * 删除文件
     * @throws URISyntaxException
     * @throws IOException
     * @throws InterruptedException
     */
    @Test
    public void deleteFile() throws IOException, URISyntaxException, InterruptedException {
        FileSystem fs = FileSystem.get(
                new URI("hdfs://hadoop102:9000"), new Configuration(), "lisi");
        fs.delete(new Path("/lisi/lisi01/in"),true);

        System.out.println("over");
    }

    /**
     * 查看文件
     * @throws URISyntaxException
     * @throws IOException
     * @throws InterruptedException
     */
    @Test
    public void catFile() throws IOException, URISyntaxException, InterruptedException {
        FileSystem fs = FileSystem.get(
                new URI("hdfs://hadoop102:9000"), new Configuration(), "lisi");
//        fs.getStatus()

        System.out.println("over");
    }
}
