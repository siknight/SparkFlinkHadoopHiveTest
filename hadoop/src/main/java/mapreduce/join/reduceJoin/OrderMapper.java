package mapreduce.join.reduceJoin;

import mapreduce.join.Order;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

public class OrderMapper extends Mapper<LongWritable,Text,IntWritable,Order> {


    String filename =null;  //文件名称
    Order order=new Order();
    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        FileSplit fs= (FileSplit)context.getInputSplit();
        //获取文件名称
        filename = fs.getPath().toString();
        System.out.println("filename="+filename);
    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] split = value.toString().split(" ");

        if (filename.contains("order.txt")){
            System.out.println("0="+split[0]+",1="+split[1]+",2="+split[2]);
            order.setId(Integer.parseInt(split[0]));
            order.setPid(Integer.parseInt(split[1]));
            order.setAmount(Integer.parseInt(split[2]));

            order.setPname("");
            order.setFlag(true);
        }else  if (filename.contains("pd.txt")){
            System.out.println("pd0="+split[0]+",1="+split[1]);
            order.setId(0);
            order.setPid(Integer.parseInt(split[0]));
            order.setPname(split[1]);
            order.setAmount(0);
            order.setFlag(false);
        }
        System.out.println(order);
        context.write(new IntWritable(order.getPid()),order);
    }
}
