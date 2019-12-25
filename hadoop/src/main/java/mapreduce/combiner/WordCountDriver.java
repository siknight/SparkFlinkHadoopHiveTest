package mapreduce.combiner;

import mapreduce.wordcount.WordCountMappper;
import mapreduce.wordcount.WordCountReducer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class WordCountDriver {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        //1.获取一个job
        Job job = Job.getInstance(new Configuration());
        //2.指定job的jar包路径
        job.setJarByClass(WordCountDriver.class);
        //3.关联mappper和reducer类
        job.setMapperClass(WordCountMappper.class);
        job.setReducerClass(WordCountReducer.class);
        //4.指定mappper的kv输出类型
        job.setMapOutputKeyClass(Text.class);;
        job.setMapOutputValueClass(LongWritable.class);
        //5.指定reducer输出的类型，也是最终输出的类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);


        //设置combiner
        job.setCombinerClass(WordCountCombiner.class);



        //6.指定job输入的路径
       FileInputFormat.setInputPaths(job,new Path("in/two"));

        //7.指定job输出的路径
       FileOutputFormat.setOutputPath(job,new Path("out/hadoop/combiner/03"));

        //提交job
        boolean b = job.waitForCompletion(true);
        System.out.println("b="+b);

        System.exit(b ? 0:1);

    }
}
