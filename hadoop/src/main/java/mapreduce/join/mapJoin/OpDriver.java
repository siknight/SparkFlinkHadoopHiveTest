package mapreduce.join.mapJoin;

import mapreduce.join.reduceJoin.OrderMapper;
import mapreduce.wordcount.WordCountDriver;
import mapreduce.wordcount.WordCountMappper;
import mapreduce.wordcount.WordCountReducer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class OpDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException, URISyntaxException {
        //1.获取一个job
        Job job = Job.getInstance(new Configuration());
        //2.指定job的jar包路径
        job.setJarByClass(OpDriver.class);
        //3.关联mappper
        job.setMapperClass(OpMapper.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);

       job.addCacheFile(new URI("in/order/pd.txt"));
        //6.指定job输入的路径
        FileInputFormat.setInputPaths(job,new Path("in/order/order.txt"));

        //7.指定job输出的路径
        FileOutputFormat.setOutputPath(job,new Path("out/hadoop/order/pd/02"));

        boolean b = job.waitForCompletion(true);
        System.out.println("b="+b);
    }
}
