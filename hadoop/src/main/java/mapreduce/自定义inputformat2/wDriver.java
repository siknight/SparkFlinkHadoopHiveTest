package mapreduce.自定义inputformat2;

import mapreduce.自定义inputformat.WholeFileInputFormat;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat;

import java.io.IOException;

public class wDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Job job = Job.getInstance(new Configuration());

        job.setJarByClass(wDriver.class);

        job.setMapperClass(wMapper.class);
        job.setReducerClass(wReducer.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(BytesWritable.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(BytesWritable.class);

        job.setInputFormatClass(wInputformat.class);
        job.setOutputFormatClass(SequenceFileOutputFormat.class);
//


        FileInputFormat.setInputPaths(job, new Path("in/word2"));
        FileOutputFormat.setOutputPath(job, new Path("out/hadoop/s07"));

        boolean b = job.waitForCompletion(true);
        System.exit(b ? 0 : 1);
    }
}
