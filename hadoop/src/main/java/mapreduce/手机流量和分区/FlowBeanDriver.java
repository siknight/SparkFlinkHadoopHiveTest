package mapreduce.手机流量和分区;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class FlowBeanDriver {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
        //获取job对象
        Job job = Job.getInstance(conf);
        //设置job方法入口的驱动类
        job.setJarByClass(FlowBeanDriver.class);
        //设置Mapper组件类
        job.setMapperClass(FlowBeanMapper.class);
        //设置mapper的输出key类型
        job.setMapOutputKeyClass(Text.class);
        //设置Mappper的输出value类型，注意Text的导包问题
        job.setMapOutputValueClass(FlowBean.class);
        //设置reduce组件类
        job.setReducerClass(FlowBeanReducer.class);
        //设置reduce输出的key和value类型
        job.setOutputKeyClass(NullWritable.class);
        job.setOutputValueClass(FlowBean.class);
        /**
         * 设置分区类和分区大小
         */
//        job.setPartitionerClass(FlowBeanPartitioner.class);
//        job.setNumReduceTasks(5);

//        //设置输入路径
         FileInputFormat.setInputPaths(job, new Path("in/phone_data.txt"));
//        //设置输出结果路径，要求结果路径事先不能存在
         FileOutputFormat.setOutputPath(job, new Path("out/hadoop/flowBean/10"));
        //设置输入路径
//        FileInputFormat.setInputPaths(job, new Path("hdfs://hadoop102:9000/in/phone_data.txt"));
//        //设置输出结果路径，要求结果路径事先不能存在
//        FileOutputFormat.setOutputPath(job, new Path("hdfs://hadoop102:9000/out/phonedata/01"));
        //提交job
        boolean b = job.waitForCompletion(true);
        System.out.println("b="+b);
        //0正常结束，1非正常结束
        System.exit(b ? 0:1);
    }
}
