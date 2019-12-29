package mapreduce.扩展案例.倒排索引案例.第一次处理;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class OneIndexReducer extends Reducer<Text,IntWritable,Text,IntWritable> {

    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int sum=0;
        for (IntWritable v:values){
            sum+=v.get();
        }
        context.write(key,new IntWritable(sum));
    }
}
