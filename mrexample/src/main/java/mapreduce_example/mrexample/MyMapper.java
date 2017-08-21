package mapreduce_example.mrexample;

import java.io.IOException;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MyMapper extends Mapper<LongWritable, Text, Text, DoubleWritable> {
	
	public void map(LongWritable key,Text value,Context context)
		throws IOException,InterruptedException
		{
		
			String[] csv = value.toString().split(";");
			
			final String storerKey = csv[5];
			final String cantidad = csv[7];
			final String estado = csv[15];
			
			
			if(NumberUtils.isNumber(cantidad))
			{
				context.write(new Text(storerKey), new DoubleWritable(NumberUtils.toDouble(cantidad)));
				
			}
		
		
		}
	

}
