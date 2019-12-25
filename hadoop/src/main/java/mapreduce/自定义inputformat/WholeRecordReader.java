package mapreduce.自定义inputformat;

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

/**
     *
     * private boolean notRead = true;
     *     private Text key = new Text();
     *     private BytesWritable value = new BytesWritable();
     *
     *     private FSDataInputStream inputStream;
     *     private FileSplit fs;
 *
 */

public class WholeRecordReader extends RecordReader<Text,BytesWritable> {
    //文件是否读，notRead为true表示没读，为false表示已经读过
    private boolean notRead = true;
    //key
    private Text key = new Text();
    private BytesWritable value = new BytesWritable();

    private FSDataInputStream inputStream;
    private FileSplit fs;

    @Override
    public void initialize(InputSplit inputSplit, TaskAttemptContext taskAttemptContext) throws IOException, InterruptedException {
        //转换切片类型为文件切片
        fs=(FileSplit)inputSplit;
        //通过切片获得路径
        Path path= fs.getPath();
        //通过路径获取文件系统
        FileSystem fileSystem =path.getFileSystem(new Configuration());
        //打开输入流
        inputStream = fileSystem.open(path);

    }

    /**
     * 读取下一组kv
     * @return 如果读到了，返回true，如果读完了返回false
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    public boolean nextKeyValue() throws IOException, InterruptedException {
        if (notRead){
            //具体读文件的过程
            //读Key,将切片的路径设置为key
            key.set(fs.getPath().toString());
            //读value
            byte[] buf=new byte[(int)fs.getLength()];
            inputStream.read(buf);
            value.set(buf,0,buf.length);
            notRead=false;
            return true;  //返回true表示读成功
        }else{
            return  false;  //一次读成功后就关闭
        }


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
        return notRead?0:1;
    }

    @Override
    public void close() throws IOException {
        IOUtils.closeStream(inputStream);
    }
}
