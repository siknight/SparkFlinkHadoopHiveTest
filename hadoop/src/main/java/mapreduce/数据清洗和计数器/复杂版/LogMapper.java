package mapreduce.数据清洗和计数器.复杂版;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class LogMapper extends Mapper<LongWritable,Text,Text,NullWritable> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String[] fields = line.split(" ");
        LogBean logBean = new LogBean();
        if (fields.length>11){
            // 2封装数据
            logBean.setRemote_addr(fields[0]); // 记录客户端的ip地址
            logBean.setRemote_user(fields[1]);// 记录客户端用户名称,忽略属性"-"
            logBean.setTime_local(fields[3].substring(1)); // 记录访问时间与时区
            logBean.setRequest(fields[6]);// 记录请求的url与http协议
            logBean.setStatus(fields[8]); // 记录请求状态；成功是200
            logBean.setBody_bytes_sent(fields[9]); // 记录发送给客户端文件主体内容大小
            logBean.setHttp_referer(fields[10]); // 用来记录从那个页面链接访问过来的
            if (fields.length > 12) {
                logBean.setHttp_user_agent(fields[11] + " " + fields[12]);
            }else {
                logBean.setHttp_user_agent(fields[11]);
            }

            if(Integer.parseInt(logBean.getStatus())<400){
                context.write(new Text(logBean.toString()),NullWritable.get());
            }
        }




    }
}
