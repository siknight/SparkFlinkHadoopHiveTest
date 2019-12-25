package mapreduce.join.reduceJoin;


import mapreduce.join.Order;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.ArrayList;

/**
 * 这个reduce非常奇怪，思路是对的，结果总是错，同一个values循环不能执行两次
 */
public class OrderReducer extends Reducer<IntWritable,Order,Order,NullWritable> {
    @Override
    protected void reduce(IntWritable key, Iterable<Order> values, Context context) throws IOException, InterruptedException {

        String name ="";
        ArrayList<Order> orders = new ArrayList<>();

        for (Order o:values){

            if (o.getFlag()==false){
                name=o.getPname();
            }

            orders.add(o);
        }

        for (Order o1:orders){
            if (o1.getFlag()==true){
               o1.setPname(name);
               context.write(o1,NullWritable.get());
            }
        }




    }
}
