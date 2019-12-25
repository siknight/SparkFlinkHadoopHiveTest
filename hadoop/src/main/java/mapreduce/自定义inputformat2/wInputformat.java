package mapreduce.自定义inputformat2;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;

import java.io.IOException;
import java.util.List;


public class wInputformat extends FileInputFormat<Text,BytesWritable> {

    @Override
    protected boolean isSplitable(JobContext context, Path filename) {
        return false;  //false 表示文件不可以分片，true表示文件可以分片
    }

    @Override
    public RecordReader<Text, BytesWritable> createRecordReader(InputSplit inputSplit, TaskAttemptContext taskAttemptContext) throws IOException, InterruptedException {
        wRecordReader rr = new wRecordReader();
        rr.initialize(inputSplit,taskAttemptContext);
        return rr;
    }
}
