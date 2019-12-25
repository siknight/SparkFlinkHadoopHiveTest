package phoneWriter;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class PhoneMapper extends Mapper<LongWritable,Text,Text,PhoneFlow> {

    /**
     * 期望输入
         * 7 	13560436666	120.196.100.99		1116		 954			200
         * id	手机号码		网络ip			上行流量  下行流量     网络状态码
     * 期望输出
         * 13560436666 		1116		      954 			2070
         * 手机号码		    上行流量        下行流量		总流量
     * @param key
     * @param value
     * @param context
     * @throws IOException
     * @throws InterruptedException
     */
    PhoneFlow pf = new PhoneFlow();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
//        super.map(key, value, context);
        String[] split = value.toString().split("\t");
        String phone = split[1];
        String downFlow = split[split.length - 2];
        String upflow = split[split.length - 3];
        pf.setPhone(phone);
        pf.setUpFlow(Integer.valueOf(upflow));
        pf.setDownFlow(Integer.valueOf(downFlow));
        pf.setSumFlow(Integer.valueOf(upflow)+Integer.valueOf(downFlow));
        context.write(new Text(phone),pf);

    }


}
