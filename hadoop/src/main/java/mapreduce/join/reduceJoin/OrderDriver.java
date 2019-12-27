package mapreduce.join.reduceJoin;

import mapreduce.join.Order;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;

import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class OrderDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Job job = Job.getInstance(new Configuration());

        job.setJarByClass(OrderDriver.class);
        job.setMapperClass(OrderMapper.class);
        job.setReducerClass(OrderReducer.class);

        job.setMapOutputKeyClass(IntWritable.class);
       job.setMapOutputValueClass(Order.class);
       job.setOutputKeyClass(Order.class);
       job.setOutputValueClass(NullWritable.class);

        FileInputFormat.setInputPaths(job,new Path("in/order"));
       FileOutputFormat.setOutputPath(job,new Path("out/hadoop/order/33"));

        boolean b = job.waitForCompletion(true);
        System.exit(b?0:1);

    }
}
