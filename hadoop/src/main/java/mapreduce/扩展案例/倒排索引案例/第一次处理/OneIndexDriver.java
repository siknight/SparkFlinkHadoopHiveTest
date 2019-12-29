package mapreduce.扩展案例.倒排索引案例.第一次处理;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class OneIndexDriver {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Job job = Job.getInstance(new Configuration());
        job.setJarByClass(OneIndexDriver.class);

        job.setMapperClass(OneIndexMapper.class);
        job.setReducerClass(OneIndexReducer.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        FileInputFormat.setInputPaths(job,new Path("in/倒排索引"));
        FileOutputFormat.setOutputPath(job,new Path("out/hadoop/倒排索引/oneIndex01"));

        boolean b = job.waitForCompletion(true);
        System.exit(b?0:1);


    }
}
