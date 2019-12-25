package phoneWriter;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class PhoneFlowDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        //1.获取一个job
        Job job = Job.getInstance(new Configuration());
        job.setJarByClass(PhoneFlowDriver.class);

        job.setMapperClass(PhoneMapper.class);
        job.setReducerClass(PhoneReducer.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(PhoneFlow.class);

        job.setOutputKeyClass(NullWritable.class);
        job.setOutputValueClass(PhoneFlow.class);

        FileInputFormat.setInputPaths(job,new Path("in/phone_data.txt"));
        FileOutputFormat.setOutputPath(job,new Path("out/hadoop/flowBean/03"));

        boolean b = job.waitForCompletion(true);
        System.exit(b?1:0);
    }
}
