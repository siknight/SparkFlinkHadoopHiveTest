package mapreduce.join.mapJoin;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.*;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class OpMapper extends Mapper<LongWritable,Text,Text,NullWritable> {
    Map<String, String> map = new HashMap<>();
    @Override
    protected void setup(Context context) throws IOException, InterruptedException {

        URI[] cacheFiles = context.getCacheFiles();

        BufferedReader  reader =
                new BufferedReader(new InputStreamReader(
                        new FileInputStream(cacheFiles[0].getPath()),"UTF-8"));
        String line;
        while(StringUtils.isNotEmpty(line=reader.readLine())){
            String[] split = line.split(" ");
            String pid=split[0];
            String pname=split[1];
            map.put(pid,pname);
        }


    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] s = value.toString().split(" ");
        String id = s[0];
        String pid = s[1];
        String amount = s[2];

        String name = map.get(pid);
        String out=id+" "+name+" "+amount;
        context.write(new Text(out),NullWritable.get());

    }
}
