package mapreduce.GroupingComparator;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class OrderDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Job job = Job.getInstance(new Configuration());
        job.setMapperClass(OrderMapper.class);
        job.setReducerClass(OrderReducer.class);

        job.setMapOutputKeyClass(OrderBean.class);
        job.setMapOutputValueClass(NullWritable.class);

        job.setOutputKeyClass(OrderBean.class);
        job.setOutputValueClass(OrderBean.class);
        job.setGroupingComparatorClass(OrderGroupCompactor.class);
        FileInputFormat.setInputPaths(job,new Path("in/GroupingComparator.txt"));
        FileOutputFormat.setOutputPath(job,new Path("out/hadoop/GroupingComparator/9"));

        boolean b = job.waitForCompletion(true);
        System.exit(b?0:1);


    }
}
