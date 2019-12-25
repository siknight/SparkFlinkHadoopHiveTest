package mapreduce.GroupingComparator;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class OrderMapper extends Mapper<LongWritable,Text,OrderBean,NullWritable> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] split = value.toString().split(" ");
        System.out.println("split="+split[0]);
        String orderid = split[0];
        String price = split[2];
        OrderBean orderBean = new OrderBean(Integer.parseInt(orderid), Double.parseDouble(price));
        context.write(orderBean,NullWritable.get());
    }
}
