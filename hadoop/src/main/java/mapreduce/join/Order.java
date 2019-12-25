package mapreduce.join;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class Order  implements Writable {
    private int id ;
    private int pid;
    private int amount;
    private String pname;
    private boolean flag;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public boolean getFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeInt(id);
        dataOutput.writeInt(pid);
        dataOutput.writeInt(amount);
        dataOutput.writeUTF(pname);
        dataOutput.writeBoolean(flag);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        id=dataInput.readInt();
        pid=dataInput.readInt();
        amount=dataInput.readInt();
        pname=dataInput.readUTF();
        flag=dataInput.readBoolean();
    }


    @Override
    public String toString() {

        return  id + "\t" + pname + "\t" + amount ;
    }
}
