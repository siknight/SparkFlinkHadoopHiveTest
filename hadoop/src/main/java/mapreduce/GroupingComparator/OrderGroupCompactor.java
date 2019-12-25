package mapreduce.GroupingComparator;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class OrderGroupCompactor extends WritableComparator {
    protected OrderGroupCompactor() {
        super(OrderBean.class,true);
    }

    @Override
    public int compare(WritableComparable a, WritableComparable b) {

        OrderBean aBean = (OrderBean) a;
        OrderBean bBean = (OrderBean) b;

        int result;
        if (aBean.getOrderid() > bBean.getOrderid()) {
            result = 1;
        } else if (aBean.getOrderid() < bBean.getOrderid()) {
            result = -1;
        } else {
            result = 0;
        }

        return result;
    }
}
