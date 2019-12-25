package 自定义inputformat;

import com.google.common.primitives.Bytes;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;
//每个maptask读取一次
public class WhRecordReader extends RecordReader<Text,BytesWritable> {

    Boolean notRead=true;
    Text key = new Text();
    BytesWritable value=new BytesWritable();
    FileSplit fs = null;
    FSDataInputStream fsdis =null;
    Path path =null;

    @Override
    public void initialize(InputSplit inputSplit, TaskAttemptContext taskAttemptContext) throws IOException, InterruptedException {
        System.out.println("recordReader initialize 执行");
       fs=(FileSplit) inputSplit;
        path = fs.getPath();
        fs=(FileSplit) inputSplit;
        FileSystem fileSystem = path.getFileSystem(taskAttemptContext.getConfiguration());
        fsdis = fileSystem.open(path);



    }

    @Override
    public boolean nextKeyValue() throws IOException, InterruptedException {
        if (notRead){

            key.set(path.toString());
            byte[] bytes = new byte[(int) fs.getLength()];

            fsdis.read(bytes);
            value.set(bytes,0,bytes.length);

            notRead=false;  //表示这个文件已经读过
            return true;

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
        return 0;
    }

    @Override
    public void close() throws IOException {

    }
}
