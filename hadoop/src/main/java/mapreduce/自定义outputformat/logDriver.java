package mapreduce.自定义outputformat;

import org.apache.hadoop.conf.Configuration;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;


import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class logDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Job job = Job.getInstance(new Configuration());
        job.setJarByClass(logDriver.class);

        job.setMapperClass(logMapper.class);
        job.setReducerClass(logReducer.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(NullWritable.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);

        job.setOutputFormatClass(logOutputFormat.class);
        FileInputFormat.setInputPaths(job,new Path("in/bg.txt"));
        //指定一个success的输出文件 job,new Path("out/hadoop/log/success")
        FileOutputFormat.setOutputPath(job,new Path("out/hadoop/log/success"));
        boolean b = job.waitForCompletion(true);
        System.exit(b?0:1);
    }
}
