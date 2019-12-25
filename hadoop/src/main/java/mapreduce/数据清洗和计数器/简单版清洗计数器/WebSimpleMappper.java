package mapreduce.数据清洗和计数器.简单版清洗计数器;


import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * maptask进程
 */

public class WebSimpleMappper extends Mapper<LongWritable,Text,Text,NullWritable> {



    /**
     * map()方法（maptask进程）对每一个<K,V>调用一次
     */

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //获取一行
        String line = value.toString();
        //获取一个个切分的单词
        String[] words =line.split(" ");
        if (words.length>11){
            context.write(value,NullWritable.get());
            //计数器会在控制台输出
            context.getCounter("计数器",">11").increment(1);
        }else{
            //计数器会在控制台输出
            context.getCounter("计数器","<=11").increment(1);
        }
    }
}
