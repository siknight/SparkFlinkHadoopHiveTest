package mapreduce.beanwriteble;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class FlowBeanMapper extends Mapper<LongWritable,Text,Text,FlowBean> {
    /**
     * 每一个kv对执行一次map方法
     * @param key
     * @param value
     * @param context
     * @throws IOException
     * @throws InterruptedException
     *
     */

    Text phoneText = new Text();
    FlowBean flowBean = new FlowBean();
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //获取一行数据
        String line = value.toString();
        //按tab进行拆分,tab用\t
        String[] split = line.split("\t");
        System.out.println("splitlength="+split.length);
        //获取flowbean的相关属性，然后进行拆分,
        //手机号
        String phone =split[1];
        //上行流量
         String upFlow = split[split.length-3];
        //下行流量
        String downFlow = split[split.length - 2];
        //将相关属性封装成flowBean
        flowBean.setFlowBean(Long.parseLong(phone),
                     Long.parseLong(upFlow), Long.parseLong(downFlow));
        //将phone封装成序列化Text
        phoneText.set(phone);
        //context上下文将flowbean写出去
        context.write(phoneText,flowBean);



    }
}
