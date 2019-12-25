package mapreduce.combiner;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * reduceTask进程
 */
public class WordCountCombiner extends Reducer<Text,LongWritable,Text,LongWritable> {
    /**
     * reduceTask进程对每一组相同k的<k,v>组调用一次reduce方法
     * @param key
     * @param values
     * @param context
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void reduce(Text key, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException {
        int sum =0;
        for (LongWritable value : values){
            sum += (int)value.get();
        }

        context.write(key,new LongWritable(sum));
    }
}
