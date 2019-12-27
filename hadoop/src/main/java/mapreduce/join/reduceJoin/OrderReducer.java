package mapreduce.join.reduceJoin;


import mapreduce.join.Order;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
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
            Order od = new Order();
            if (o.getFlag()==false){
                System.out.println("pdname="+o.getPname());
                System.out.println("key="+key);
                name=o.getPname();
            }else{
                System.out.println("key="+key);
                System.out.println("oid="+o.getId());
                try {
                    //这里不用这个工具拷，会不成功 .例如直接orders.add(od);这样就会失败，因为for（order o:values）这里的o是临时变量
                    //放到orders.add(od)里的是一个地址，地址不会变，永远只会存最后一个值，
                    BeanUtils.copyProperties(od,o);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                orders.add(od);
            }

        }

        for (Order o1:orders){


                System.out.println("id="+o1.getId());
               o1.setPname(name);
               context.write(o1,NullWritable.get());

        }




    }
}
