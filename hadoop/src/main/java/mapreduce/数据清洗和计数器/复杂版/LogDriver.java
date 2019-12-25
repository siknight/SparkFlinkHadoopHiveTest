package mapreduce.数据清洗和计数器.复杂版;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class LogDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Job job = Job.getInstance(new Configuration());
        job.setJarByClass(LogDriver.class);

        job.setMapperClass(LogMapper.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);

        job.setNumReduceTasks(0);

        FileInputFormat.setInputPaths(job,new Path("in/web.log"));
        FileOutputFormat.setOutputPath(job,new Path("out/hadoop/weblog/05"));

        boolean b = job.waitForCompletion(true);
        System.exit(b?0:1);

    }
}
