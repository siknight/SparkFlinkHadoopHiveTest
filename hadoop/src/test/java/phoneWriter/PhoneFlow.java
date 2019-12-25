package phoneWriter;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class PhoneFlow implements WritableComparable<PhoneFlow> {
    private String phone;
    private int upFlow;
    private int downFlow;
    private int sumFlow;


    public PhoneFlow() {
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {

        dataOutput.writeInt(upFlow);
        dataOutput.writeInt(downFlow);
        dataOutput.writeInt(sumFlow);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {

        upFlow=dataInput.readInt();
        downFlow=dataInput.readInt();
        sumFlow=dataInput.readInt();
    }

    public int getUpFlow() {
        return upFlow;
    }

    public void setUpFlow(int upFlow) {
        this.upFlow = upFlow;
    }

    public int getDownFlow() {
        return downFlow;
    }

    public void setDownFlow(int downFlow) {
        this.downFlow = downFlow;
    }

    public int getSumFlow() {
        return sumFlow;
    }

    public void setSumFlow(int sumFlow) {
        this.sumFlow = sumFlow;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    @Override
    public String toString() {
        return
             phone+"\t"+upFlow+"\t"+downFlow+"\t"+sumFlow;
    }

    @Override
    public int compareTo(PhoneFlow o) {
        return Long.compare(sumFlow,o.getSumFlow());
    }
}
