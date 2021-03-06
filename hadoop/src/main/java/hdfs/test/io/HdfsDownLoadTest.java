package hdfs.test.io;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class HdfsDownLoadTest {
    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
        Configuration configuration = new Configuration();
        FileSystem fs =
                FileSystem.get(new URI("hdfs://hadoop102:9000"),
                        configuration, "lisi");

        FSDataInputStream fdis = fs.open(new Path("/fuxi/out/phonedata"));
        FileOutputStream fos = new FileOutputStream("out/hadoop/io01");
        IOUtils.copyBytes(fdis,fos,configuration);
        fos.close();
        fdis.close();
        fs.close();

    }
}
