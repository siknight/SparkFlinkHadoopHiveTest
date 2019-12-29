package mapreduce.扩展案例.倒排索引案例.第二次处理;


import org.apache.hadoop.io.Text;

import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class TwoIndexReducer extends Reducer<Text,Text,Text,Text> {

    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        StringBuilder stringBuilder = new StringBuilder();
        for(Text t:values){
            String[] split = t.toString().split(" ");
            stringBuilder.append(split[0]+"-->"+split[1]+" ");
        }
        String va = stringBuilder.toString();
        context.write(key,new Text(va));
    }
}
