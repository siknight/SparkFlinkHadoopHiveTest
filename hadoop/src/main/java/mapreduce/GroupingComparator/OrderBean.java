package mapreduce.GroupingComparator;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class OrderBean implements WritableComparable<OrderBean> {
    private int orderid;
    private double price;

    public OrderBean() {
    }

    public OrderBean(int orderid, double price) {
        this.orderid = orderid;
        this.price = price;
    }

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public int compareTo(OrderBean o) {
        //按订单号从小到大排列
        if(orderid>o.getOrderid()){
            return 1;
        }else if(orderid<o.getOrderid()){
            return -1;
        }else{
            //按价格从大到小排列
            return price>o.getPrice()?-1:1;
        }

    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeInt(orderid);
        dataOutput.writeDouble(price);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        orderid=dataInput.readInt();
        price=dataInput.readDouble();
    }

    @Override
    public String toString() {
        return
                orderid +
                " " + price;

    }
}
