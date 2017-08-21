package mapreduce_example.mrexample;

import java.io.IOException;
	
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class MyReduce extends Reducer<Text, DoubleWritable, Text, DoubleWritable> {

	
	public void reduce(Text key,Iterable<DoubleWritable> values,Context context)
		throws IOException,InterruptedException
		{
		
			double total = new Double(0);
		
			for (DoubleWritable v : values)
			{
				total += v.get();
			}
			
			context.write(key, new DoubleWritable(total));
		
		
		}
	
}
