package mapreduce.扩展案例.倒排索引案例.第二次处理;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class TwoIndexDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Job job = Job.getInstance(new Configuration());
        job.setJarByClass(TwoIndexDriver.class);

        job.setMapperClass(TwoIndexMapper.class);
        job.setReducerClass(TwoIndexReducer.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        FileInputFormat.setInputPaths(job,new Path("in/daopai"));
        FileOutputFormat.setOutputPath(job,new Path("out/hadoop/倒排索引/twoIndex05"));

        boolean b = job.waitForCompletion(true);

        System.exit(b?0:1);
    }
}
