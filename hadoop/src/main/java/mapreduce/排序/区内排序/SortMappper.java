package mapreduce.排序.区内排序;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class SortMappper  extends Mapper<LongWritable,Text,FlowBean,Text> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] split = value.toString().split(" ");
        FlowBean flowBean = new FlowBean();
        flowBean.setPhone(Long.parseLong(split[0]));
        flowBean.setUpFlow(Integer.parseInt(split[1]));
        flowBean.setDownFlow(Integer.parseInt(split[2]));
        flowBean.setSumFlow(Integer.parseInt(split[3]));
        context.write(flowBean,new Text(split[0]));
    }
}
