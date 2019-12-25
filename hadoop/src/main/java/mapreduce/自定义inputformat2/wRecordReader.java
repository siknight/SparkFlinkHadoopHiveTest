package mapreduce.自定义inputformat2;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;


import java.io.IOException;

public class wRecordReader  extends RecordReader<Text,BytesWritable> {

    FileSplit fs=null;
    Text key=new Text();
    BytesWritable value = new BytesWritable();
    Boolean flag =true;
    Configuration configuration=null;
    FSDataInputStream fsDataInputStream =null;

    @Override
    public void initialize(InputSplit inputSplit, TaskAttemptContext taskAttemptContext) throws IOException, InterruptedException {

        fs=(FileSplit) inputSplit;
        configuration=taskAttemptContext.getConfiguration();

    }

    @Override
    public boolean nextKeyValue() throws IOException, InterruptedException {
        if(flag){

            Path path = fs.getPath();
            FileSystem fileSystem = path.getFileSystem(configuration);
            fsDataInputStream = fileSystem.open(path);
            //设置key
            key.set(path.toString());

            byte[] bytes = new byte[(int) fs.getLength()];
//            IOUtils.readFully(fsDataInputStream,bytes,0,bytes.length);
            fsDataInputStream.read(bytes);
            //设置value
            value.set(bytes,0,bytes.length);



            flag=false;
            return true;  //只有为true，才会执行map方法
        }

        return false;
    }

    @Override
    public Text getCurrentKey() throws IOException, InterruptedException {
        return key;
    }

    @Override
    public BytesWritable getCurrentValue() throws IOException, InterruptedException {
        return value;
    }

    @Override
    public float getProgress() throws IOException, InterruptedException {
        return 0;  //一个task处理完就结束了
    }

    @Override
    public void close() throws IOException {
        IOUtils.closeStream(fsDataInputStream);
    }
}
