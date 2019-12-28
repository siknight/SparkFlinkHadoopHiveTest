package mapreduce.压缩.map端压缩;

import mapreduce.wordcount.WordCountMappper;
import mapreduce.wordcount.WordCountReducer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.compress.BZip2Codec;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * map端压缩，mapper和reduce方法都不用变
 * 在main方法里加入几个配置就行
 * 	// 开启map端输出压缩
 * 	configuration.setBoolean("mapreduce.map.output.compress", true);
 * 		// 设置map端输出压缩方式
 * 	configuration.setClass("mapreduce.map.output.compress.codec", BZip2Codec.class, CompressionCodec.class);
 */
public class WordCountDriver {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        /*
            压缩配置
         */
        Configuration configuration = new Configuration();
        // 开启map端输出压缩
        configuration.setBoolean("mapreduce.map.output.compress", true);
        // 设置map端输出压缩方式
        configuration.setClass("mapreduce.map.output.compress.codec", BZip2Codec.class, CompressionCodec.class);


        //1.获取一个job
        Job job = Job.getInstance(configuration);

        job.setJarByClass(WordCountDriver.class);

        job.setMapperClass(WordCountMappper.class);
        job.setReducerClass(WordCountReducer.class);

        job.setMapOutputKeyClass(Text.class);;
        job.setMapOutputValueClass(LongWritable.class);
        //5.指定reducer输出的类型，也是最终输出的类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
        //6.指定job输入的路径
        FileInputFormat.setInputPaths(job,new Path("in/two"));

        //7.指定job输出的路径
        FileOutputFormat.setOutputPath(job,new Path("out/hadoop/yasuo/map01"));

        //提交job
        boolean b = job.waitForCompletion(true);
        System.out.println("b="+b);


        System.exit(b ? 0:1);

    }
}
