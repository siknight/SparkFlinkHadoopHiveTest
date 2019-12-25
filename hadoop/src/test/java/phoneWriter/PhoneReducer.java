package phoneWriter;


import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class PhoneReducer  extends Reducer<Text,PhoneFlow,NullWritable,PhoneFlow> {
    PhoneFlow pf2= new PhoneFlow();
    @Override
    protected void reduce(Text key, Iterable<PhoneFlow> values, Context context) throws IOException, InterruptedException {
        int upFlow =0;
        int downFlow =0;
        int sumFlow=0;
        for (PhoneFlow pf:values){
            upFlow=pf.getUpFlow();
            downFlow=pf.getDownFlow();
            sumFlow=pf.getSumFlow();
        }
        pf2.setPhone(key.toString());
        pf2.setUpFlow(upFlow);
        pf2.setDownFlow(downFlow);
        pf2.setSumFlow(sumFlow);

        context.write(NullWritable.get(),pf2);
    }
}
