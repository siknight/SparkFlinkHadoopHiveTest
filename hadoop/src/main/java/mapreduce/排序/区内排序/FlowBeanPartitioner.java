package mapreduce.排序.区内排序;


import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 * 这里的partitioner泛型是从mapper出来的
 */
public class FlowBeanPartitioner extends Partitioner<FlowBean,Text> {

    @Override
    public int getPartition(FlowBean flowBean, Text text, int i) {
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
