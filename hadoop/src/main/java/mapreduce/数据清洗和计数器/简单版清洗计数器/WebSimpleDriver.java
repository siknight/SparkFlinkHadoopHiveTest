package mapreduce.数据清洗和计数器.简单版清洗计数器;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class WebSimpleDriver {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        //1.获取一个job
        Job job = Job.getInstance(new Configuration());
        //2.指定job的jar包路径
        job.setJarByClass(WebSimpleDriver.class);

        job.setMapperClass(WebSimpleMappper.class);

        //只设置最终输出格式
        job.setOutputKeyClass(Text.class);;
        job.setOutputValueClass(NullWritable.class);

        //没有reduceTask
        job.setNumReduceTasks(0);

        FileInputFormat.setInputPaths(job,new Path("in/web.log"));

        FileOutputFormat.setOutputPath(job,new Path("out/hadoop/weblog/04"));
        //提交job
        boolean b = job.waitForCompletion(true);


        System.exit(b ? 0:1);

    }
}
