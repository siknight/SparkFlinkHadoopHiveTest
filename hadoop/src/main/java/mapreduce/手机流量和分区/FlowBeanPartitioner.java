package mapreduce.手机流量和分区;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 * 这里的partitioner泛型是从mapper出来的
 */
public class FlowBeanPartitioner extends Partitioner<Text,FlowBean> {
    @Override
    public int getPartition(Text text, FlowBean flowBean, int i) {
        String phone = text.toString();
        if(phone.startsWith("136")){
            return 0;
        }else if(phone.startsWith("137")){
            return 1;
        }else if(phone.startsWith("138")){
            return 2;
        }else if(phone.startsWith("139")){
            return 3;
        }else {
            return 4;
        }

    }
}
