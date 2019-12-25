package hdfs.test.io;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;


public class HdfsIOUploadTest {
    /**
     * 上传文件到hdfs
     */
    @Test
    public static   void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
        // 1 获取文件系统
        Configuration configuration = new Configuration();
        FileSystem fs =
                FileSystem.get(new URI("hdfs://hadoop102:9000"),
                            configuration, "lisi");

        FileInputStream fis= new FileInputStream("in/phone_data.txt");
        FSDataOutputStream fdos = fs.create(new Path("/fuxi/out/phonedata2"));

        //流对拷
        IOUtils.copyBytes(fis,fdos,configuration);
        //关闭流
        IOUtils.closeStream(fdos);
        IOUtils.closeStream(fis);
        fs.close();


    }
}
