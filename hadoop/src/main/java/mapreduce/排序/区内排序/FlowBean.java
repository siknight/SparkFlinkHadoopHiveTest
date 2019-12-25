package mapreduce.排序.区内排序;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * 自定义流量类，实现序列化
 */
public class FlowBean implements WritableComparable<FlowBean> {
    //手机号
    private long phone;
    //上行流量
    private long upFlow;
    //下行流量
    private long downFlow;
    //总流量
    private long sumFlow;
    //无参构造 ,反序列化时，需要反射调用空参构造函数，所以必须有
    public FlowBean() {
    }
    //有参构造
    public FlowBean(long phone, long upFlow, long downFlow) {
        this.phone = phone;
        this.upFlow = upFlow;
        this.downFlow = downFlow;
        //计算sumFlow
        this.sumFlow = upFlow + downFlow;
    }
     //对flow用set赋值，mapper方法用，可以减少创建对象
    public void setFlowBean(long phone,long upFlow, long downFlow) {
        this.phone = phone;
        this.upFlow = upFlow;
        this.downFlow = downFlow;
        //计算sumFlow
        this.sumFlow = upFlow + downFlow;
    }
    //对flow用set赋值，reducer方法用，可以减少创建对象
    public void setFlowBean(long upFlow, long downFlow) {
        this.upFlow = upFlow;
        this.downFlow = downFlow;
        //计算sumFlow
        this.sumFlow = upFlow + downFlow;
    }
    //下面是set和get方法


    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public long getUpFlow() {
        return upFlow;
    }

    public void setUpFlow(long upFlow) {
        this.upFlow = upFlow;
    }

    public long getDownFlow() {
        return downFlow;
    }

    public void setDownFlow(long downFlow) {
        this.downFlow = downFlow;
    }

    public long getSumFlow() {
        return sumFlow;
    }

    public void setSumFlow(long sumFlow) {
        this.sumFlow = sumFlow;
    }



    /**
     * 将flowBean相关属性序列化
     * @param dataOutput
     * @throws IOException
     */
    @Override
    public void write(DataOutput dataOutput) throws IOException {
            dataOutput.writeLong(phone);
            dataOutput.writeLong(upFlow);
            dataOutput.writeLong(downFlow);
            dataOutput.writeLong(sumFlow);
    }



    /**
     * 反序列化方法
     *   特别注意：
     *     反序列化方法读顺序必须和写序列化方法的写顺序必须一致
     * @param dataInput
     * @throws IOException
     */
    @Override
    public void readFields(DataInput dataInput) throws IOException {
        this.phone = dataInput.readLong();
        this.upFlow=dataInput.readLong();
        this.downFlow=dataInput.readLong();
        this.sumFlow=dataInput.readLong();
    }

    /**
     * 此方法为reduce最终输出的格式。
     * @return
     */
    @Override
    public String toString() {

        return phone+"  "+upFlow + "   " + downFlow + "   " + sumFlow;
    }

    /**
     * 用于排序
     * @param o
     * @return
     */
//    @Override
//    public int compareTo(FlowBean o) {
//        return Long.compare(o.sumFlow, this.sumFlow);
//    }
    @Override
    public int compareTo(FlowBean o) {
        return Long.compare(o.sumFlow, this.sumFlow);

    }
}
