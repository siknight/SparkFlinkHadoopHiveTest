package mapreduce.手机流量和分区;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * flowBean的reducer方法
 */
public class FlowBeanReducer extends Reducer<Text,FlowBean,NullWritable,FlowBean> {
    /**
     * 每一k相同的kv对执行一次reducer方法
     * @param key
     * @param values
     * @param context
     * @throws IOException
     * @throws InterruptedException
     */
    FlowBean flowBean = new FlowBean();
    NullWritable nullWritable = NullWritable.get();
    @Override
    protected void reduce(Text key, Iterable<FlowBean> values, Context context) throws IOException, InterruptedException {
        long sumUpFlow = 0;
        long  sumDownFlow = 0;
        //对相同手机号的上行，下行流量进行相加
        for (FlowBean flowBean : values){
            sumUpFlow += flowBean.getUpFlow();
            sumDownFlow += flowBean.getDownFlow();
        }
        //对处理后数据进行封装
        flowBean.setFlowBean(Long.parseLong(key.toString()),sumUpFlow, sumDownFlow);

        //将flowBean写出去
        context.write(nullWritable,flowBean);

    }
}
