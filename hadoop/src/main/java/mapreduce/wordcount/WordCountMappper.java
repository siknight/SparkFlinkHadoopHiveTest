package mapreduce.wordcount;


import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * maptask进程
 */

public class WordCountMappper  extends Mapper<LongWritable,Text,Text,LongWritable> {

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {

        super.setup(context);
        System.out.println("开始");
    }

    @Override
    protected void cleanup(Context context) throws IOException, InterruptedException {

        super.cleanup(context);
        System.out.println("结束");
    }

    /**
     * map()方法（maptask进程）对每一个<K,V>调用一次
     */
    LongWritable num = new LongWritable(1);
    Text text = new Text();
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //获取一行
        String line = value.toString();
        //获取一个个切分的单词
        String[] words =line.split(" ");

        for (String word : words){
            text.set(word);
            //context上下文将数据写出
            context.write(text,num);
        }
    }
}
