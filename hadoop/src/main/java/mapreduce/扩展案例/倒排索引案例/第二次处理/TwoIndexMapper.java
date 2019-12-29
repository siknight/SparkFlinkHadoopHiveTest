package mapreduce.扩展案例.倒排索引案例.第二次处理;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class TwoIndexMapper extends Mapper<LongWritable,Text,Text,Text> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] split = value.toString().split("--");
        System.out.println("split[0]="+split[0]+",split[1]="+split[1]);
        context.write(new Text(split[0]),new Text(split[1]));
    }
}
