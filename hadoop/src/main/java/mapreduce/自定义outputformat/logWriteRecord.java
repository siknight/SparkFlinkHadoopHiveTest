package mapreduce.自定义outputformat;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;

import java.io.IOException;

public class logWriteRecord extends RecordWriter<Text, NullWritable> {
    Configuration configuration =null;
    FileSystem fileSystem =null;
    FSDataOutputStream atguigu =null;
    FSDataOutputStream other = null;
    public logWriteRecord(TaskAttemptContext job) throws IOException {
        configuration = job.getConfiguration();
        fileSystem = FileSystem.get(configuration);
        atguigu = fileSystem.create(new Path("out/hadoop/log/atguigu.log"));
        other = fileSystem.create(new Path("out/hadoop/log/other.log"));
    }

    @Override
    public void write(Text text, NullWritable nullWritable) throws IOException, InterruptedException {
        String s = text.toString();


        if (s.contains("atguigu")){

            atguigu.writeBytes(s);
        }else{

            other.writeBytes(s);
        }
    }

    @Override
    public void close(TaskAttemptContext taskAttemptContext) throws IOException, InterruptedException {

        IOUtils.closeStream(atguigu);
        IOUtils.closeStream(other);

    }
}
