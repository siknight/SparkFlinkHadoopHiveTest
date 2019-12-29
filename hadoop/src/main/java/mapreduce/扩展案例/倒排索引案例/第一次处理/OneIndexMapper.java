package mapreduce.扩展案例.倒排索引案例.第一次处理;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

public class OneIndexMapper extends Mapper<LongWritable,Text,Text,IntWritable> {
    String fileName =null;
    Text text= new Text();
    IntWritable va=new IntWritable(1);
    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        FileSplit fs = (FileSplit) context.getInputSplit();
        fileName = fs.getPath().getName();

    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] split = value.toString().split(" ");
        for (String s:split){
            text.set(s+"--"+fileName);
            System.out.println(text.toString());
            context.write(text,va);
        }

    }
}
